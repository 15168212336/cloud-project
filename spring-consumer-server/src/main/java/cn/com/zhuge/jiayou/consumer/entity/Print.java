package cn.com.zhuge.jiayou.consumer.entity;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Print {
    private int num = 1;
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    public void printc1() {
        lock.lock();
        try {
            System.out.println("c1开始--num==" + num);
            while (num != 1) {
                System.out.println("c1等待--num=="+num);
                c1.await();
            }
            System.out.println(Thread.currentThread().getName() + "线程打印");
            num = 2;
            System.out.println("c1结束--num==" + num);
            c2.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }

    public void printc2() {
        lock.lock();
        try {
            System.out.println("c2开始--num==" + num);
            while (num != 2) {
                System.out.println("c2等待--num=="+num);
                c2.await();
            }
            System.out.println(Thread.currentThread().getName() + "线程打印");
            num = 3;
            System.out.println("c2结束--num==" + num);
            c3.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }

    public void printc3() {
        lock.lock();
        try {
            System.out.println("c3开始--num==" + num);
            while (num != 3) {
                System.out.println("c3等待--num==" + num);
                c1.await();
            }
            System.out.println(Thread.currentThread().getName() + "线程打印");
            num = 1;
            System.out.println("c3结束--num==" + num);
            c1.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }


}
