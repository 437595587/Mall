package com.ruoyi.seckill;

import com.ruoyi.common.security.annotation.EnableCustomConfig;
import com.ruoyi.common.security.annotation.EnableRyFeignClients;
import com.ruoyi.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * 1、整合sentinel
 *      1）、导入依赖 spring-cloud-starter-alibaba-sentinel
 *      2）、下载sentinel的控制台
 *      3）、配置sentinel控制台地址信息
 *      4）、在控制台设置参数，【默认所有的流控设置保存在内存中，重启失效】
 * 2、每一个微服务都导入actuator；配合management.endpoints.web.exposure.include=*
 * 3、自定义sentinel流控返回数据
 *
 * 4、使用sentinel来保护feign远程调用：熔断；
 *      1）、调用方熔断保护： feign.sentinel.enabled=true
 *      2）、调用方手动指定远程服务的降级策略。远程服务被降级处理。触发熔断回调方法
 *      3）、超大流量的时候，必须牺牲一些远程服务。在服务的提供方（远程服务）指定降级策略；
 *          提供方是在运行。但是不运行业务逻辑，只返回降级数据（限流的数据）
 *
 * 5、自定义受保护资源
 *      1）、代码
 *          try (Entry entry = SphU.entry("seckillSkus")) {
 *              //业务逻辑
 *          } catch (BlockException e) {}
 *      2)、注解
 *          @SentinelResource(value = "selectCurrentSeckillSkus", blockHandler = "blockHandler")
 *
 *          无论是1，2一定要配置被限流后的默认返回
 *          url请求可以设置统一返回 BlockExceptionHandler
 *
 */
@EnableRedisHttpSession
@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class MallSeckillApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallSeckillApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  秒杀模块启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
                " .-------.       ____     __        \n" +
                " |  _ _   \\      \\   \\   /  /    \n" +
                " | ( ' )  |       \\  _. /  '       \n" +
                " |(_ o _) /        _( )_ .'         \n" +
                " | (_,_).' __  ___(_ o _)'          \n" +
                " |  |\\ \\  |  ||   |(_,_)'         \n" +
                " |  | \\ `'   /|   `-'  /           \n" +
                " |  |  \\    /  \\      /           \n" +
                " ''-'   `'-'    `-..-'              ");

    }
}
