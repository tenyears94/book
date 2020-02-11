package cn.javamap.book;

import cn.javamap.book.util.DateUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.support.TestPropertySourceUtils;
import org.springframework.util.unit.DataUnit;
import org.thymeleaf.util.DateUtils;
import org.thymeleaf.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@SpringBootTest
class BookApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void stringUtilTest() {
        System.out.println("abcdefg".contains("ac"));
        System.out.println(StringUtils.contains("abcdefg", "ac"));
    }

    @Test
    public void testTime() {
        System.out.println(DateUtil.creatDate());
        System.out.println(DateUtil.localDateTimeFormat());
        System.out.println(new Date());
    }

}
