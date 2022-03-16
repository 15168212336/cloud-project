package cn.com.zhuge.jiayou.consumer.entity;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Commodity {

    private int stock;

    private Lock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();

    public void IncreaseStock() {

        lock.lock();
        try {
            //1.判断
            // 注：多线程判断必须要用while  if会出现虚假唤醒
            while (stock != 0) {
                condition.await();
            }
            //2.操作
            stock = stock + 1;
            System.out.println("生产1，当前"+stock);
            //3.通知
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }

    public void DecreaseStock() {
        lock.lock();
        try {
            while (stock == 0) {
                condition.await();
            }
            stock = stock - 1;
            System.out.println("消费1，当前"+stock);
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }
}
