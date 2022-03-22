package cn.com.zhuge.jiayou.consumer.ioc.service;

import cn.com.zhuge.jiayou.consumer.ioc.dao.HelloDao;
import cn.com.zhuge.jiayou.consumer.ioc.dao.HelloDaoImpl;

import java.util.List;

public class HelloServiceImpl implements HelloService{
    @Override
    public List getData() {
        HelloDao helloDao = new HelloDaoImpl();
        return helloDao.findAll();
    }
}
