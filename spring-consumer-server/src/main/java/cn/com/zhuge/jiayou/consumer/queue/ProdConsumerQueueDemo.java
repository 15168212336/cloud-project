package cn.com.zhuge.jiayou.consumer.queue;

import cn.com.zhuge.jiayou.consumer.entity.CommodityByQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 生产者消费者阻塞队列版
 *
 * 一个可以手动控制开关的生产消费队列
 * 当开启的时候 实现 生产-》消费 循环的操作
 * 当关闭的时候关闭
 */
public class ProdConsumerQueueDemo {

    public static void main(String[] args) {
        BlockingQueue blockingQueue = new ArrayBlockingQueue(1);
        CommodityByQueue commodityByQueue = new CommodityByQueue(blockingQueue);
        new Thread(() -> {
            commodityByQueue.prod();
        }, "T1").start();

        new Thread(() -> {
            commodityByQueue.consumer();
        }, "T2").start();

        try {
            TimeUnit.SECONDS.sleep(5);
            commodityByQueue.close();
            System.out.println("停止");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



    }

}
