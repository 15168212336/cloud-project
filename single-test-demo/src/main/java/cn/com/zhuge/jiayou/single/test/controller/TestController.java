package cn.com.zhuge.jiayou.single.test.controller;

import cn.com.zhuge.jiayou.common.redisson.annotation.RedisLock;
import cn.com.zhuge.jiayou.common.redisson.context.LockType;
import cn.com.zhuge.jiayou.common.redisson.utils.RedissonUtil;
import org.redisson.Redisson;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 诸葛
 * @date 2022/08/19 11:13
 **/
@RestController
public class TestController {

    private static int count = 0;
    private static int beforeCount = 0;
    @Autowired
    private RedissonClient redissonClient;

    @GetMapping("lock/Test")
    @RedisLock(lockKey = "transactionTest",keepMills = 3000,lockType = LockType.REENTRANT_LOCK)
    public String lockTest() {
        beforeCount++;
        long lockNum = 0;
        RAtomicLong lockTest = redissonClient.getAtomicLong("lockTest");
        lockNum = lockTest.get();
        lockNum++;
        lockTest.set(lockNum);
        count++;
        System.out.println(lockNum+"--"+count+"--"+beforeCount);
        return "{\"num\":"+lockNum+"}";
    }

    @GetMapping("noLock/Test")
    public String noLockTest() {
            long noLockNum = 0;
            RAtomicLong lockTest = redissonClient.getAtomicLong("noLockTest");
            noLockNum = lockTest.get();
            noLockNum++;
            lockTest.set(noLockNum);
            System.out.println("=========="+noLockNum);
            return "{\"num\":" + noLockNum + "}";
    }
}
