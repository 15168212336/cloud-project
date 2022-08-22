package cn.com.zhuge.jiayou.common.redisson.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {

    @Bean
    public RedissonClient initRedis() {
        Config config = new Config();
        config.useSingleServer()
                // use "rediss://" for SSL connection
                .setAddress("redis://182.61.35.198:6379")
                .setPassword("admin");
        RedissonClient redissonClient = Redisson.create(config);
        return redissonClient;
    }
}
