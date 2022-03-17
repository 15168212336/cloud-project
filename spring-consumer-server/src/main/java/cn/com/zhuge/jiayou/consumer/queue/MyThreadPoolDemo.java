package cn.com.zhuge.jiayou.consumer.queue;

import com.sun.jmx.snmp.tasks.ThreadService;

import java.util.Random;
import java.util.concurrent.*;

/**
 * 自定义线程池
 *
 * Executors.newFixedThreadPool() 一池固定数量的线程
 * Executors.newSingledThreadPool() 一池单个线程
 * Executors.newCachedThreadPool() 一池多个可扩展线程
 *
 * 线程池7大参数
 * 1.corePoolSize 常驻核心线程数
 * 2.maximumPoolSize  线程最大数量
 * 3.keepAliveTime 线程存活时长
 * 4.unit 时间单位
 * 5.workQueue 工作队列
 * 6.
 *
 *
 */
public class MyThreadPoolDemo {



    public static void main(String[] args) {
//        ExecutorService threadPool = Executors.newFixedThreadPool(5);//一池5个处理线程
        ExecutorService threadPool = Executors.newSingleThreadExecutor();//一池1个处理线程
//        ExecutorService threadPool = Executors.newCachedThreadPool();//一池1个处理线程

        //底层都是使用这个new出来的
        new ThreadPoolExecutor(1,1,0L, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<Runnable>());

        //模拟十个用户办理业务，每个用户是一个外部请求的线程
        try {
            for (int i = 0; i < 10; i++) {
                threadPool.execute(() ->{
                    try {
                        System.out.println(Thread.currentThread().getName()+"\t 办理业务");
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            threadPool.shutdown();
        }



    }
}
