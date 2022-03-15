package cn.com.zhuge.jiayou.consumer.queue;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 *
 *
 * 1.阻塞队列
 *
 */
public class BlockingQueueDemo {

    public static void main(String[] args) {
//        List list = new ArrayList();
        BlockingQueue blockingQueue = new ArrayBlockingQueue(3);
        //add ,remove 成功ture  失败抛出异常
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
        System.out.println(blockingQueue.element());
        System.out.println(blockingQueue.remove("a"));
        System.out.println(blockingQueue.remove("b"));
//        System.out.println(blockingQueue.remove("c"));

        //offer / poll 插入成功返回ture 失败false 取出失败返回null
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        System.out.println(blockingQueue.offer("d"));
        System.out.println(blockingQueue.peek());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());

        //put / take  队列满了阻塞线程 直到添加队列  从队列空了阻塞线程 直到队列有
        try {
            blockingQueue.put("a");
            blockingQueue.put("a");
            blockingQueue.put("a");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        //offer 队列满了之后等待 超时后返回false
        try {
            System.out.println(blockingQueue.offer("a", 3l, TimeUnit.SECONDS));
            System.out.println(blockingQueue.offer("b", 3l, TimeUnit.SECONDS));
            System.out.println(blockingQueue.offer("c", 3l, TimeUnit.SECONDS));

            System.out.println(blockingQueue.poll(2l,TimeUnit.SECONDS));
            System.out.println(blockingQueue.poll(2l,TimeUnit.SECONDS));
            System.out.println(blockingQueue.poll(2l,TimeUnit.SECONDS));
            System.out.println(blockingQueue.poll(2l,TimeUnit.SECONDS));
            System.out.println(blockingQueue.poll(2l,TimeUnit.SECONDS));

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() ->{
            try {
                for (int i = 0; i < 10; i++) {
                    System.out.println(blockingQueue.offer(i,1l,TimeUnit.SECONDS));
                    TimeUnit.SECONDS.sleep(1l);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()->{
            try {
                    for (int i = 0; i < 10; i++) {
                        System.out.println(blockingQueue.poll(3l,TimeUnit.SECONDS));
                    }
                }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();


    }

}
