package cn.javamap.book.controller;

import cn.javamap.book.pojo.Dept;
import cn.javamap.book.pojo.User;
import cn.javamap.book.service.DeptService;
import cn.javamap.book.service.UserService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 公共操作，用户登录，注册，注销
 */
@Controller
@RequestMapping("/user")
@SessionAttributes("login_user")
public class LoginUserController {
    @Autowired
    private UserService userService;
    @Autowired
    private DeptService deptService;

    /**
     * 超链接进入首页
     *
     * @return
     */
    @GetMapping("/index")
    public String index() {
        return "index";
    }

    /**
     * 超链接进入登录页面
     *
     * @return
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * 表单验证登录
     * 问题点： @SessionAttributes注解model.addAttribute只能在index页面有效，所有加了个session.setAttribute
     *
     * @param user
     * @param model
     * @param session
     * @return
     */
    @PostMapping("/login")
    public String login(User user, Model model, HttpSession session, String key) {
        User user1 = userService.getLoginUser(user, key);
        if (user1 != null) {
            if (user1.getUserStatus() == 1) {
                model.addAttribute("loginMessage", "该账户已被禁用，请联系管理员！");
                return "login";
            }
            model.addAttribute("login_user", user1);
            session.setAttribute("login_user", user1);
            return "index";
        }
        model.addAttribute("loginMessage", "账户或密码错误，请重新登录！");
        return "login";
//        return "redirect:/user/login";
    }

    /**
     * 进入注册页面
     *
     * @return
     */
    @GetMapping("/reg")
    public String reg(Model model) {
        List<Dept> deptList = deptService.getAllDept();
        model.addAttribute("deptList", deptList);
        return "user/reg";
    }

    /**
     * 表单注册用户
     *
     * @param user
     * @param model
     * @return
     */
    @PostMapping("/reg")
    public String reg(User user, Model model) {
        boolean b = userService.addUser(user);
        if (b) {
            return "redirect:/user/login";
        } else {
            model.addAttribute("user", user);
            model.addAttribute("message", "注册失败，请重新注册");
            return "user/reg";
        }
    }

    /**
     * 退出登录
     *
     * @param sessionStatus
     * @return
     */
    @GetMapping("/logout")
    public String logout(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "index";
    }

    /**
     * 异步验证注册用户名
     *
     * @param userName
     * @param key
     * @return
     */
    @PostMapping("/checkUserName")
    @ResponseBody
    public String checkUserName(String userName, String key) {
//        System.out.println("--------->" + userName + "<--------------");
        User user = new User(userName);
        User loginUser = userService.getLoginUser(user, key);
        return loginUser == null ? "noExist" : "exist";
    }
}
