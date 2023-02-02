package cn.com.zhuge.jiayou.alipay.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

/**
 * @author 诸葛
 * @date 2022/08/24 10:04
 **/
@RestController
public class DBController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("insertLog")
    public String inputLog() throws SQLException {


            String insertLog = "insert into room_open_log (room_id,open_lock_time,open_lock_type,card_id,fingerprint_id,open_lock_result,create_time) values (?,?,?,?,?,?,?)";

            jdbcTemplate.update(insertLog, 123, 54, "roomOpenLog1", "", 43, "","");

        return "success";




    }
}
