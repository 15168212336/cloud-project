package cn.com.zhuge.jiayou.consumer.ioc.factory;

import cn.com.zhuge.jiayou.consumer.ioc.entity.MyBeanDefinition;
import org.springframework.context.ApplicationContext;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MyAnnotationConfigApplicationContext {
    private Set<MyBeanDefinition> myBeanDefinitions = new HashSet<>();
    public MyAnnotationConfigApplicationContext(String... basePackages) {
        //遍历包 获取目标类
        scan(basePackages);

    }


    private void scan(String... basePackages) {
        ScanExecutor scanExecutor = ScanExecutor.getInstance();
        for (String basePackage : basePackages) {
            Set<Class<?>> classSet = scanExecutor.search(basePackage, null);
            Iterator<Class<?>> iterator = classSet.iterator();
            for (Class clazz : classSet) {
                MyBeanDefinition myBeanDefinition = new MyBeanDefinition();
                System.out.println(clazz.getName());
                Annotation[] annotations = clazz.getAnnotations();
                if (annotations != null && annotations.length > 0) {
                    for (Annotation annotation :
                            annotations) {
                        annotation.getClass().getName().equals("Component");
                        setMyBeanDefinition(clazz, myBeanDefinition);
                    }
                }
                myBeanDefinitions.add(myBeanDefinition);
            }
        }
    }

    private void setMyBeanDefinition(Class clazz, MyBeanDefinition myBeanDefinition) {
        //todo 判断是否设置beanName注解
        setBeanName(clazz, myBeanDefinition);
        //todo 判断是否设置value注解
        setBean(clazz, myBeanDefinition);
    }

    private void setBeanName(Class clazz, MyBeanDefinition myBeanDefinition) {
        if (true) {

        } else {
            myBeanDefinition.setBeanName(clazz.getName());
        }
    }

    public void setBean(Class clazz, MyBeanDefinition myBeanDefinition) {
//        try {
//            Class newClass = (Class) clazz.newInstance();
//            myBeanDefinition.setBeanClass(newClass);
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
    }



}
