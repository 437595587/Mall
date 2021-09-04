package com.ruoyi.cart.controller;

import com.ruoyi.cart.domain.vo.Cart;
import com.ruoyi.cart.domain.vo.CartItem;
import com.ruoyi.cart.service.ICartService;
import com.ruoyi.common.core.domain.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Controller
public class CartController {

    @Autowired
    private ICartService cartService;

    @ResponseBody
    @GetMapping("/currentUserCartItems")
    public R<List<CartItem>> getCurrentUserCartItems() {
        return R.ok(cartService.selectCurrentUseCartItem());
    }

    @GetMapping("/deleteItem")
    public String deleteItem(@RequestParam("skuId") Long skuId) {
        cartService.deleteItem(skuId);
        return "redirect:http://cart.mall.com/cart.html";
    }

    @GetMapping("/countItem")
    public String countItem(@RequestParam("skuId") Long skuId, @RequestParam("num") Integer num) {
        cartService.updateCountItem(skuId, num);
        return "redirect:http://cart.mall.com/cart.html";
    }

    @GetMapping("/checkItem")
    public String checkItem(@RequestParam("skuId") Long skuId, @RequestParam("check") Integer check) {
        cartService.updateCheckItem(skuId, check);
        return "redirect:http://cart.mall.com/cart.html";
    }

    /**
     * 浏览器cookie:user-key：表示用户身份，一个月后过期；
     * 如果第一次使用购物车功能，都会给一个临时的用户身份
     *
     * 登录：session有
     * 没登录：按照cookie里面带来的user-key
     * 第一次：如果没有临时用户，创建一个临时用户
     *
     * @return
     */
    @GetMapping("/cart.html")
    public String cartListPage(Model model) throws ExecutionException, InterruptedException {
        Cart cart = cartService.selectCart();
        model.addAttribute("cart", cart);
        return "cartList";
    }

    /**
     *
     * RedirectAttributes redirectAttributes
     * redirectAttributes.addFlashAttribute() 将数据放在session里面可以在页面取出，但是只能取一次
     * redirectAttributes.addAttribute() 将数据放在url后面
     * @param skuId
     * @param num
     * @param redirectAttributes
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @GetMapping("/add")
    public String addToCart(@RequestParam("skuId") Long skuId, @RequestParam("num") Integer num, RedirectAttributes redirectAttributes) throws ExecutionException, InterruptedException {
        CartItem cartItem = cartService.insertToCart(skuId, num);
        redirectAttributes.addAttribute("skuId", skuId);
        return "redirect:http://cart.mall.com/addToCartSuccess.html";
    }

    @GetMapping("/addToCartSuccess.html")
    public String addToCartSuccess(@RequestParam("skuId") Long skuId, Model model) {
        //再次查询购物车数据
        CartItem item = cartService.selectCartItem(skuId);
        model.addAttribute("item", item);
        return "success";
    }
}
