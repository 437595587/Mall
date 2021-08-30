package com.ruoyi.mall.api.to;

import lombok.Data;

@Data
public class SocialUser {
    private String access_token;
    private Long id;
    private String login;
    private String name;
    private String avatar_url;
    private String email;
}
