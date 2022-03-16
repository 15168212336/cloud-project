package cn.com.zhuge.jiayou.consumer.queue;

import cn.com.zhuge.jiayou.consumer.entity.Commodity;

public class ProdConsumerDemo {
    public static void main(String[] args) {
        Commodity commodity = new Commodity();
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    commodity.IncreaseStock();
                }
            }, "T1").start();

            new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    commodity.DecreaseStock();
                }
            }, "T2").start();
        }

//        new Thread(() ->{
//            commodity.IncreaseStock();
//        },"T1").start();
//
//        new Thread(() ->{
//            commodity.DecreaseStock();
//        },"T1").start();

    }

}
