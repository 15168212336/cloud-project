package cn.com.zhuge.jiayou.ioc.service;



import cn.com.zhuge.jiayou.ioc.dao.HelloDao;
import cn.com.zhuge.jiayou.ioc.factory.BeanFactory;

import java.util.List;

public class HelloServiceImpl implements HelloService{

    @Override
    public List getData() {
        HelloDao helloDao = (HelloDao) BeanFactory.getDao();
        HelloDao helloDao2 = (HelloDao) BeanFactory.getDao();
        System.out.println(helloDao==helloDao2);
        return helloDao.findAll();
    }
}
