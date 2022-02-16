package cn.com.zhuge.jiayou.consumer.controller;


import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RestController
public class ConsumerController {

    @Autowired
    private DataSource dataSource;

    @GetMapping("consumer")
    public String consumer(@RequestParam("tag") int tag) {
        return "tag is " + tag;
    }

    @GetMapping("datasource")
    public void datasource() {
        System.out.println(dataSource.getClass());
        try {
            Connection connection = dataSource.getConnection();
            System.out.println(connection);

            DruidDataSource druidDataSource = (DruidDataSource) dataSource;
            System.out.println("druidDataSource 数据源最大连接数：" + druidDataSource.getMaxActive());
            System.out.println("druidDataSource 数据源初始化连接数：" + druidDataSource.getInitialSize());

            //关闭连接
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
