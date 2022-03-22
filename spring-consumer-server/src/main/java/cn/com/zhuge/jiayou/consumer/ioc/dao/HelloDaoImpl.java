package cn.com.zhuge.jiayou.consumer.ioc.dao;

import cn.com.zhuge.jiayou.consumer.ioc.dao.HelloDao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HelloDaoImpl implements HelloDao{
    @Override
    public List<String> findAll() {
        return Arrays.asList("test","get","ioc");
    }
}
