package cn.com.zhuge.jiayou.consumerservice.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {

    @GetMapping("consumer")
    public String consumer(@RequestParam("tag") int tag) {
        return "tag is " + tag;
    }
}
