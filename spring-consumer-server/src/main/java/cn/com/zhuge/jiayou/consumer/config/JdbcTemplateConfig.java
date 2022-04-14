package cn.com.zhuge.jiayou.consumer.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

//@Configuration
public class JdbcTemplateConfig {

    public JdbcTemplate getJdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        return jdbcTemplate;
    }
}
