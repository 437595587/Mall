package com.ruoyi.order;

import com.ruoyi.common.security.annotation.EnableCustomConfig;
import com.ruoyi.common.security.annotation.EnableRyFeignClients;
import com.ruoyi.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @RabbitListener： 类+方法上 监听哪些队列
 * @RabbitHandle：  标在方法上 重载区分不同的消息
 *
 * 本地事务失效：
 * 同一个对象内事务方法互相调用 默认失效 原因：绕过了代理对象，事务使用代理对象来控制的
 * 解决：使用代理对象来解决
 *
 * 开启注解@EnableAspectJAutoProxy(exposeProxy = true)
 * ServiceImpl service=(ServiceImpl) AopContext.currentProxy()
 * service.a()
 * service.b()
 * service.c()
 *
 * Seata控制分布式事务
 * 1、每一个微服务必须创建undo_log表
 * 2、导入依赖 spring-cloud-starter-alibaba-seata
 * 3、解压并启动seata-server
 *      registry.conf
 *      file.conf
 * 4、分布式事务的微服务使用seata DataSourceProxy代理自己的数据源
 * 5、每个微服务 导入 registry.conf、file.conf 可以再application.properties中配置
 * 6、分布式事务标注@GlobalTransactional
 * 7、本地事务用@Transactional
 */
@EnableRabbit
@EnableRedisHttpSession
@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
@SpringBootApplication
public class MallOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallOrderApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  订单模块启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
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
