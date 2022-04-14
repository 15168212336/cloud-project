package cn.com.zhuge.jiayou.consumer.util;

import cn.com.zhuge.jiayou.consumer.annotation.RedisLockKey;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.util.SortedMap;
import java.util.TreeMap;

public class RedissonUtil {
    private static Logger logger = LoggerFactory.getLogger(RedissonUtil.class);

    /**
     * 关闭Redisson客户端连接
     * @param redisson
     */
    public static void closeRedisson(RedissonClient redisson){
        redisson.shutdown();
        logger.info("成功关闭Redis Client连接");
    }

    /**
     * 获取字符串对象
     * @param redisson
     * @param objectName
     * @return
     */
    public static <T> RBucket<T> getRBucket(RedissonClient redisson, String objectName){
        RBucket<T> bucket=redisson.getBucket(objectName);
        return bucket;
    }

    /**
     * 获取Map对象
     * @param redisson
     * @param objectName
     * @return
     */
    public static <K,V> RMap<K, V> getRMap(RedissonClient redisson, String objectName){
        RMap<K, V> map=redisson.getMap(objectName);
        return map;
    }

    /**
     * 获取有序集合
     * @param redisson
     * @param objectName
     * @return
     */
    public static <V> RSortedSet<V> getRSortedSet(RedissonClient redisson, String objectName){
        RSortedSet<V> sortedSet=redisson.getSortedSet(objectName);
        return sortedSet;
    }

    /**
     * 获取集合
     * @param redisson
     * @param objectName
     * @return
     */
    public static <V> RSet<V> getRSet(RedissonClient redisson, String objectName){
        RSet<V> rSet=redisson.getSet(objectName);
        return rSet;
    }

    /**
     * 获取列表
     * @param redisson
     * @param objectName
     * @return
     */
    public static <V> RList<V> getRList(RedissonClient redisson, String objectName){
        RList<V> rList=redisson.getList(objectName);
        return rList;
    }

    /**
     * 获取队列
     * @param redisson
     * @param objectName
     * @return
     */
    public <V> RQueue<V> getRQueue(RedissonClient redisson, String objectName){
        RQueue<V> rQueue=redisson.getQueue(objectName);
        return rQueue;
    }

    /**
     * 获取双端队列
     * @param redisson
     * @param objectName
     * @return
     */
    public static <V> RDeque<V> getRDeque(RedissonClient redisson, String objectName){
        RDeque<V> rDeque=redisson.getDeque(objectName);
        return rDeque;
    }

    /**
     * 此方法不可用在Redisson 1.2 中
     * 在1.2.2版本中可用
     * @param redisson
     * @param objectName
     * @return
     */
    public static <V> RBlockingQueue<V> getRBlockingQueue(RedissonClient redisson, String objectName){
        RBlockingQueue rb=redisson.getBlockingQueue(objectName);
        return rb;
    }

    /**
     * 获取锁
     * @param redisson
     * @param objectName
     * @return
     */
    public static RLock getRLock(RedissonClient redisson, String objectName){
        System.out.println("redisLock======================"+objectName);
        RLock rLock=redisson.getLock(objectName.intern());
        return rLock;
    }

    /**
     * 获取公平锁
     * @param redisson
     * @param objectName
     * @return
     */
    public static RLock getFairLock(RedissonClient redisson, String objectName) {
        RLock rLock = redisson.getFairLock(objectName);
        return rLock;
    }

    /**
     * 获取原子数
     * @param redisson
     * @param objectName
     * @return
     */
    public static RAtomicLong getRAtomicLong(RedissonClient redisson, String objectName){
        RAtomicLong rAtomicLong=redisson.getAtomicLong(objectName);
        return rAtomicLong;
    }

    /**
     * 获取记数锁
     * @param redisson
     * @param objectName
     * @return
     */
    public static RCountDownLatch getRCountDownLatch(RedissonClient redisson, String objectName){
        RCountDownLatch rCountDownLatch=redisson.getCountDownLatch(objectName);
        return rCountDownLatch;
    }

    /**
     * 获取消息的Topic
     * @param redisson
     * @param objectName
     * @return
     */
    public static RTopic getRTopic(RedissonClient redisson, String objectName){
        RTopic rTopic=redisson.getTopic(objectName);
        return rTopic;
    }

    /**
     * 获取包括方法参数上的key
     * redis key的拼写规则为 "DistRedisLock+" + lockKey + @DistRedisLockKey<br/>
     *
     * @param point
     * @param lockKey
     * @return
     */
    public static String getLockKey(ProceedingJoinPoint point, String lockKey) {
        try {
            lockKey = "DistRedisLock:" + lockKey;
            Object[] args = point.getArgs();
            if (args != null && args.length > 0) {
                MethodSignature methodSignature = (MethodSignature)point.getSignature();
                Annotation[][] parameterAnnotations = methodSignature.getMethod().getParameterAnnotations();
                SortedMap<Integer, String> keys = new TreeMap<>();
                for (int i = 0; i < parameterAnnotations.length; i ++) {
                    RedisLockKey redisLockKey = getAnnotation(RedisLockKey.class, parameterAnnotations[i]);
                    if (redisLockKey != null) {
                        Object arg = args[i];
                        if (arg != null) {
                            keys.put(redisLockKey.order(), arg.toString());
                        }
                    }
                }
                if (keys != null && keys.size() > 0){
                    for (String key : keys.values()) {
                        lockKey += (":" + key);
                    }
                }
            }

            return lockKey;
        } catch (Exception e) {
            logger.error("getLockKey error.", e);
        }
        return null;
    }

    /**
     * 获取注解类型
     * @param annotationClass
     * @param annotations
     * @param <T>
     * @return
     */
    private static <T extends Annotation> T getAnnotation(final Class<T> annotationClass, final Annotation[] annotations) {
        if (annotations != null && annotations.length > 0) {
            for (final Annotation annotation : annotations) {
                if (annotationClass.equals(annotation.annotationType())) {
                    return (T) annotation;
                }
            }
        }
        return null;
    }
}
