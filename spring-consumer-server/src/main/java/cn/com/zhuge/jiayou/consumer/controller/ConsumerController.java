package cn.com.zhuge.jiayou.consumer.controller;


import com.alibaba.druid.pool.DruidDataSource;
import com.google.inject.internal.cglib.core.$DefaultGeneratorStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

@RestController
public class ConsumerController {

    private int i = 0;
    @Autowired
    private DataSource dataSource;

    @GetMapping("consumer")
    public String consumer(@RequestParam("tag") String tag) {
        synchronized (tag.intern()) {
            try {
                System.out.println(tag + "-------in");
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "tag is " + tag;
        }
    }

    @GetMapping("JMeter")
    public void JMeterTest(@RequestParam("tag") String tag) {

//        i++;
//        System.out.println(i);
        synchronized (tag.intern()) {
            try {
                System.out.println(tag + "is in");
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
//        return "tag is " + tag;
    }

    @GetMapping("datasource")
    public void datasource() {
        System.out.println(dataSource.getClass());
        try {
            Connection connection = dataSource.getConnection();
            System.out.println(connection);

            DruidDataSource druidDataSource = (DruidDataSource) dataSource;
            System.out.println("druidDataSource 数据源最大连接数：" + druidDataSource.getMaxActive());
            System.out.println("druidDataSource 数据源初始化连接数：" + druidDataSource.getInitialSize());

            //关闭连接
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(1);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, () -> {
            System.out.println("等待线程执行");
        });

        for (int i = 1; i <= 5; i++) {
            new Thread(() -> {

                try {
                    System.out.println("线程"+Thread.currentThread().getName()+"执行中");
                    Random random = new Random();
                    long i1 = random.nextInt(5);
                    System.out.println(i1);
                    TimeUnit.SECONDS.sleep(i1);
                    System.out.println("线程"+Thread.currentThread().getName()+"执行完成");
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }

//        for (int i = 1; i <= 6; i++) {
//            new Thread(() -> {
//                try {
//                    System.out.println("线程等待");
//                    semaphore.acquire();
//                    System.out.println(Thread.currentThread().getName()+"抢占成功");
//                    TimeUnit.SECONDS.sleep(3);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }finally {
//                    semaphore.release();
//                    System.out.println(Thread.currentThread().getName()+"释放");
//                }
//            },String.valueOf(i)).start();
//        }
    }
}
