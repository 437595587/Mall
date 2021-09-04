package com.ruoyi.order.config.pay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.ruoyi.order.domain.vo.PayVo;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */
@ConfigurationProperties(prefix = "alipay")
@Component
@Data
public class AlipayTemplate {


//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public String app_id = "2016102300742177";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDQgdRQGVbxi+be61FNdG6midaWDXfS9Sxi80yHew9BO5vw7HYGu/HMsrxAuJGHDyUYl20RVUSG3KaCVdN1Qzog/4+8SbmViyxz1Vj7vz8ee/KXnydXDwMIsjE2HS3dllNNEoHHK3mGn6q5FkbCNcnc8SSMR9OZCHAq7GYLWCYZM+Nm8PhJpCZmSEMxlwz+wponQ2WARHBeDngQ35t1QuOJ3AXrASLFDsXWOUEsHa7iWpklBJ6ZIW6KMbtsYLB2s6iifR5pk0STT9JOd2TnHFKfLgFffInu70M4t4iJy2FQULp75M1AKLrOFQMuFLqmwhDxmuAQc09jwWmOI8SZwIIZAgMBAAECggEARdJUAIabwMMyio+OaDuHGimqPBFCgNgtBu2mQPAKibOmYqUsWNDzBbMMqzejM/D0YSnEEjvVeLvG3npTFz9t5cLupQSjKtqyBg/5fkPqpRCoifF3wQV75JuXMyHOTBecyYBbCREb3uL0zooRI9PTRzNRI9ChoOtcdGM5QK9CAsnsoGdYLN5H6TF2JmVYNC6AReUDKYZNM2X+wPjMPjzCMIbsvIxFUZ2fl+OPibK5onBrF4QBQaKRP+w23IrWbMvp1AewYMgzYBOJhQG4Z+Wo5Xt0PjaXh6Hb76TzfvmQZUUAyuPBPMCcEaR6m/mPW5KcO9wP2e0zCAKWLQAyalDSyQKBgQDtS9w5QZuUvYwXsYnqq0e/Isi9BlTfE29xSmOLfGkpzO8a1ZoX5f3BiF62zUTHh7bJImnzIuTD6wEPELoovFXUHcgMxCc8mC1DAH3r5KhGC+MjYTBgteJfLKzagGXx4GVecVuiJSQUb/K5isf9PdOXVcNVJswwWXqSNHNOm/rJVwKBgQDg8RBma+1bbEtajvcOkjS+XRhDYZXm5IndSIf+QKYFc7/mZoJXEYSsPTS3tZXKhyckfSTSK6cuUF1FZNiVxuQKUWkIHz6VH8yR9TZ20i7L5CxmwfTXAV5pLHu/O1VYTrRYwLyWEVCOW7ODj5t2iyI5JHt2Z+EP0JwET+LoO3c6DwKBgQDBbxha+BdKHWVInynMyMiYj1NRewrOOXqbwEb7GnRjZbH3ju2o04uSKocCvrt1ZzTiKpJxZx1Sc4wOatVFhZj1az164p8VbvteZdU/6MofJ2uFmKEmBL4+VR2mlAoGLKSVYxSOTXZmzobvJqk1zyNfdsLe89x5AUfATM894jqBywKBgCncMpUc2Jr+2DcOC+PmSXJzmKRRcLf7J7zKVqR2G29ATyHtSawyUmJLMZhOboW2CQ69SzDhdscjpXfYgIW+8YkKBWXBir/tnwA6hdwu2Q6Qc8LbrVilpJAl+xFnVUaNvvoVRAMZTchwQEPnYaL52XCQ3Xj3c+ahh6c1xqA5EML5AoGALlrwEdc7q7MCCRFeJoEDnoiOQ4GPvxxnEPI20p+rbDBGCJh3WH77U62O3xtZQ73SuRWMDv86bGX5Pjo+l1SUtkTpfdGJqC2eaLgql3Q/AK4M3HiuI6AzksvuxCc1ihsK8cxMkckGOpl5+qPgnHO0l5IOsfOW+uEFk1FUhiH0Aqk=";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAj1WbZPdFkDWhswonPck00QUi7QmdNvtbvIWpdVRIbJ0ihg+fPUQMw2usCZu3BQm2X+M169Ddbk+L20jtbc3oJUE9f8GQqsDE5eLpJhdGeGeCo0Ufh4QnL+guqPHNnvMSLxHugiS9drG0UeL9HF3vJ+y4egRdZR9TOp1i//RrsjdzkBzF7iS23xXljcBlxpLOsPexz0s5VpdXWyCa2C4heVY1IYuJhh3jFE+qr1a9KMmwgGdvPmwCF/dEgbRlcF5bJFg0I2ydZifyfM8oxPWD2tWw8xeLnVdGeHztTbqHN25sl5MGuJEwBsBC+zvyinnMhf9NefeklfPiUVGM2MVs5wIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public String notify_url = "http://2bxi93.natappfree.cc/payed/notify";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public String return_url = "http://member.mall.com/memberOrder.html";

    // 签名方式
    public String sign_type = "RSA2";

    // 字符编码格式
    public String charset = "utf-8";

    // 支付宝网关
    public String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public String log_path = "C:\\";

    private String timeout = "29m";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    public  String pay(PayVo vo) throws AlipayApiException {
        //1、根据支付宝的配置生成一个支付客户端
        AlipayClient alipayClient = new DefaultAlipayClient(gatewayUrl,
                app_id, merchant_private_key, "json",
                charset, alipay_public_key, sign_type);

        //2、创建一个支付请求 //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(return_url);
        alipayRequest.setNotifyUrl(notify_url);

        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = vo.getOut_trade_no();
        //付款金额，必填
        String total_amount = vo.getTotal_amount();
        //订单名称，必填
        String subject = vo.getSubject();
        //商品描述，可空
        String body = vo.getBody();

        alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\","
                + "\"total_amount\":\"" + total_amount + "\","
                + "\"subject\":\"" + subject + "\","
                + "\"body\":\"" + body + "\","
                + "\"timeout_express\":\"" + timeout + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        String result = alipayClient.pageExecute(alipayRequest).getBody();

        //会收到支付宝的响应，响应的是一个页面，只要浏览器显示这个页面，就会自动来到支付宝的收银台页面
        // System.out.println("支付宝的响应："+result);

        return result;

    }
}
