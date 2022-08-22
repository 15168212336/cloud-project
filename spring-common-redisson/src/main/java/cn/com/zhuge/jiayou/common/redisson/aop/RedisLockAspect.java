package cn.com.zhuge.jiayou.common.redisson.aop;


import cn.com.zhuge.jiayou.common.redisson.annotation.RedisLock;
import cn.com.zhuge.jiayou.common.redisson.utils.RedissonUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import static cn.com.zhuge.jiayou.common.redisson.context.LockType.*;


@Component
@Aspect
public class RedisLockAspect {
    @Autowired
    private RedissonClient redissonClient;

    @Around(value = "execution(* cn.com.zhuge.jiayou..*(..))&&@annotation(cn.com.zhuge.jiayou.common.redisson.annotation.RedisLock)")
    public Object lock(ProceedingJoinPoint point) throws Throwable {
        Object object =null;
        RedisLock redisLock = getDistRedisLockInfo(point);
//            String lockKey = RedissonUtil.getLockKey(point, redisLock.lockKey());
            switch (redisLock.lockType()) {
                case REENTRANT_LOCK: {
                    object = reentrantLock(point, redisLock);
                    break;
                }
                case FAIR_LOCK:{
                    object = fairLock(point, redisLock);
                    break;
                }
                case READ_LOCK:
                    break;
                case WRITE_LOCK:
                    break;
            }
        return object;
    }

    private RedisLock getDistRedisLockInfo(ProceedingJoinPoint point) {
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        Method method = methodSignature.getMethod();
        return method.getAnnotation(RedisLock.class);
    }

    /***
     * 可重入锁
     * @param point
     * @param redisLock
     * @return
     * @throws Throwable
     */
    private Object reentrantLock(ProceedingJoinPoint point, RedisLock redisLock) throws Throwable {
        Object object = null;
        RLock lock = null;
        try {
            String lockKey = RedissonUtil.getLockKey(point, redisLock.lockKey());
            lock = RedissonUtil.getRLock(redissonClient, lockKey);
            System.out.println("tryLock=================="+lock.isLocked());
            if (lock.tryLock(redisLock.maxSleepMills(), redisLock.keepMills(), TimeUnit.MILLISECONDS)) {
                object = point.proceed();
            }
        }finally {
            if (lock.isLocked()&&lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
        return object;
    }

    /**
     * 可重入公平锁
     * @param point
     * @param redisLock
     * @return
     * @throws Throwable
     */
    private Object fairLock(ProceedingJoinPoint point, RedisLock redisLock) throws Throwable {
        Object object = null;
        RLock fairlock = null;
        try {
            String lockKey = RedissonUtil.getLockKey(point, redisLock.lockKey());
            fairlock = RedissonUtil.getFairLock(redissonClient, lockKey);
            if (fairlock.tryLock(redisLock.maxSleepMills(), redisLock.keepMills(), TimeUnit.MILLISECONDS)) {
                object = point.proceed();
            }
        }finally {
            if (fairlock.isLocked()) {
                fairlock.unlock();
            }
        }
        return object;
    }
}
