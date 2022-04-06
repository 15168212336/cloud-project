package cn.com.zhuge.jiayou.ioc;



import cn.com.zhuge.jiayou.ioc.service.HelloService;
import cn.com.zhuge.jiayou.ioc.service.HelloServiceImpl;

import javax.servlet.http.HttpServlet;
import java.util.HashSet;
import java.util.Set;


public class HelloController extends HttpServlet {

    private HelloService helloService = new HelloServiceImpl();

//    public String Hello() {
//        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("cn.com.zhuge.jiayou.consumer.ioc.entity");
//        Set set = new HashSet();
//
//        System.out.println(applicationContext.getBean("account"));
//        return helloService.getData().toString();
//    }
}
