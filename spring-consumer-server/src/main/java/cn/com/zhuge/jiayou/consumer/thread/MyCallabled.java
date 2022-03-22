package cn.com.zhuge.jiayou.consumer.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class MyCallabled implements Callable<Integer>{


    @Override
    public Integer call() throws Exception {
        TimeUnit.SECONDS.sleep(4);
        return 12;
    }
}
