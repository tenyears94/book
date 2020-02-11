package cn.javamap.book.dao;

import cn.javamap.book.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 动态查询所有，user为空时，查询所有
     *
     * @param user
     * @param key
     * @return
     */
    List<User> selectAllSelective(@Param("user") User user, @Param("key") String key);
}