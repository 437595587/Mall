package com.ruoyi.auth.domain.vo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class UserRegisterVo {
    @NotBlank(message = "用户名必须填写")
    private String username;
    @Length(min = 6, max = 20, message = "密码必须是6-20位字符")
    @NotBlank(message = "密码必须填写")
    private String password;
    @NotBlank(message = "手机号必须填写")
    @Pattern(regexp = "^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\\d{8}$",message = "手机号格式不正确")
    private String phone;
    @NotBlank(message = "验证码必须填写")
    private String code;
}
