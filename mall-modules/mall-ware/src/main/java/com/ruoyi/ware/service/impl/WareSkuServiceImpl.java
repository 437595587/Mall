package com.ruoyi.ware.service.impl;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.utils.bean.BeanUtils;
import com.ruoyi.mall.api.RemoteOrderService;
import com.ruoyi.mall.api.to.WareSkuLockTo;
import com.ruoyi.mall.api.vo.Order;
import com.ruoyi.mall.api.vo.OrderItemVo;
import com.ruoyi.mall.common.core.to.mq.StockDetailTo;
import com.ruoyi.mall.common.core.to.mq.StockLockedTo;
import com.ruoyi.ware.domain.WareOrderTask;
import com.ruoyi.ware.domain.WareOrderTaskDetail;
import com.ruoyi.ware.domain.WareSku;
import com.ruoyi.ware.domain.vo.SkuHasStockVo;
import com.ruoyi.ware.mapper.WareSkuMapper;
import com.ruoyi.ware.service.IWareOrderTaskDetailService;
import com.ruoyi.ware.service.IWareOrderTaskService;
import com.ruoyi.ware.service.IWareSkuService;
import lombok.Data;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 商品库存Service业务层处理
 *
 * @author xuxing
 * @date 2021-08-23
 */
@Service
public class WareSkuServiceImpl implements IWareSkuService
{
    @Autowired
    private WareSkuMapper wareSkuMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private IWareOrderTaskService wareOrderTaskService;

    @Autowired
    private IWareOrderTaskDetailService wareOrderTaskDetailService;

    @Autowired
    private RemoteOrderService remoteOrderService;

    /**
     * 查询商品库存
     *
     * @param id 商品库存主键
     * @return 商品库存
     */
    @Override
    public WareSku selectWareSkuById(Long id)
    {
        return wareSkuMapper.selectWareSkuById(id);
    }

    /**
     * 查询商品库存列表
     *
     * @param wareSku 商品库存
     * @return 商品库存
     */
    @Override
    public List<WareSku> selectWareSkuList(WareSku wareSku)
    {
        return wareSkuMapper.selectWareSkuList(wareSku);
    }

    /**
     * 新增商品库存
     *
     * @param wareSku 商品库存
     * @return 结果
     */
    @Override
    public int insertWareSku(WareSku wareSku)
    {
        return wareSkuMapper.insertWareSku(wareSku);
    }

    /**
     * 修改商品库存
     *
     * @param wareSku 商品库存
     * @return 结果
     */
    @Override
    public int updateWareSku(WareSku wareSku)
    {
        return wareSkuMapper.updateWareSku(wareSku);
    }

    /**
     * 批量删除商品库存
     *
     * @param ids 需要删除的商品库存主键
     * @return 结果
     */
    @Override
    public int deleteWareSkuByIds(Long[] ids)
    {
        return wareSkuMapper.deleteWareSkuByIds(ids);
    }

    /**
     * 删除商品库存信息
     *
     * @param id 商品库存主键
     * @return 结果
     */
    @Override
    public int deleteWareSkuById(Long id)
    {
        return wareSkuMapper.deleteWareSkuById(id);
    }

    @Override
    public List<SkuHasStockVo> selectSkusHasStock(List<Long> skuIds) {
        return skuIds.stream().map(skuId -> {
            SkuHasStockVo vo = new SkuHasStockVo();
            Long count = wareSkuMapper.selectSkuHasStock(skuId);
            vo.setSkuId(skuId);
            vo.setHasStock(count != null && count > 0);
            return vo;
        }).collect(Collectors.toList());
    }

    /** 库存解锁场景
     *      1）、下单成功，订单过期、被用户手动取消、 需要解锁库存
     *      2）、下单成功，业务调用失败，导致订单回滚 之前锁定的库存需要解锁
     * @param to 订单项锁定库存To
     * @return 结果
     */
    @Transactional
    @Override
    public boolean orderLockStock(WareSkuLockTo to) {
        //保存库存工作单详情 追溯
        WareOrderTask task = new WareOrderTask();
        task.setOrderSn(to.getOrderSn());

        wareOrderTaskService.insertWareOrderTask(task);
        List<OrderItemVo> locks = to.getLocks();
        List<SkuWareHasStock> skuWareHasStocks = locks.stream().map(item -> {
            SkuWareHasStock stock = new SkuWareHasStock();
            Long skuId = item.getSkuId();
            stock.setSkuId(skuId);
            stock.setNum(item.getCount().longValue());
            List<Long> wareIds = wareSkuMapper.listWareIdHasSkuStock(skuId);
            stock.setWareIds(wareIds);
            return stock;
        }).collect(Collectors.toList());
        for (SkuWareHasStock hasStock : skuWareHasStocks) {
            boolean skuStocked = false;
            Long skuId = hasStock.getSkuId();
            List<Long> wareIds = hasStock.getWareIds();
            if (wareIds == null || wareIds.size() == 0) {
                throw new RuntimeException("商品id：" + skuId + "没有足够的库存");
            }
            //1、如果每一个商品都锁定成功，将当前商品锁定了几件的工作单记录发给MQ
            //2、锁定失败。前面保存信息就回滚了。
            //防止回滚以后找不到数据
            for (Long wareId : wareIds) {
                int count = wareSkuMapper.lockSkuStock(skuId, wareId, hasStock.getNum());
                if (count > 0) {
                    skuStocked = true;
                    //TODO 告诉mq库存锁定成功
                    WareOrderTaskDetail taskDetail = new WareOrderTaskDetail();
                    taskDetail.setSkuId(skuId);
                    taskDetail.setSkuName("");
                    taskDetail.setSkuNum(hasStock.getNum());
                    taskDetail.setTaskId(task.getId());
                    taskDetail.setWareId(wareId);
                    taskDetail.setLockStatus(1);
                    wareOrderTaskDetailService.insertWareOrderTaskDetail(taskDetail);
                    StockLockedTo lockedTo = new StockLockedTo();
                    lockedTo.setId(task.getId());
                    StockDetailTo detailTo = new StockDetailTo();
                    BeanUtils.copyBeanProp(detailTo, taskDetail);
                    lockedTo.setDetailTo(detailTo);
                    rabbitTemplate.convertAndSend("stock.event.exchange", "stock.locked", lockedTo);
                    break;
                } else {
                    //当前仓库锁失败 重试下一个仓库

                }
            }
            if (!skuStocked) {
                throw new RuntimeException("商品id：" + skuId + "没有足够的库存");
            }
        }
        //锁定成功过
        return true;
    }

    @Data
    static class SkuWareHasStock {
        private Long skuId;
        private Long num;
        private List<Long> wareIds;
    }

    @Transactional
    @Override
    public void unlockStock(StockLockedTo to) {
        StockDetailTo detail = to.getDetailTo();
        Long detailId = detail.getId();
        //关于锁定库存信息
        WareOrderTaskDetail wareOrderTaskDetail = wareOrderTaskDetailService.selectWareOrderTaskDetailById(detailId);
        if (wareOrderTaskDetail != null) {
            //有 回滚库存
            Long id = to.getId();
            WareOrderTask wareOrderTask = wareOrderTaskService.selectWareOrderTaskById(id);
            String orderSn = wareOrderTask.getOrderSn();
            R<Order> r = remoteOrderService.getOrderStatus(orderSn);
            if (r.getCode() == 200) {
                Order data = r.getData();
                if (data == null || data.getStatus() == 4) {
                    //订单不存在或订单取消
                    //当前状态1才可以解锁
                    if (wareOrderTaskDetail.getLockStatus() == 1) {
                        unlockStock(detail.getSkuId(), detail.getWareId(), detail.getSkuNum(), detailId);
                    }
                }
            } else {
                //消息拒绝以后 重新入队让别人继续解锁
                throw new RuntimeException("远程服务失败");
            }
        } else {
            //没有 不需要回滚
        }
    }

    //解决订单服务卡顿库存无法解锁
    @Transactional
    @Override
    public void unlockStock(Order order) {
        String orderSn = order.getOrderSn();
        WareOrderTask task = wareOrderTaskService.selectOrderTaskByOrderSn(orderSn);
        Long taskId = task.getId();
        WareOrderTaskDetail wareOrderTaskDetail = new WareOrderTaskDetail();
        wareOrderTaskDetail.setTaskId(taskId);
        wareOrderTaskDetail.setLockStatus(1);
        List<WareOrderTaskDetail> wareOrderTaskDetails = wareOrderTaskDetailService.selectWareOrderTaskDetailList(wareOrderTaskDetail);
        for (WareOrderTaskDetail taskDetail : wareOrderTaskDetails) {
            //当前状态1才可以解锁
            if (taskDetail.getLockStatus() == 1) {
                unlockStock(taskDetail.getSkuId(), taskDetail.getWareId(), taskDetail.getSkuNum(), taskDetail.getId());
            }
        }
    }

    private void unlockStock(Long skuId, Long wareId, Long num, Long taskDetailId) {
        //库存解锁
        wareSkuMapper.unlockStock(skuId, wareId, num, taskDetailId);
        //更新库存工作单状态
        WareOrderTaskDetail wareOrderTaskDetail = new WareOrderTaskDetail();
        wareOrderTaskDetail.setId(taskDetailId);
        wareOrderTaskDetail.setLockStatus(2);
        wareOrderTaskDetailService.updateWareOrderTaskDetail(wareOrderTaskDetail);
    }
}
