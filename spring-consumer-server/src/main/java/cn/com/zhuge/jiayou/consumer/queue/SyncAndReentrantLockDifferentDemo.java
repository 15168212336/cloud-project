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
//        new Thread(()->{
//            print.printc1();
//        },"D").start();
//        new Thread(()->{
//            print.printc2();
//        },"E").start();
//        new Thread(()->{
//            print.printc3();
//        },"F").start();
//        new Thread(()->{
//            print.printc1();
//        },"G").start();
//        new Thread(()->{
//            print.printc2();
//        },"H").start();
//        new Thread(()->{
//            print.printc3();
//        },"I").start();
//        new Thread(()->{
//            print.printc1();
//        },"G").start();
//        new Thread(()->{
//            print.printc2();
//        },"K").start();
//        new Thread(()->{
//            print.printc3();
//        },"L").start();
    }
}
