package cn.com.zhuge.jiayou.common.redisson.annotation;

import cn.com.zhuge.jiayou.common.redisson.context.LockType;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface RedisLock {

    /**
     * 锁的key
     * 如果想添加非固定锁，可以在参数上添加@RedisLock注解，本参数是必写选项<br/>
     * redis key的拼写规则为 "DistRedisLock+" + lockKey + @RedisLOckKey<br/>
     */
    String lockKey();

    /**
     * 持锁时间
     * 单位毫秒,默认10秒<br/>
     */
    long keepMills() default 60 * 1000;

    /**
     * 没有获取到锁时，等待时间
     * @return
     */
    long maxSleepMills() default 60 * 1000;

    /**
     * 锁的类型
     * @return
     */
    LockType lockType();
}
