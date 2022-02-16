package cn.com.zhuge.jiayou.consumer.controller;


import cn.com.zhuge.jiayou.consumer.entity.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ConsumerController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("getAllRoom")
    public List<Room> getAllRoom() {
        List<Room> query = jdbcTemplate.query("select * from room", new BeanPropertyRowMapper<>(Room.class));
        return query;
    }
    @GetMapping("getRoomById")
    public Room getRoomById(@RequestParam(value = "id") Long id) {
        return jdbcTemplate.query("select * from room where id = ?",
                ps -> ps.setString(1,id.toString()),
                (ResultSetExtractor<Room>)rs -> rs.next() ? convert(rs) : null);
    }

    private static Room convert(ResultSet rs) throws SQLException {
        Room room = new Room();
        room.setId(rs.getLong("id"));
        room.setRoomName(rs.getString("room_name"));
        return room;
    }
}
