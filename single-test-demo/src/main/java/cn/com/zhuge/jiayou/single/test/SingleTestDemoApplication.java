package cn.com.zhuge.jiayou.single.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
@ComponentScan("cn.com.zhuge.jiayou.*")
public class SingleTestDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SingleTestDemoApplication.class, args);
    }

}
