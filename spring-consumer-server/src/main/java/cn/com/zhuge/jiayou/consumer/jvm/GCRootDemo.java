package cn.com.zhuge.jiayou.consumer.jvm;

/**
 *
 * JVM垃圾回收的时候如何确定垃圾？是否知道什么是GCRoots
 *
 *
 * 什么事GCRoots ：JVM采用根可达性算法判断一个对象是否可以回收
 *
 * java中 可以作为GC Roots的对象有
 * 1.虚拟机栈(栈帧中的本地变量表)中引用的对象
 * 2.方法区中的类静态属性引用的对象
 * 3.方法区中常量引用的对象
 * 4.本地方法栈中Native方法引用的对象
 *
 */
public class GCRootDemo {

    //m2为方法区中静态属性引用的对象
//    private static GCRootDemo2 m2;

    //m3为方法区中常量引用的对象
//    private static final GCRootDemo3 m3;


    public static void m1() {
        //m1为虚拟机栈中引用的对象
        GCRootDemo m1 = new GCRootDemo();

    }

    public static void main(String[] args) {
        m1();
    }
}

