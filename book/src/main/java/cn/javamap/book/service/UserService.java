package cn.javamap.book.service;

import cn.javamap.book.pojo.User;

import java.util.List;

public interface UserService {
    /**
     * 验证登录用户
     *
     * @param user
     * @return
     */
    User getLoginUser(User user, String key);

    /**
     * 查询所有用户
     *
     * @param user
     * @return
     */
    List<User> getUserList(User user, String key);

    /**
     * 修改用户信息
     *
     * @param user
     * @return
     */
    boolean modifyUser(User user);

    /**
     * 根据用户id查询用户
     *
     * @param userId
     * @return
     */
    User getUserById(Integer userId);

    boolean addUser(User user);
}
