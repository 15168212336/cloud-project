package cn.com.zhuge.jiayou.consumer.ioc.controller;

import cn.com.zhuge.jiayou.consumer.ioc.service.HelloService;
import cn.com.zhuge.jiayou.consumer.ioc.service.HelloServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;
import java.util.HashSet;
import java.util.Set;

@RestController
public class HelloController extends HttpServlet {

    private HelloService helloService = new HelloServiceImpl();

    @RequestMapping("hello")
    public String Hello() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("cn.com.zhuge.jiayou.consumer.ioc.entity");
        Set set = new HashSet();

        System.out.println(applicationContext.getBean("account"));
        return helloService.getData().toString();
    }
}
