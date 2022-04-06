package cn.com.zhuge.jiayou.ioc.entity;


import cn.com.zhuge.jiayou.ioc.annotation.Component;

@Component
public class User {
    private Account account;
    private String name;
    private int old;
}
