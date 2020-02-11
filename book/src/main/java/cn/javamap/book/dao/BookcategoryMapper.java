package cn.javamap.book.dao;

import cn.javamap.book.pojo.Bookcategory;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookcategoryMapper {
    int deleteByPrimaryKey(Integer categoryId);

    int insert(Bookcategory record);

    int insertSelective(Bookcategory record);

    Bookcategory selectByPrimaryKey(Integer categoryId);

    int updateByPrimaryKeySelective(Bookcategory record);

    int updateByPrimaryKey(Bookcategory record);

    List<Bookcategory> selectAll();
}