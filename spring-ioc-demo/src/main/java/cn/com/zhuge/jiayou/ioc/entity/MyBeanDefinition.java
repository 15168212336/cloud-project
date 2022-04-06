package cn.com.zhuge.jiayou.ioc.entity;

import cn.com.zhuge.jiayou.ioc.annotation.Component;
import lombok.Data;

@Data
public class MyBeanDefinition {

    private String beanName;
    private Class beanClass;
}
