package cn.com.zhuge.jiayou.consumer.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * SynchronousQueue 没有容量
 * 与BlockingQueue不同 SynchronousQueue不存储元素
 *
 * 不消费就不生产
 */
public class SynchronousQueueDemo {


    /**
     * 验证： 不消费就不生产  专属定制版队列
     * @param args
     */
    public static void main(String[] args) {
        BlockingQueue blockingQueue = new SynchronousQueue();
        new Thread(() -> {
            try {
                System.out.println("生产AAA");
                blockingQueue.put("AAA");
                System.out.println("生产BBB");
                blockingQueue.put("BBB");
                System.out.println("生产CCC");
                blockingQueue.put("CCC");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"AAA").start();

        new Thread(() ->{
            try {
                TimeUnit.SECONDS.sleep(3l);
                System.out.println("消费"+blockingQueue.take());
                TimeUnit.SECONDS.sleep(3l);
                System.out.println("消费"+blockingQueue.take());
                TimeUnit.SECONDS.sleep(3l);
                System.out.println("消费"+blockingQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"BBB").start();

    }
}
