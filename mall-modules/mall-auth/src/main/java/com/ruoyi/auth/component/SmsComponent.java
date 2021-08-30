package com.ruoyi.auth.component;

import com.ruoyi.auth.utils.HttpUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class SmsComponent {

    public void sendSmsCode(String phone, String code) throws Exception {
        String host = "https://gyytz.market.alicloudapi.com";
        String path = "/sms/smsSend";
        String method = "POST";
        String appcode = "fc5003fc6c61468db612ab88302158ea";
        Map<String, String> headers = new HashMap<>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<>();
        querys.put("param", "**code**:" + code + ",**minute**:5");
        querys.put("mobile", phone);
        querys.put("smsSignId", "2e65b1bb3d054466b82f0c9d125465e2");
        querys.put("templateId", "908e94ccf08b4476ba6c876d13f084ad");
        //JDK 1.8示例代码请在这里下载：  http://code.fegine.com/Tools.zip
        HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, "");
        //System.out.println(response.toString());如不输出json, 请打开这行代码，打印调试头部状态码。
        //状态码: 200 正常；400 URL无效；401 appCode错误； 403 次数用完； 500 API网管错误
        //获取response的body
        System.out.println(EntityUtils.toString(response.getEntity()));
    }
}
