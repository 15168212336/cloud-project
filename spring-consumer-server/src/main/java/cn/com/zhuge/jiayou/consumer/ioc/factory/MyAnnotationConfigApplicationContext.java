package cn.com.zhuge.jiayou.consumer.ioc.factory;

import cn.com.zhuge.jiayou.consumer.ioc.entity.MyBeanDefinition;
import org.springframework.context.ApplicationContext;

import java.util.Set;

public class MyAnnotationConfigApplicationContext {
    private Set<MyBeanDefinition> myBeanDefinitions;
    private MyAnnotationConfigApplicationContext(String... basePackages) {
        //遍历包 获取目标类
    }


    private void scan(String... basePackages) {

    }

}
