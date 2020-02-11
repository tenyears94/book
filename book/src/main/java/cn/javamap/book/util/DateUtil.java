package cn.javamap.book.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 日期工具类
 */
public class DateUtil {
    /**
     * LocalDateTime获取当前日期，DateTimeFormatter（线程安全）格式化
     *
     * @return
     */
    public static String localDateTimeFormat() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(dateTimeFormatter);
    }

    public static Date creatDate() {
        return new Date();
    }
}
