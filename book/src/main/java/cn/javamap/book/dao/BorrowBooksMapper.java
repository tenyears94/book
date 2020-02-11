package cn.javamap.book.dao;

import cn.javamap.book.pojo.BorrowBooks;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowBooksMapper {
    int deleteByPrimaryKey(Integer borrowId);

    int insert(BorrowBooks record);

    int insertSelective(BorrowBooks record);

    BorrowBooks selectByPrimaryKey(Integer borrowId);

    int updateByPrimaryKeySelective(BorrowBooks record);

    int updateByPrimaryKey(BorrowBooks record);

    List<BorrowBooks> selectAllSelective(@Param("borrow") BorrowBooks record, @Param("idList") List<Integer> idList);
}