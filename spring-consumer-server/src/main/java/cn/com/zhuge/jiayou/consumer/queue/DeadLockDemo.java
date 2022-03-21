package cn.com.zhuge.jiayou.consumer.queue;

import java.util.concurrent.TimeUnit;

/**
 * 死锁：
 * 指的是两个或者两个以上的线程在执行过程中，
 * 因互相抢夺资源而造成的一种互相等待的现象
 * 若无外力干涉它们将一直等待下去
 *
 * 死锁原因：
 *  1.系统资源不足
 *  2.进城运行时推进的
 *  3.资源分配不当
 *
 *  排查死锁
 *  jdk自带jps 和jstack
 *
 *  jps:查看当前运行的java进程 和端口
 *  jstack:查看原因
 */
public class DeadLockDemo {

    public static void main(String[] args) {

        new Thread(new HoldLockThread("A","B"),"线程A").start();

        new Thread(new HoldLockThread("B","A"),"线程B").start();

    }
}


/**
 * 持锁线程
 */
class HoldLockThread implements Runnable {

    private String lockA;
    private String lockB;

    public HoldLockThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA) {
            try {
                System.out.println(Thread.currentThread().getName() + "持有" + lockA);
                TimeUnit.SECONDS.sleep(1);
                System.out.println("尝试获取"+lockB);
                synchronized (lockB) {
                    System.out.println(lockB+"获取成功");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
