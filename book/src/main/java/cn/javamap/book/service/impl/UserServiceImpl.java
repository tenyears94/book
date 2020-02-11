package cn.javamap.book.service.impl;

import cn.javamap.book.dao.UserMapper;
import cn.javamap.book.pojo.User;
import cn.javamap.book.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 验证登录用户
     *
     * @param user
     * @return
     */
    @Override
    public User getLoginUser(User user, String key) {
        List<User> userList = userMapper.selectAllSelective(user, key);
        return (userList != null && userList.size() > 0) ? userList.get(0) : null;
    }

    /**
     * 查询所有用户
     *
     * @param user
     * @return
     */
    @Override
    public List<User> getUserList(User user, String key) {
        List<User> userList = userMapper.selectAllSelective(user, key);
        return userList;
    }

    /**
     * 修改用户信息
     *
     * @param user
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean modifyUser(User user) {
        return userMapper.updateByPrimaryKeySelective(user) > 0 ? true : false;
    }

    /**
     * 根据主键查用户
     *
     * @param userId
     * @return
     */
    @Override
    public User getUserById(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public boolean addUser(User user) {
        int i = userMapper.insertSelective(user);
        return i > 0 ? true : false;
    }
}
