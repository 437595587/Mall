package com.ruoyi.mall.api.to;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class UserRegisterTo {
    private String username;
    private String password;
    private String phone;
}
