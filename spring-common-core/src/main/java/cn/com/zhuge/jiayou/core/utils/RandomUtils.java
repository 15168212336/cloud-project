package cn.com.zhuge.jiayou.core.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/** 随机数生成、校验工具类
 * @author 诸葛
 * @date 2022/08/22 17:22
 **/
public class RandomUtils {


    private static final int MAX_LENGTH = 30;
    private static final int DEFAULT_MIN_LENGTH = 4;

    /**
     * 判断长度合法性
     *
     * @param length
     * @return 长度
     */
    private static int checkLength(int length) {
        if (length <= 0) {
            length = DEFAULT_MIN_LENGTH;
        } else if (length > MAX_LENGTH) {
            length = MAX_LENGTH;
        }
        return length;
    }


    /**
     * 生成指定位数随机数
     *
     * @param length
     * @return 指定长度随机数字符串
     */
    public static String getRanNum(int length) {
        length = checkLength(length);
        StringBuilder ran = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            ran = ran.append(new Random().nextInt(10));
        }
        return ran.toString();
    }

    /**
     * 生成指定位数字母，大小写混合
     *
     * @param length 长度
     * @return 指定长度随机字母符串
     */
    public static String genRanStr(int length) {
        length = checkLength(length);
        StringBuilder ran = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int temp = new Random().nextInt(2) % 2 == 0 ? 65 : 97;
            ran = ran.append((char) (temp + new Random().nextInt(26)));
        }
        return ran.toString();
    }

    /**
     * 生成指定长度数字字母组合
     *
     * @param length 长度
     * @return
     */
    public static String getRanStrNum(int length) {
        length = checkLength(length);
        StringBuilder ran = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            switch (new Random().nextInt(2) % 2) {
                case 0:
                    ran.append(getRanNum(1));
                    break;
                case 1:
                    int temp = new Random().nextInt(2) % 2 == 0 ? 65 : 97;
                    ran = ran.append((char) (temp + new Random().nextInt(26)));
                    break;
                default:
                    ran.append("0");
            }
        }
        return ran.toString();
    }


    /**
     * 生成订单类型的随机数字符串，yyyyMMddHHmmss+自定义随机数
     * @param length
     * @return
     */
    public static String getTradeNo(int length) {
        length = checkLength(length);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String format = sdf.format(new Date());
        String ran = format+getRanNum(length);
        return ran;
    }
}
