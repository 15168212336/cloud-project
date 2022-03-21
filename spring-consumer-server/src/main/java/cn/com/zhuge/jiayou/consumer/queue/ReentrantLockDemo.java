package cn.com.zhuge.jiayou.consumer.queue;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockCheck reentrantLock= new ReentrantLockCheck();
        new Thread(reentrantLock, "AA").start();
        TimeUnit.SECONDS.sleep(1L);
        new Thread(reentrantLock, "BB").start();

    }
}

class ReentrantLockCheck implements Runnable{

    @Override
    public void run() {


            if (Thread.currentThread().getName().equals("AA")) {
                method01();
            }else {
                method02();
            }

    }

    public void method01() {
        ReentrantLock reentrantLock = new ReentrantLock();
        try {
            reentrantLock.lock();
            System.out.println(Thread.currentThread().getName()+"进入方法01");
            TimeUnit.SECONDS.sleep(2L);
            method02();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            reentrantLock.unlock();
        }
    }
    public void method02() {
        ReentrantLock reentrantLock = new ReentrantLock();
        try {
            reentrantLock.lock();
            System.out.println(Thread.currentThread().getName()+"进入方法02");
            TimeUnit.SECONDS.sleep(5L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            reentrantLock.unlock();
        }


    }
}

class ReadLockCheck implements Runnable{

    @Override
    public void run() {
        if (Thread.currentThread().getName().equals("AA")) {
            method01();
        }else {
            method02();
        }
    }

    public void method01() {
        try {
            System.out.println(Thread.currentThread().getName()+"进入方法01");
            TimeUnit.SECONDS.sleep(2L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        method02();
    }
    public void method02() {

        System.out.println(Thread.currentThread().getName()+"进入方法02");


    }
}
