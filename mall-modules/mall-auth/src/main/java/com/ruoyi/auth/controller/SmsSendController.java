package com.ruoyi.auth.controller;

import com.ruoyi.auth.component.SmsComponent;
import com.ruoyi.auth.constant.AuthConstant;
import com.ruoyi.common.core.exception.BizCodeEnum;
import com.ruoyi.common.core.text.UUID;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.web.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/sms")
public class SmsSendController {

    @Autowired
    private SmsComponent smsComponent;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping("/sendCode")
    public AjaxResult sendCode(@RequestParam("phone") String phone) throws Exception {
        //TODO 1. 接口防刷
        String redisCode = redisTemplate.opsForValue().get(AuthConstant.SMS_CODE_CACHE_PREFIX + phone);
        if (StringUtils.isNotEmpty(redisCode)) {
            long l = Long.parseLong(redisCode.split("_")[1]);
            if (System.currentTimeMillis() - l < 60000) {
                //60秒不能再发
                return AjaxResult.error(BizCodeEnum.SMS_CODE_EXCEPTION.getCode(), BizCodeEnum.SMS_CODE_EXCEPTION.getMsg());
            }
        }
        //2. redis 存 key-phone value-code
        String code = UUID.randomUUID().toString().substring(0, 5);
        smsComponent.sendSmsCode(phone, code);
        code = code + "_" + System.currentTimeMillis();
        redisTemplate.opsForValue().set(AuthConstant.SMS_CODE_CACHE_PREFIX + phone, code, 10, TimeUnit.MINUTES);
        return AjaxResult.success();
    }
}
