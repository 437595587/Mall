package com.ruoyi.cart.service.impl;

import com.ruoyi.cart.domain.vo.Cart;
import com.ruoyi.cart.domain.vo.CartItem;
import com.ruoyi.cart.domain.vo.UserInfoTo;
import com.ruoyi.cart.interceptor.CartInterceptor;
import com.ruoyi.cart.service.ICartService;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.mall.api.RemoteProductService;
import com.ruoyi.mall.api.vo.PmsSkuInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements ICartService {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Autowired
    private RemoteProductService remoteProductService;

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    private final String CART_PREFIX = "mall:cart:";

    @Override
    public CartItem insertToCart(Long skuId, Integer num) throws ExecutionException, InterruptedException {
        BoundHashOperations<Object, Object, Object> cartOps = getCartOps();
        CartItem cartItemRed = (CartItem) cartOps.get(skuId.toString());
        //无商品时 添加新商品到购物车
        if (cartItemRed == null) {
            //1.远程查询要添加的商品信息
            CartItem cartItem = new CartItem();
            CompletableFuture<Void> getSkuInfoTask = CompletableFuture.runAsync(() -> {
                R<PmsSkuInfo> r = remoteProductService.getSkuInfo(skuId);
                if (r.getCode() == 200) {
                    PmsSkuInfo data = r.getData();
                    cartItem.setCheck(true);
                    cartItem.setCount(num);
                    cartItem.setImage(data.getSkuDefaultImg());
                    cartItem.setTitle(data.getSkuTitle());
                    cartItem.setSkuId(skuId);
                    cartItem.setPrice(data.getPrice());
                }
            }, taskExecutor);
            //2. 远程查询sku属性信息
            CompletableFuture<Void> getSkuSaleAttrValuesTask = CompletableFuture.runAsync(() -> {
                R<List<String>> r = remoteProductService.getSkuSaleAttrValues(skuId);
                if (r.getCode() == 200) {
                    List<String> data = r.getData();
                    cartItem.setSkuAttr(data);
                }
            }, taskExecutor);
            CompletableFuture.allOf(getSkuInfoTask, getSkuSaleAttrValuesTask).get();
            cartOps.put(skuId.toString(), cartItem);
            return cartItem;
        } else {
            //购物车有此商品 修改数量
            cartItemRed.setCount(cartItemRed.getCount() + num);
            cartOps.put(skuId.toString(), cartItemRed);
            return cartItemRed;
        }
    }

    @Override
    public CartItem selectCartItem(Long skuId) {
        BoundHashOperations<Object, Object, Object> cartOps = getCartOps();
        return (CartItem) cartOps.get(skuId.toString());
    }

    @Override
    public Cart selectCart() throws ExecutionException, InterruptedException {
        UserInfoTo userInfoTo = CartInterceptor.threadLocal.get();
        Cart cart = new Cart();
        if (userInfoTo.getUserId() != null) {
            //登录
            String cartKey = CART_PREFIX + userInfoTo.getUserId();
            //如果临时购物车的数据还没有进行合并 进行合并并且清空
            //合并
            String tempCartKey = CART_PREFIX + userInfoTo.getUserKey();
            List<CartItem> tempCartItems = selectCartItems(tempCartKey);
            if (tempCartItems != null && tempCartItems.size() > 0) {
                for (CartItem item : tempCartItems) {
                    insertToCart(item.getSkuId(), item.getCount());
                }
                //清空临时购物车
                clearCart(tempCartKey);
            }
            //获取登陆后的购物车数据 合并后的数据
            List<CartItem> cartItems = selectCartItems(cartKey);
            cart.setItems(cartItems);
        } else {
            //没登录
            String cartKey = CART_PREFIX + userInfoTo.getUserKey();
            List<CartItem> cartItems = selectCartItems(cartKey);
            cart.setItems(cartItems);
        }
        return cart;
    }

    @Override
    public void updateCheckItem(Long skuId, Integer check) {
        BoundHashOperations<Object, Object, Object> cartOps = getCartOps();
        CartItem cartItem = selectCartItem(skuId);
        cartItem.setCheck(check == 1);
        cartOps.put(skuId.toString(), cartItem);
    }

    @Override
    public void updateCountItem(Long skuId, Integer num) {
        CartItem cartItem = selectCartItem(skuId);
        cartItem.setCount(num);
        BoundHashOperations<Object, Object, Object> cartOps = getCartOps();
        cartOps.put(skuId.toString(), cartItem);
    }

    @Override
    public void deleteItem(Long skuId) {
        BoundHashOperations<Object, Object, Object> cartOps = getCartOps();
        cartOps.delete(skuId.toString());
    }

    @Override
    public List<CartItem> selectCurrentUseCartItem() {
        UserInfoTo userInfoTo = CartInterceptor.threadLocal.get();
        if (userInfoTo.getUserId() != null) {
            String cartKey = CART_PREFIX + userInfoTo.getUserId();
            List<CartItem> cartItems = selectCartItems(cartKey);
            if (cartItems != null && cartItems.size() > 0) {
                cartItems = cartItems.stream().filter(CartItem::getCheck)
                        .map(item->{
                            R<BigDecimal> r = remoteProductService.getSkuPrice(item.getSkuId());
                            //TODO 1 更新为最新价格
                            if (r.getCode() == 200) {
                                item.setPrice(r.getData());
                            }
                            return item;
                        }).collect(Collectors.toList());
                return cartItems;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    private List<CartItem> selectCartItems(String cartKey) {
        BoundHashOperations<Object, Object, Object> hashOps = redisTemplate.boundHashOps(cartKey);
        List<Object> values = hashOps.values();
        if (values != null && values.size() > 0) {
            return values.stream().map(obj -> (CartItem) obj).collect(Collectors.toList());
        } else {
            return null;
        }
    }

    private BoundHashOperations<Object, Object, Object> getCartOps() {
        UserInfoTo userInfoTo = CartInterceptor.threadLocal.get();
        String cartKey = "";
        if (userInfoTo.getUserId() != null) {
            cartKey = CART_PREFIX + userInfoTo.getUserId();
        } else {
            cartKey = CART_PREFIX + userInfoTo.getUserKey();
        }
        return redisTemplate.boundHashOps(cartKey);
    }

    private void clearCart(String cartKey) {
        redisTemplate.delete(cartKey);
    }
}
