package cn.com.zhuge.jiayou.consumer.ioc.entity;

import lombok.Data;

@Data
public class MyBeanDefinition {

    private String beanName;
    private Class beanClass;
}
