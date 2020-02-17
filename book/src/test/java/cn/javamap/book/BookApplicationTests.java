package cn.javamap.book;

import cn.javamap.book.pojo.User;
import cn.javamap.book.service.UserService;
import cn.javamap.book.util.DateUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.support.TestPropertySourceUtils;
import org.springframework.util.unit.DataUnit;
import org.thymeleaf.util.DateUtils;
import org.thymeleaf.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@SpringBootTest
class BookApplicationTests {
    @Autowired
    private UserService userService;

    @Test
    public void testUserService(){
        System.out.println("开始debug");
        User user1 = new User();
        List<User> userList = userService.getUserList(user1, null);
        for (User user : userList) {
            System.out.println(user);
        }
    }
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

    @Test
    public void testGit(){
        System.out.println("git修改啦");
    }

    @Test
    public void testBranch(){
        System.out.println("测试新分支");
    }

}
