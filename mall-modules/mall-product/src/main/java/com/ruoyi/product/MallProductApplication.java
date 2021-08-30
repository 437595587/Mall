package com.ruoyi.product;

import com.ruoyi.common.security.annotation.EnableCustomConfig;
import com.ruoyi.common.security.annotation.EnableRyFeignClients;
import com.ruoyi.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * 页面修改不重启服务器实时更新
 * 1)、引入dev-tools
 * 2)、修改完页面 ctrl+shift+f9重新编译当前页面
 * 3)、代码配置、推荐重启
 *
 * 整合SpringCache简化缓存开发
 *  自动配置：CacheAutoConfiguration会导入 RedisCacheConfiguration
 *  自动配好了缓存管理器RedisCacheManager
 * @Cacheable 触发将数据保存到缓存的操作
 * @CacheEvit 触发将数据从缓存删除的操作
 * @CachePut 不影响方法执行更新缓存
 * @Caching 组合以上多个缓存操作
 * @CacheConfig 在类级别共享缓存的相同配置
 * 只需要使用注解就能完成缓存操作
 *
 * SpringCache的不足：
 *      1)、读模式：
 *           缓存穿透： 查询一个null数据。解决：缓存空数据;spring.cache.redis.cache-null-values=true
 *           缓存击穿:  大量并发进来查询一个正好过期的数据。解决：加锁 默认无加锁的  sync = true 加锁解决击穿 本地锁
 *           缓存雪崩： 大量的key同时过期。 解决： 加随机时间。加上过期时间。spring.cache.redis.time-to-live=3600000
 *      2)、写模式：
 *           1)、读写加锁
 *           2)、引入canal，感知到mysql的更新去更新缓存
 *           3)、读多写多，直接去数据库查询就行
 *
 *  总结：
 *      常规数据（读多写少，即时性，一致性要求不高的数据）；完全可以使用SpringCache 写模式（只要缓存的数据有过期时间就足够了）
 *
 *      特殊数据：特殊设计
 *
 *  原理：
 *      CacheManager(RedisCacheManager)->Cache(RedisCache)->Cache负责缓存的读写
 *
 */
@EnableRedisHttpSession
@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
@SpringBootApplication
public class MallProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallProductApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  商品模块启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
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
