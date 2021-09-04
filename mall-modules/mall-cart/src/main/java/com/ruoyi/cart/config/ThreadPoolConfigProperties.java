package com.ruoyi.cart.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "mall.thread")
@Component
@Data
public class ThreadPoolConfigProperties {
    //核心线程数（默认线程数）
    private Integer corePoolSize = 20;
    //最大线程数
    private Integer maxPoolSize = 200;
    //允许线程空闲时间（单位：默认为秒）
    private Integer keepAliveTime = 10;
    //缓冲队列数
    private Integer queueCapacity = 200;
    //线程池名前缀
    private String threadNamePrefix = "Async-Service-";
}
