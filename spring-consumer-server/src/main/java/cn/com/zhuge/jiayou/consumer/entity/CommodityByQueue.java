package cn.com.zhuge.jiayou.consumer.entity;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class CommodityByQueue {
    private BlockingQueue blockingQueue = null;
    private volatile boolean flag = true;


    public CommodityByQueue(BlockingQueue blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println("queue类型"+blockingQueue.getClass().getTypeName());
    }

    public void prod() {
        //1.判断
        while (flag) {
            Random random = new Random();
            int data = random.nextInt(10);
            try {
                //2干活
                if (blockingQueue.offer(data)) {
                    System.out.println("生产"+data);
                }
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }

        System.out.println("生产者停止");
    }

    public void consumer() {
        //1.判断
        while (flag) {
            //2干活
            Object poll = blockingQueue.poll();
            if (poll != null) {
                System.out.println("消费"+poll);
            }
        }

        System.out.println("消费者停止");
    }

    public void close() {
        flag = false;
    }
    public void open() {
        flag = true;
    }
}
