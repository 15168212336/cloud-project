package cn.com.zhuge.jiayou.consumer.queue;

import cn.com.zhuge.jiayou.consumer.entity.Print;

/**
 * synchronized和ReentrantLock区别
 *
 *
 */
public class SyncAndReentrantLockDifferentDemo {
    public static void main(String[] args) {
        Print print = new Print();
//        new Thread(()->{
//            for (int i = 0; i < 5; i++) {
//                print.printc1();
//            }
//        },"A").start();
//        new Thread(()->{
//            for (int i = 0; i < 5; i++) {
//                print.printc2();
//            }
//        },"B").start();
//        new Thread(()->{
//            for (int i = 0; i < 5; i++) {
//                print.printc3();
//            }
//        },"C").start();
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                print.printc1();
            },"A").start();
            new Thread(()->{
                print.printc2();
            },"B").start();
            new Thread(()->{
                print.printc3();
            },"C").start();
        }

    }
}
