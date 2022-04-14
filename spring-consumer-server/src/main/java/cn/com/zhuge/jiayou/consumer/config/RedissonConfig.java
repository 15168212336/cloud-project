package cn.com.zhuge.jiayou.consumer.config;

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
                .setAddress("redis://127.0.0.1:6379");
        RedissonClient redissonClient = Redisson.create(config);
        return redissonClient;
    }
}
