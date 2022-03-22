package cn.com.zhuge.jiayou.consumer.basic;

import org.springframework.beans.factory.annotation.Autowired;

/**
 *final
 修饰类：表示类不可被继承
 修饰方法：表示方法不可被子类覆盖，但是可以重载
 修饰变量：表示变量一旦被赋值就不可以更改它的值 ,如果修饰的是引用对象  可以修改引用对象的值 但是不能指向另一个对象


 */
public class FinalDemo extends FinalMethodClass{
//public class FinalDemo extends FinalClass{

    private final String ms = "test";
    static final int test = 0;

    //-------------------------------------
//    final int test1; //全局变量声明必须指定初始值
    final int test2= 0;
    //-------------------------------------


    //final修饰的方法不能被重写
//    @Override
//    public void finalMethod() { }


    //final修饰的方法能被重载
    public void finalMethod(String string) {
//        ms = "t"; //final修饰的变量不能被修改
        System.out.println("可以被重载");
    }



}

/**
 * final修饰的类不能被extends
 */
final class  FinalClass{



}

/**
 * final修饰的方法不能被重写
 * 但是可以被重载
 */
class FinalMethodClass{
    public final void finalMethod() {
        System.out.println("finalMethod");
    }
}

