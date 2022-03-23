package cn.com.zhuge.jiayou.consumer.ioc.entity;

import lombok.Data;
import org.springframework.stereotype.Repository;

@Data
@Repository
public class Account {
    private String name;
    private String old;
    private String sex;
}
