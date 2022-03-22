package cn.com.zhuge.jiayou.consumer.basic;

public class StringDemo {


    public static void main(String[] args) {
        String st1 = "test";
        String st2 = new String("test");
        String st3 = st2;   //引用传递
        System.out.println(st1 == st2);
        System.out.println(st2 == st3);
        System.out.println(st1 == st3);
        System.out.println(st2.equals(st1));
        System.out.println(st3.equals(st1));
        System.out.println(st3.equals(st2));
    }
}
