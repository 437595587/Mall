package com.ruoyi.order.service.impl;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.text.UUID;
import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.common.core.utils.IdUtils;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.utils.bean.BeanUtils;
import com.ruoyi.common.redis.service.RedisService;
import com.ruoyi.mall.api.RemoteCartService;
import com.ruoyi.mall.api.RemoteMemberService;
import com.ruoyi.mall.api.RemoteProductService;
import com.ruoyi.mall.api.RemoteWareService;
import com.ruoyi.mall.api.to.WareSkuLockTo;
import com.ruoyi.mall.api.vo.*;
import com.ruoyi.mall.common.core.to.mq.SeckillOrderTo;
import com.ruoyi.order.constant.OrderConstant;
import com.ruoyi.order.domain.Order;
import com.ruoyi.order.domain.OrderItem;
import com.ruoyi.order.domain.PaymentInfo;
import com.ruoyi.order.domain.to.OrderCreateTo;
import com.ruoyi.order.domain.vo.*;
import com.ruoyi.order.enume.OrderStatusEnum;
import com.ruoyi.order.interceptor.LoginUserInterceptor;
import com.ruoyi.order.mapper.OrderItemMapper;
import com.ruoyi.order.mapper.OrderMapper;
import com.ruoyi.order.service.IOrderService;
import com.ruoyi.order.service.IPaymentInfoService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * ??????Service???????????????
 *
 * @author xuxing
 * @date 2021-08-31
 */
@Service
public class OrderServiceImpl implements IOrderService
{
    private ThreadLocal<OrderSubmitVo> confirmVoThreadLocal = new ThreadLocal<>();

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private RemoteMemberService remoteMemberService;

    @Autowired
    private RemoteCartService remoteCartService;

    @Autowired
    private RemoteWareService remoteWareService;

    @Autowired
    private RemoteProductService remoteProductService;

    @Autowired
    ThreadPoolTaskExecutor taskExecutor;

    @Autowired
    private RedisService redisService;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private IPaymentInfoService paymentInfoService;

    /**
     * ????????????
     *
     * @param id ????????????
     * @return ??????
     */
    @Override
    public Order selectOrderById(Long id)
    {
        return orderMapper.selectOrderById(id);
    }

    /**
     * ??????????????????
     *
     * @param order ??????
     * @return ??????
     */
    @Override
    public List<Order> selectOrderList(Order order)
    {
        return orderMapper.selectOrderList(order);
    }

    /**
     * ????????????
     *
     * @param order ??????
     * @return ??????
     */
    @Override
    public int insertOrder(Order order)
    {
        order.setCreateTime(DateUtils.getNowDate());
        return orderMapper.insertOrder(order);
    }

    /**
     * ????????????
     *
     * @param order ??????
     * @return ??????
     */
    @Override
    public int updateOrder(Order order)
    {
        return orderMapper.updateOrder(order);
    }

    /**
     * ??????????????????
     *
     * @param ids ???????????????????????????
     * @return ??????
     */
    @Override
    public int deleteOrderByIds(Long[] ids)
    {
        return orderMapper.deleteOrderByIds(ids);
    }

    /**
     * ??????????????????
     *
     * @param id ????????????
     * @return ??????
     */
    @Override
    public int deleteOrderById(Long id)
    {
        return orderMapper.deleteOrderById(id);
    }

    @Override
    public OrderConfirmVo confirmOrder() throws ExecutionException, InterruptedException {
        OrderConfirmVo confirmVo = new OrderConfirmVo();
        Member member = LoginUserInterceptor.loginUser.get();
        //fein??????????????????????????????
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        CompletableFuture<Void> memberAddressFuture = CompletableFuture.runAsync(() -> {
            RequestContextHolder.setRequestAttributes(requestAttributes);
            //?????????????????????????????????
            R<List<MemberReceiveAddress>> r = remoteMemberService.getMemberAddress(member.getId());
            if (r.getCode() == 200) {
                confirmVo.setAddress(r.getData());
            }
        }, taskExecutor);
        CompletableFuture<Void> cartFuture = CompletableFuture.runAsync(() -> {
            RequestContextHolder.setRequestAttributes(requestAttributes);
            //?????????????????????????????????
            R<List<OrderItemVo>> r2 = remoteCartService.getCurrentUserCartItems();
            if (r2.getCode() == 200) {
                confirmVo.setItems(r2.getData());
            }
        }, taskExecutor).thenRunAsync(() -> {
            List<OrderItemVo> items = confirmVo.getItems();
            List<Long> skuIds = items.stream().map(OrderItemVo::getSkuId).collect(Collectors.toList());
            R<List<SkuHasStockVo>> r = remoteWareService.getSkusHasStock(skuIds);
            if (r.getCode() == 200) {
                List<SkuHasStockVo> data = r.getData();
                Map<Long, Boolean> map = data.stream().collect(Collectors.toMap(SkuHasStockVo::getSkuId, SkuHasStockVo::getHasStock));
                confirmVo.setStocks(map);
            }
        }, taskExecutor);
        //??????????????????
        Long integration = member.getIntegration();
        confirmVo.setIntegration(integration);
        //TODO ????????????
        String token = UUID.randomUUID().toString().replace("-", "");
        redisService.setCacheObject(OrderConstant.USER_ORDER_TOKEN_PREFIX + member.getId(), token, 30L, TimeUnit.MINUTES);
        confirmVo.setOrderToken(token);
        CompletableFuture.allOf(memberAddressFuture, cartFuture).get();
        return confirmVo;
    }

    //?????????????????????????????????seata???AT??????
    //????????????????????????
    // @GlobalTransactional
    @Transactional
    @Override
    public SubmitOrderResponseVo submitOrder(OrderSubmitVo vo) {
        confirmVoThreadLocal.set(vo);
        SubmitOrderResponseVo responseVo = new SubmitOrderResponseVo();
        responseVo.setCode(200);
        Member member = LoginUserInterceptor.loginUser.get();
        //?????? ???????????? ???????????? ????????? ?????????
        //1???????????????(????????????????????????????????????)
        String script = "if redis.call(\"get\",KEYS[1]) == ARGV[1]\n" +
                "then\n" +
                "    return redis.call(\"del\",KEYS[1])\n" +
                "else\n" +
                "    return 0\n" +
                "end";
        String orderToken = vo.getOrderToken();
        Long result = (Long) redisService.redisTemplate.execute(new DefaultRedisScript<>(script, Long.class), Arrays.asList(OrderConstant.USER_ORDER_TOKEN_PREFIX + member.getId()), orderToken);
        if (result == null || result == 0L) {
            responseVo.setCode(500);
            return responseVo;
        } else {
            //2. ????????????????????????
            OrderCreateTo order = createOrder(member);
            //3. ??????
            BigDecimal payAmount = order.getOrder().getPayAmount();
            BigDecimal payPrice = vo.getPayPrice();
            //????????????
            if (Math.abs(payAmount.subtract(payPrice).doubleValue()) < 0.01) {
                //4. ????????????
                saveOrder(order);
                //5. ????????????
                List<OrderItemVo> orderItemVos = order.getOrderItems().stream().map((item) -> {
                    OrderItemVo orderItemVo = new OrderItemVo();
                    orderItemVo.setSkuId(item.getSkuId());
                    orderItemVo.setCount(item.getSkuQuantity().intValue());
                    return orderItemVo;
                }).collect(Collectors.toList());
                WareSkuLockTo lockTo = new WareSkuLockTo();
                lockTo.setOrderSn(order.getOrder().getOrderSn());
                lockTo.setLocks(orderItemVos);
                //????????????????????? ????????????????????????
                R<Boolean> r = remoteWareService.orderLockStock(lockTo);
                if (r.getCode() == 200) {
                    //??????????????????
                    // int i = 1 / 0;
                    //TODO ???????????????????????????????????????mq
                    rabbitTemplate.convertAndSend("order.event.exchange", "order.create.order", order.getOrder());
                    responseVo.setOrder(order.getOrder());
                } else {
                    throw new RuntimeException("???????????????????????????");
                }
            } else {
                //????????????
                responseVo.setCode(501);
            }
            return responseVo;
        }
    }

    @Override
    public Order selectOrderByOrderSn(String orderSn) {
        return orderMapper.selectOrderByOrderSn(orderSn);
    }

    @Override
    public void closeOrder(Order order) {
        //???????????????????????????
        Order orderDb = selectOrderById(order.getId());
        if (orderDb.getStatus().equals(OrderStatusEnum.CREATE_NEW.getCode())) {
            //??????
            Order iOrder = new Order();
            iOrder.setId(orderDb.getId());
            iOrder.setOrderSn(orderDb.getOrderSn());
            iOrder.setStatus(OrderStatusEnum.CANCLED.getCode());
            updateOrder(iOrder);
            //???MQ?????????
            com.ruoyi.mall.api.vo.Order orderVo = new com.ruoyi.mall.api.vo.Order();
            BeanUtils.copyBeanProp(orderVo, iOrder);
            try {
                //TODO ????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
                //TODO ????????????????????????????????????????????????????????????
                rabbitTemplate.convertAndSend("order.event.exchange", "order.release.other", orderVo);
            } catch (Exception e) {
                //TODO ?????????????????????????????????????????????

            }
        }
    }

    @Override
    public PayVo getOrderPay(String orderSn) {
        PayVo payVo = new PayVo();
        Order order = selectOrderByOrderSn(orderSn);
        payVo.setOut_trade_no(orderSn);
        OrderItem orderItemSelect = new OrderItem();
        orderItemSelect.setOrderSn(orderSn);
        List<OrderItem> orderItems = orderItemMapper.selectOrderItemList(orderItemSelect);
        payVo.setSubject("???????????????");
        OrderItem orderItem = null;
        if (orderItems != null && orderItems.size() > 0) {
            orderItem = orderItems.get(0);
            payVo.setSubject(payVo.getSubject() + orderItem.getSkuName());
        }
        BigDecimal bigDecimal = order.getPayAmount().setScale(2, RoundingMode.UP);
        payVo.setTotal_amount(bigDecimal.toString());
        if (orderItem != null && StringUtils.isNotEmpty(orderItem.getSkuAttrsVals())) {
            payVo.setBody(orderItem.getSkuAttrsVals());
        }
        return payVo;
    }

    @Override
    public List<Order> selectOrderWithItem() {
        Member member = LoginUserInterceptor.loginUser.get();
        Order order = new Order();
        order.setMemberId(member.getId());
        List<Order> orders = selectOrderList(order);
        orders = orders.stream().map(o -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderSn(o.getOrderSn());
            List<OrderItem> orderItems = orderItemMapper.selectOrderItemList(orderItem);
            o.setItems(orderItems);
            return o;
        }).collect(Collectors.toList());
        return orders;
    }

    @Transactional
    @Override
    public String handlePayResult(PayAsyncVo vo) throws ParseException {
        //??????????????????
        PaymentInfo paymentInfo = new PaymentInfo();
        String orderSn = vo.getOut_trade_no();
        paymentInfo.setOrderSn(orderSn);
        paymentInfo.setAlipayTradeNo(vo.getTrade_no());
        paymentInfo.setSubject(vo.getSubject());
        String trade_status = vo.getTrade_status();
        paymentInfo.setPaymentStatus(trade_status);
        paymentInfo.setCreateTime(DateUtils.getNowDate());
        paymentInfo.setCallbackTime(DateUtils.parseDate(vo.getNotify_time(), "yyyy-MM-dd HH:mm:ss"));
        paymentInfoService.insertPaymentInfo(paymentInfo);

        //??????????????????????????????
        if (trade_status.equals("TRADE_SUCCESS") || trade_status.equals("TRADE_FINISHED")) {
            Order order = new Order();
            order.setOrderSn(orderSn);
            order.setStatus(OrderStatusEnum.PAYED.getCode());
            updateOrderByOrderSn(order);
        }
        return "success";
    }

    @Override
    public void updateOrderByOrderSn(Order order) {
        orderMapper.updateOrderByOrderSn(order);
    }

    @Override
    public void createSeckillOrder(SeckillOrderTo seckillOrder) {
        //TODO ??????????????????
        Order order = new Order();
        order.setOrderSn(seckillOrder.getOrderSn());
        order.setMemberId(seckillOrder.getMemberId());
        order.setStatus(OrderStatusEnum.CREATE_NEW.getCode());
        BigDecimal payAmount = seckillOrder.getSeckillPrice().multiply(new BigDecimal(seckillOrder.getNum()));
        order.setPayAmount(payAmount);
        insertOrder(order);
        //???????????????
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderSn(seckillOrder.getOrderSn());
        orderItem.setRealAmount(payAmount);
        //TODO ????????????sku????????????????????? remoteProductService.getSpuInfoBySkuId(skuId)
        orderItem.setSkuQuantity(seckillOrder.getNum().longValue());

        orderItemMapper.insertOrderItem(orderItem);
    }

    private void saveOrder(OrderCreateTo orderCreateTo) {
        Order order = orderCreateTo.getOrder();
        order.setCreateTime(DateUtils.getNowDate());
        insertOrder(order);
        Long orderId = order.getId();
        List<OrderItem> orderItems = orderCreateTo.getOrderItems();
        //TODO ????????????????????????
        for (OrderItem orderItem : orderItems) {
            orderItem.setOrderId(orderId);
            orderItemMapper.insertOrderItem(orderItem);
        }
    }

    private OrderCreateTo createOrder(Member member) {
        OrderCreateTo orderCreateTo = new OrderCreateTo();
        //1??????????????????
        String orderSn = IdUtils.fastSimpleUUID();
        Order order = buildOrder(member, orderSn);
        //2??????????????????????????????
        List<OrderItem> orderItems = buildOrderItems(orderSn);
        //??????????????????????????????
        compute(order, orderItems);
        orderCreateTo.setOrder(order);
        orderCreateTo.setOrderItems(orderItems);
        return orderCreateTo;
    }

    private void compute(Order order, List<OrderItem> items) {
        BigDecimal total = new BigDecimal(0);
        //?????????????????????????????????????????????????????????
        BigDecimal coupon = new BigDecimal(0);
        BigDecimal integration = new BigDecimal(0);
        BigDecimal promotion = new BigDecimal(0);
        BigDecimal gift = new BigDecimal(0);
        BigDecimal growth = new BigDecimal(0);
        for (OrderItem item : items) {
            coupon = coupon.add(item.getCouponAmount());
            integration = integration.add(item.getIntegrationAmount());
            promotion = promotion.add(item.getPromotionAmount());
            total = total.add(item.getRealAmount());
            gift= gift.add(new BigDecimal(item.getGiftIntegration()));
            growth = growth.add(new BigDecimal(item.getGiftGrowth()));
        }
        //1.??????????????????
        order.setTotalAmount(total);
        //??????????????????
        order.setPayAmount(total.add(order.getFreightAmount()));
        order.setPromotionAmount(promotion);
        order.setIntegrationAmount(integration);
        order.setCouponAmount(coupon);
        //?????????????????????
        order.setIntegration(gift.longValue());
        order.setGrowth(growth.longValue());
    }


    private Order buildOrder(Member member, String orderSn) {
        Order order = new Order();
        order.setOrderSn(orderSn);
        //2) ??????????????????
        order.setMemberId(member.getId());
        order.setMemberUsername(member.getUsername());
        //????????????????????????
        OrderSubmitVo submitVo = confirmVoThreadLocal.get();
        R<FareVo> r = remoteWareService.getFare(submitVo.getAddrId());
        if (r.getCode() == 200) {
            FareVo data = r.getData();
            MemberReceiveAddress address = data.getAddress();
            BigDecimal fare = data.getFare();
            //????????????
            order.setFreightAmount(fare);
            //?????????????????????
            order.setReceiverCity(address.getCity());
            order.setReceiverDetailAddress(address.getDetailAddress());
            order.setReceiverName(address.getName());
            order.setReceiverPhone(address.getPhone());
            order.setReceiverPostCode(address.getPostCode());
            order.setReceiverProvince(address.getProvince());
            order.setReceiverRegion(address.getRegion());

            //??????????????????
            order.setDeleteStatus(0);//?????????
            order.setStatus(OrderStatusEnum.CREATE_NEW.getCode());
            order.setAutoConfirmDay(7L);
        }
        return order;
    }


    //???????????????
    private List<OrderItem> buildOrderItems(String orderSn) {
        R<List<OrderItemVo>> r2 = remoteCartService.getCurrentUserCartItems();
        if (r2.getCode() == 200) {
            List<OrderItemVo> currentUserCartItems = r2.getData();
            if (currentUserCartItems != null && currentUserCartItems.size() > 0) {
                return currentUserCartItems.stream().map(cartItem -> {
                    OrderItem item = buildOrderItem(cartItem);
                    item.setOrderSn(orderSn);
                    return item;
                }).collect(Collectors.toList());
            }
        }
        return null;
    }

    //???????????????
    private OrderItem buildOrderItem(OrderItemVo cartItem) {
        OrderItem orderItem = new OrderItem();
        //1???????????????
        //2????????????spu??????
        Long skuId = cartItem.getSkuId();
        R<PmsSpuInfo> r = remoteProductService.getSpuInfoBySkuId(skuId);
        if (r.getCode() == 200) {
            PmsSpuInfo spuInfo = r.getData();
            orderItem.setSpuId(spuInfo.getId());
            orderItem.setSpuBrand(spuInfo.getBrandId().toString());
            orderItem.setSpuName(spuInfo.getSpuName());
            orderItem.setCategoryId(spuInfo.getCatalogId());
        }
        //3????????????sku??????
        orderItem.setSkuId(cartItem.getSkuId());
        orderItem.setSkuName(cartItem.getTitle());
        orderItem.setSkuPrice(cartItem.getPrice());
        orderItem.setSkuPic(cartItem.getImage());
        String skuAttr = StringUtils.join(cartItem.getSkuAttr(), ",");
        orderItem.setSkuAttrsVals(skuAttr);
        orderItem.setSkuQuantity(cartItem.getCount().longValue());
        //4???????????????????????????
        //5???????????????
        orderItem.setGiftGrowth(cartItem.getPrice().multiply(new BigDecimal(cartItem.getCount())).longValue());
        orderItem.setGiftIntegration(cartItem.getPrice().multiply(new BigDecimal(cartItem.getCount())).longValue());
        //6???????????????????????????
        orderItem.setPromotionAmount(new BigDecimal(0));
        orderItem.setCouponAmount(new BigDecimal(0));
        orderItem.setIntegrationAmount(new BigDecimal(0));
        //?????????????????????
        BigDecimal origin = orderItem.getSkuPrice().multiply(new BigDecimal(orderItem.getSkuQuantity()));
        ///????????????????????????
        BigDecimal subtract = origin.subtract(orderItem.getCouponAmount())
                .subtract(orderItem.getPromotionAmount())
                .subtract(orderItem.getIntegrationAmount());
        orderItem.setRealAmount(subtract);
        return orderItem;
    }
}
