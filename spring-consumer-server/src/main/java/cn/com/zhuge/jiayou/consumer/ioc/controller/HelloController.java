package cn.com.zhuge.jiayou.consumer.ioc.controller;

import cn.com.zhuge.jiayou.consumer.ioc.service.HelloService;
import cn.com.zhuge.jiayou.consumer.ioc.service.HelloServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/hello")
public class HelloController extends HttpServlet {

    private HelloService helloService = new HelloServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.getWriter().write(helloService.getData().toString());
    }
}
