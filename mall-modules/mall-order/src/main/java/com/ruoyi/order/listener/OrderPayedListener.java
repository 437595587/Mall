package com.ruoyi.order.listener;

import com.alipay.api.internal.util.AlipaySignature;
import com.ruoyi.order.config.pay.AlipayTemplate;
import com.ruoyi.order.domain.vo.PayAsyncVo;
import com.ruoyi.order.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@RestController
public class OrderPayedListener {

    @Autowired
    private IOrderService orderService;

    @Autowired
    private AlipayTemplate alipayTemplate;

    @PostMapping("/payed/notify")
    public String handleAliPayed(PayAsyncVo vo, HttpServletRequest request) {
        String result = "success";
        try {
            //验签
            //获取支付宝POST过来反馈信息
            Map<String,String> params = new HashMap<String,String>();
            Map<String,String[]> requestParams = request.getParameterMap();
            for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
                String name = (String) iter.next();
                String[] values = (String[]) requestParams.get(name);
                String valueStr = "";
                for (int i = 0; i < values.length; i++) {
                    valueStr = (i == values.length - 1) ? valueStr + values[i]
                            : valueStr + values[i] + ",";
                }
                //乱码解决，这段代码在出现乱码时使用
                // valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
                params.put(name, valueStr);
            }

            boolean signVerified = AlipaySignature.rsaCheckV1(params, alipayTemplate.getAlipay_public_key(), alipayTemplate.getCharset(), alipayTemplate.getSign_type()); //调用SDK验证签名
            if (signVerified) {
                result = orderService.handlePayResult(vo);
            } else {
                return "error";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return result;
    }
}
