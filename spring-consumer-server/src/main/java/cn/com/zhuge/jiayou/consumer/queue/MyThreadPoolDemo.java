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
 * 3.keepAliveTime 多余空闲线程存活时长
 * 4.unit keepAliveTime时间单位
 * 5.workQueue 阻塞队列
 * 6.threadFactory 生成线程池中工作线程的线程工厂，用于创建线程 一般选用默认
 * 7.handler：拒绝策略，表示当队列满了并且工作线程大约等待线程池最大数量maximumPoolSize
 *
 *
 * 线程池拒绝策略
 * 1.AbortPolicy()默认：直接抛出RejectedExecutionException异常组织系统正常运行
 * 2.CallerRunsPolicy():"调用者运行" 的一种机制，该策略不会抛出异常 也不会抛弃任务  会将某些任务回退到调用者，从而降低新任务的流量
 * 3.DiscardOldestPolicy: 丢弃队列中执行最久的任务，然后把当前任务加入到队列中尝试再次提交当前任务
 * 4.DiscardPolicy: 直接丢弃任务，不予以处理且不抛异常。如果任务允许丢失 这是最好的策略
 *
 *
 *
 * 如何合理分配线程池大小 maximumPoolSize
 * 1.CPU密集型
 *   该任务需要大量运算，而没有阻塞，CPU一直全速运行
 *   CPU密集任务只有正真的多核CPU才可能得到加速（通过多线程）
 *
 *   CPU密集型任务配置安排尽可能少的线程数量
 *   一般公式：线程池数 = CPU核数 + 1
 *
 * 2.IO密集型
 *  该任务需要大量IO，即大量阻塞
 *  在单线程上运行IO密集型的任务会导致大量的CPU运算能力浪费在等待上
 *  所以在IO密集型任务中使用多线程可以大大加速程序运行，即使在单核CPU上，这种速度主要是利用了被浪费掉的阻塞时间
 *
 *  a)
 *      IO密集型任务线程并不是一直在执行任务  所以尽可能的配置多
 *      线程池数 = CPU核数 * 2
 *
 *  b)
 *      IO密集型时，大部分线程都阻塞，所以需要多配置线程数
 *     线程池数 = CPU核数 / (1 - 阻塞系数)           阻塞系数在（0.8~0.9）之间
 *
 */
public class MyThreadPoolDemo {



    public static void main(String[] args) {
        myThreadPoolExecutor();
//        threadPoolInit();

    }

    /**
     * 自定义线程池
     */
    public static void myThreadPoolExecutor() {
        System.out.println("计算机内核数量"+Runtime.getRuntime().availableProcessors());
        //abortPolicy策略
//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
//                2,
//                5,
//                1L,
//                TimeUnit.SECONDS,
//                new LinkedBlockingQueue<>(3),
//                Executors.defaultThreadFactory(),
//                new ThreadPoolExecutor.AbortPolicy());


        //callerRunsPolicy策略
//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
//                2,
//                5,
//                1L,
//                TimeUnit.SECONDS,
//                new LinkedBlockingQueue<>(3),
//                Executors.defaultThreadFactory(),
//                new ThreadPoolExecutor.CallerRunsPolicy());

        //discardOldestPolicy策略
//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
//                2,
//                5,
//                1L,
//                TimeUnit.SECONDS,
//                new LinkedBlockingQueue<>(3),
//                Executors.defaultThreadFactory(),
//                new ThreadPoolExecutor.DiscardOldestPolicy());

        //discardPolicy策略
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy());



        //模拟十个用户办理业务，每个用户是一个外部请求的线程
        try {
            for (int i = 0; i < 10; i++) {
                threadPoolExecutor.execute(() ->{
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
            threadPoolExecutor.shutdown();
        }


    }


    /**
     *    java自带Executors类创建线程池
     */
    public static void threadPoolInit() {
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
