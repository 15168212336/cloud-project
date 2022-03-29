package cn.com.zhuge.jiayou.consumer.ioc.factory;

import java.util.Set;
import java.util.function.Predicate;

/**
 * 声明一个扫描接口类
 *
 *  Predicate 是jdk8新增的Lambda表达式相关内容，可以筛选要扫描的类。
 */
public interface Scan {

    String CLASS_SUFFIX = ".class";

    Set<Class<?>> search(String packageName, Predicate<Class<?>> predicate);

    default Set<Class<?>> search(String packageName) {
        return search(packageName, null);
    }
}
