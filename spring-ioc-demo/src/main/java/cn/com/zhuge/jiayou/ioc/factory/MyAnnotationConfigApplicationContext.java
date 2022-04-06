package cn.com.zhuge.jiayou.ioc.factory;

import cn.com.zhuge.jiayou.ioc.annotation.Component;
import cn.com.zhuge.jiayou.ioc.annotation.Value;
import cn.com.zhuge.jiayou.ioc.entity.MyBeanDefinition;
import cn.com.zhuge.jiayou.ioc.utils.ScanExecutor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MyAnnotationConfigApplicationContext {
    private Set<MyBeanDefinition> myBeanDefinitions = new HashSet<>();

    private HashMap<String, Object> myBean = new HashMap<>();

    public MyAnnotationConfigApplicationContext(String... basePackages) {
        //遍历包 获取目标类
//        scan(basePackages);
        for (String basePackage : basePackages) {
            myBeanDefinitions.addAll(searchBeanDefinition(basePackage));
        }

        setBean();


    }

    private void setBean() {
        Iterator<MyBeanDefinition> iterator = myBeanDefinitions.iterator();
        while (iterator.hasNext()) {
            //实例化
            MyBeanDefinition myBeanDefinition = iterator.next();
            Class beanClass = myBeanDefinition.getBeanClass();
            String beanName = myBeanDefinition.getBeanName();
            Object o = instantiateBean(beanClass);
            System.out.println(o);
            myBean.put(beanName, o);
        }

    }

    private Object instantiateBean(Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        Object o = null;

        try {
            o = clazz.newInstance();
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                Value fieldValue = field.getAnnotation(Value.class);
                //判断是否带value注解
                if (fieldValue != null) {
                    //带value注解
                    Method declaredMethod = clazz.getDeclaredMethod("set" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1), field.getType());
                    System.out.println("set" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1));
                    System.out.println(declaredMethod);
                    System.out.println(field.getType().getTypeName());
                    switch (field.getType().getTypeName()) {
                        case "int":
                            declaredMethod.invoke(o,Integer.parseInt(fieldValue.value()));
                            break;
                        case "short":
                            declaredMethod.invoke(Short.parseShort(fieldValue.value()));
                            break;
                        case "java.lang.Integer":
                            declaredMethod.invoke(Integer.getInteger(fieldValue.value()));
                            break;
                        case "java.lang.String":
                            declaredMethod.invoke(fieldValue.value());
                            break;
                    }
                }
            }
            return o;


        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return o;
    }

    public Object getBean(String beanName) {
        if (!myBean.containsKey(beanName)) {

        }
        return myBean.get(beanName);

    }



    private Set<MyBeanDefinition> searchBeanDefinition(String basePackage) {
        Set<MyBeanDefinition> myBeanDefinitions = new HashSet<>();
        ScanExecutor scanExecutor = ScanExecutor.getInstance();
        //扫描遍历指定包下的类
        Set<Class<?>> classSet = scanExecutor.search(basePackage, null);
        Iterator<Class<?>> iterator = classSet.iterator();
        while (iterator.hasNext()) {
            Class<?> clazz = iterator.next();
            //设置bean
            MyBeanDefinition myBeanDefinition = setMyBeanDefinition(clazz);
            if (myBeanDefinition != null) {
                myBeanDefinitions.add(myBeanDefinition);
                System.out.println(myBeanDefinition.toString());
            }
        }
        return myBeanDefinitions;
    }


    private void scan(String... basePackages) {
        ScanExecutor scanExecutor = ScanExecutor.getInstance();
        for (String basePackage : basePackages) {
            Set<Class<?>> classSet = scanExecutor.search(basePackage, null);
            Iterator<Class<?>> iterator = classSet.iterator();
            while (iterator.hasNext()) {

//                myBeanDefinitions.add(myBeanDefinition);
            }
        }
    }


    private MyBeanDefinition setMyBeanDefinition(Class<?> clazz) {
        //筛选带有component注解的类
        Component component = clazz.getAnnotation(Component.class);
        if (component != null) {
            MyBeanDefinition myBeanDefinition = new MyBeanDefinition();
            //判断注解是否定义beanName
            if (component.value().equals("")) {
                //未定义则取类名首字母小写
                String simpleName = clazz.getSimpleName();
                myBeanDefinition.setBeanName(simpleName.substring(0, 1).toLowerCase() + simpleName.substring(1)); //设置beanName
            } else {
                myBeanDefinition.setBeanName(component.value());//设置beanName

            }
            //设置beanClass
            myBeanDefinition.setBeanClass(clazz);
            return myBeanDefinition;

        } else {
            return null;
        }
    }


}
