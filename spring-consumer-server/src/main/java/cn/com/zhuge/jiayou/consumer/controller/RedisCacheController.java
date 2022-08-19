//package cn.com.zhuge.jiayou.consumer.controller;
//
//
//import cn.com.zhuge.jiayou.common.redisson.context.LockType;
//import cn.com.zhuge.jiayou.consumer.entity.Room;
//import cn.com.zhuge.jiayou.common.redisson.annotation.RedisLock;
//import org.redisson.api.RBucket;
//import org.redisson.api.RedissonClient;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.DataAccessException;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.PreparedStatementCreator;
//import org.springframework.jdbc.core.ResultSetExtractor;
//import org.springframework.jdbc.support.GeneratedKeyHolder;
//import org.springframework.jdbc.support.KeyHolder;
//import org.springframework.web.bind.annotation.*;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//@RestController
//public class RedisCacheController {
//
//    private static String ROOM_INSERT_SQL = "insert into room (room_name,room_no,unit_id,is_show) values (?,?,?,?)";
//    private static String ROOM_UPDATE_SQL = "update room set room_name = ? , room_no = ? , unit_id = ? ,is_show = ? where id = ?";
//    private static String ROOM_DELETE_SQL = "delete from room where id = ?";
//    private static String ROOM_SELECT_BY_ID_SQL = "select * from room where id = ?";
//
//    @Autowired
//    private RedissonClient redisson;
//
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//
//
//    /**
//     * 房间新增，先新增再更新缓存
//     * @param room
//     */
//    @GetMapping("roomAdd")
//    public void roomAdd(@RequestBody Room room) {
//        KeyHolder keyHolder = new GeneratedKeyHolder();
//        jdbcTemplate.update(new PreparedStatementCreator() {
//            @Override
//            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
//                PreparedStatement preparedStatement = con.prepareStatement(ROOM_INSERT_SQL);
//                preparedStatement.setString(1, room.getRoomName());
//                preparedStatement.setString(2, room.getRoomNo());
//                preparedStatement.setLong(3, room.getUnitId());
//                preparedStatement.setByte(4, room.getIsShow());
//                return preparedStatement;
//            }
//        }, keyHolder);
//
//        long roomId = keyHolder.getKey().longValue();
//        RBucket<Room> rBucket = redisson.getBucket("room"+roomId);
//        rBucket.set(room);
//
//
//    }
//
//    /**
//     * 房间删除，先删除缓存再更新数据库
//     *
//     * @param roomId
//     */
//    @GetMapping("roomDelete")
//    public void roomDeleteAfterRedis(@RequestParam("roomId") long roomId) {
//
//        RBucket<Room> rBucket = redisson.getBucket("room" + roomId);
//        rBucket.delete();
//        jdbcTemplate.update(ROOM_DELETE_SQL, roomId);
//
//    }
//
//    @GetMapping("roomGetById")
//    public Room roomGetById(@RequestParam("roomId") long roomId) {
//        RBucket<Room> rBucket = redisson.getBucket("room" + roomId);
//        if (rBucket.get() != null) {
//            return rBucket.get();
//        }
//        Room room = jdbcTemplate.query(ROOM_SELECT_BY_ID_SQL, new ResultSetExtractor<Room>() {
//            @Override
//            public Room extractData(ResultSet rs) throws SQLException, DataAccessException {
//                Room r = new Room();
//                while (rs.next()) {
//                    r.setId(rs.getLong("id"));
//                    r.setRoomName(rs.getString("room_name"));
//                    r.setRoomNo(rs.getString("room_no"));
//                    r.setValidState(rs.getByte("valid_state"));
//                }
//                return r;
//            }
//        }, roomId);
//        rBucket.set(room);
//        return room;
//    }
//
//    private static int i = 0;
//
//    @RedisLock(lockType = LockType.REENTRANT_LOCK,lockKey = "BMP-TENANT-SERVER_TENANT_RENT_CUD")
//    @GetMapping("lockTest")
//    public void reentrantLockTest() {
//        System.out.println(i+"======in");
//        i++;
//        System.out.println(i+"======out");
////        try {
////            TimeUnit.SECONDS.sleep(1);
////        } catch (InterruptedException e) {
////            e.printStackTrace();
////        }
//
//    }
//
//}
