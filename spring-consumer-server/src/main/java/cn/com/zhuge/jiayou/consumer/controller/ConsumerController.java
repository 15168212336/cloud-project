package cn.com.zhuge.jiayou.consumer.controller;


import cn.com.zhuge.jiayou.consumer.entity.RoomOpenLog;
import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.*;

@RestController
public class ConsumerController {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("consumer")
    public String consumer(@RequestParam("tag") String tag) {
        synchronized (tag.intern()) {
            try {
                System.out.println(tag + "-------in");
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "tag is " + tag;
        }
    }

    public String test() {
        return null;
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

            List<Map<String, Object>> query = jdbcTemplate.queryForList("select r.id from room r" );
            //关闭连接
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(1);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, () -> {
            System.out.println("等待线程执行");
        });

        for (int i = 1; i <= 5; i++) {
            new Thread(() -> {

                try {
                    System.out.println("线程"+Thread.currentThread().getName()+"执行中");
                    Random random = new Random();
                    long i1 = random.nextInt(5);
                    System.out.println(i1);
                    TimeUnit.SECONDS.sleep(i1);
                    System.out.println("线程"+Thread.currentThread().getName()+"执行完成");
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }

//        for (int i = 1; i <= 6; i++) {
//            new Thread(() -> {
//                try {
//                    System.out.println("线程等待");
//                    semaphore.acquire();
//                    System.out.println(Thread.currentThread().getName()+"抢占成功");
//                    TimeUnit.SECONDS.sleep(3);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }finally {
//                    semaphore.release();
//                    System.out.println(Thread.currentThread().getName()+"释放");
//                }
//            },String.valueOf(i)).start();
//        }
    }
    private BlockingQueue<RoomOpenLog> blockingQueue = new ArrayBlockingQueue<RoomOpenLog>(200);
    private static ThreadPoolExecutor threadPoolExecutor;
    static {
        threadPoolExecutor = new ThreadPoolExecutor(2,
                16,
                5,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }


    private static long max = 0;
    @PostMapping("insertLog")
    public String inputLog(@RequestBody RoomOpenLog roomOpenLog) throws SQLException {


        try {
            if (!putQueue(roomOpenLog)){
                return "time out";
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        threadPoolExecutor.execute(() -> {
            String insertLog = "insert into room_open_log (room_id,open_lock_time,open_lock_type,card_id,fingerprint_id,open_lock_result,create_time) values (?,?,?,?,?,?,?)";
            RoomOpenLog roomOpenLog1 = blockingQueue.poll();
            jdbcTemplate.update(insertLog, roomOpenLog1.getRoomId(), roomOpenLog1.getOpenLockTime(), roomOpenLog1.getOpenLockType(), roomOpenLog1.getCardId(), roomOpenLog1.getFingerprintId(), roomOpenLog1.getOpenLockResult(),roomOpenLog1.getCreateTime());
        });
        return "success";




    }

    @PostMapping("insertLog2")
    public void inputLog2(@RequestBody RoomOpenLog roomOpenLog) throws SQLException {

        String insertLog = "insert into room_open_log (room_id,open_lock_time,open_lock_type,card_id,fingerprint_id,open_lock_result,create_time) values (?,?,?,?,?,?,?)";
        jdbcTemplate.update(insertLog, roomOpenLog.getRoomId(), roomOpenLog.getOpenLockTime(), roomOpenLog.getOpenLockType(), roomOpenLog.getCardId(), roomOpenLog.getFingerprintId(), roomOpenLog.getOpenLockResult(),roomOpenLog.getCreateTime());
    }

    private boolean putQueue(RoomOpenLog roomOpenLog) throws InterruptedException {
        return blockingQueue.offer(roomOpenLog, 20, TimeUnit.SECONDS);


    }
    @GetMapping("getMax")
    public long getMax() {
        System.out.println("druidDataSource 数据源最大连接数：" + max);
        return max;
    }

    class Solution {
        public int longestStrChain(String[] words) {
            List<String> list = Arrays.asList(words);
            return list.size();
        }
    }
}
