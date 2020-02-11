package cn.javamap.book.controller.admin;

import cn.javamap.book.pojo.Dept;
import cn.javamap.book.pojo.User;
import cn.javamap.book.service.DeptService;
import cn.javamap.book.service.UserService;
import cn.javamap.book.util.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private DeptService deptService;

    /**
     * 进入网站用户展示页面
     *
     * @param model
     * @param page
     * @param key
     * @return
     */
    @RequestMapping("/list")
    public String list(User user, Model model, Page page, String key, Integer id) {
        List<User> targetList = null;
        if (page == null) {
            page = new Page();
        }
        if (id != null) {
            switch (id) {
                case 0:
                    break;
                case 1:
                    user.setUserRole("普通用户");
                    break;
                case 2:
                    user.setUserRole("管理员");
                    break;
                default:
                    return "error/error";
            }
        }

        System.out.println("------------->" + user.getUserRole() + "<-----------------");
//      PageHelper.startPage要放在查询前面，不然没效果
        PageHelper.startPage(page.getPageNo(), 5);
        if (StringUtils.isEmpty(key)) {
            targetList = userService.getUserList(user, key);
        } else {
            System.out.println("key====>" + key);
            targetList = userService.getUserList(new User(), key.trim());
        }
        PageInfo<User> userList = new PageInfo<>(targetList);
        model.addAttribute("userList", userList);
        if (key != null) {
            model.addAttribute("key", key);
        }
        page.setTotalNum(userList.getTotal());   //总条数
        page.setPageCount(userList.getPages());  //总页数
        page.setPageNo(userList.getPageNum());   //当前页
        page.setPageSize(userList.getPageSize()); //每页数量

        model.addAttribute("page", page);
        ArrayList<Integer> pageList = new ArrayList<>();
        for (int i = 1; i <= page.getPageCount(); i++) {
            pageList.add(i);
        }
        model.addAttribute("pageList", pageList);
        switch (id) {
            case 0:
                return "user/user/userRole";
            case 1:
                return "user/user/list";
            case 2:
                return "user/user/adminUserList";
            default:
                return "error/error";
        }
    }

    /**
     * 修改用户状态和角色授权
     *
     * @param userId
     * @param user
     * @return
     */
    @GetMapping("/update/{userId}")
    public String updateStatus(@PathVariable("userId") int userId, User user, Integer id) {
        boolean b = userService.modifyUser(user);
        return b == true ? "redirect:/admin/user/list?id=" + id : "error/error";
    }

    /**
     * 携带登录用户的信息进入用户资料页面
     *
     * @param user
     * @param model
     * @return
     */
    @GetMapping("/updateMessage")
    public String updateMessage(@SessionAttribute("login_user") User user, Model model, SessionStatus status) {
        List<Dept> deptList = deptService.getAllDept();
        User userById = userService.getUserById(user.getUserId());
        model.addAttribute("deptList", deptList);
        model.addAttribute("user", userById);
        /*if (user.getUserPwd() != userById.getUserPwd()){
            status.setComplete();
            return "login";
        }*/
        return "user/info";
    }

    /**
     * 修改用户信息,回显
     *
     * @param userId
     * @param model
     * @param user
     * @return
     */
    @PostMapping("/updateMessage/{userId}")
    public String updateMessage(@PathVariable("userId") Integer userId, @SessionAttribute("login_user") User user1, Model model, User user, SessionStatus status) {
        boolean b = userService.modifyUser(user);
        User userById = userService.getUserById(userId);
        if (!user1.getUserPwd().equals(userById.getUserPwd())) {
            /*System.out.println("等于"+user1.getUserPwd() == userById.getUserPwd());
            System.out.println("equals"+user1.getUserPwd().equals(userById.getUserPwd()));
            System.out.println("旧密码---------->"+user1.getUserPwd());
            System.out.println("新密码---------->"+userById.getUserPwd());*/
            status.setComplete();
            return "login";
        }
        return b == true ? "redirect:/admin/user/updateMessage" : "error/error";
    }

    /**
     * 进入修改密码页面
     *
     * @param user
     * @param model
     * @return
     */
    @GetMapping("/updatePassword")
    public String updatePassword(@SessionAttribute("login_user") User user, Model model) {
        model.addAttribute("user", user);
        return "user/password";
    }

    /**
     * 异步验证旧用户密码是否正确
     *
     * @param oldPass
     * @return
     */
    @PostMapping(value = "/checkPassword", produces = {"text/plain;charset=utf-8"})
    @ResponseBody
    public String checkPassword(@SessionAttribute("login_user") User user, String oldPass) {
//        System.out.println("---------验证密码-------");
        return oldPass.trim().equals(user.getUserPwd()) ? "exist" : "noExist";
    }
}
