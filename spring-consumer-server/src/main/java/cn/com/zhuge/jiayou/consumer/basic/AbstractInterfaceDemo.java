package cn.com.zhuge.jiayou.consumer.basic;

/**
 * 抽象类中可以存在普通成员函数，接口只能存在public abstract方法  jdk8之后 接口中可以存在 default修饰的成员函数
 *  抽象类中的成员变量可以是任意类型，接口中只能public static final
 *  抽象类只能继承一个
 *
 *
 *  接口的设计目的，是对类的行为进行约束（更准确的说是一种“有”约束，因为接口不能规定类不可以有
 什么行为），也就是提供一种机制，可以强制要求不同的类具有相同的行为。它只约束了行为的有无，
 但不对如何实现行为进行限制。

 而抽象类的设计目的，是代码复用。当不同的类具有某些相同的行为(记为行为集合A)，且其中一部分行
 为的实现方式一致时（A的非真子集，记为B），可以让这些类都派生于一个抽象类。在这个抽象类中实
 现了B，避免让所有的子类来实现B，这就达到了代码复用的目的。而A减B的部分，留给各个子类自己
 实现。正是因为A-B在这里没有实现，所以抽象类不允许实例化出来（否则当调用到A-B时，无法执
 行）。

 */
public abstract class AbstractInterfaceDemo {

    public int i = 0;

    public static int j = 0;

    //可以存在普通成员函数
    public String test() {
        return "";
    }

}


/**
 * 接口中只能存在public abstract方法  jdk8之后 接口中可以存在 default修饰的成员函数
 */
interface InterfaceDemo {
    //默认public static final
    String u = "";
    int i = 0;
    void test();

    String test(int i);

    //可以存在default修饰的成员函数
    default String method(String string) {
        return null;
    }

}
