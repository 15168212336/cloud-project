package cn.com.zhuge.jiayou.ioc.entity;

import cn.com.zhuge.jiayou.ioc.annotation.Component;
import cn.com.zhuge.jiayou.ioc.annotation.Value;
import lombok.Data;

@Data
@Component("account")
public class Account {
    @Value("张三")
    private String name;
    @Value("12")
    private int old;
    @Value("1")
    private short sex;
}
