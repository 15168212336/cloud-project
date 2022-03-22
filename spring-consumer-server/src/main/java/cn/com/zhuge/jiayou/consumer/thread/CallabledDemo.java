package cn.com.zhuge.jiayou.consumer.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallabledDemo {

    public static void main(String[] args) {

        try {
            int i = 8;

            FutureTask<Integer> futureTask = new FutureTask(new MyCallabled());
            new Thread(futureTask).start();

            System.out.println(futureTask.get());
            System.out.println(futureTask.get() + i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }
}
