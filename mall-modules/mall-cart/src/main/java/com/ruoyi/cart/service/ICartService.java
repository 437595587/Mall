package com.ruoyi.cart.service;

import com.ruoyi.cart.domain.vo.Cart;
import com.ruoyi.cart.domain.vo.CartItem;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface ICartService {
    CartItem insertToCart(Long skuId, Integer num) throws ExecutionException, InterruptedException;

    CartItem selectCartItem(Long skuId);

    Cart selectCart() throws ExecutionException, InterruptedException;

    /**
     * 勾选购物项
     * @param skuId
     * @param check
     */
    void updateCheckItem(Long skuId, Integer check);

    /**
     * 修改购物项数量
     * @param skuId
     * @param num
     */
    void updateCountItem(Long skuId, Integer num);

    void deleteItem(Long skuId);

    List<CartItem> selectCurrentUseCartItem();
}
