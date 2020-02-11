package cn.javamap.book.service;

import cn.javamap.book.pojo.Dept;

import java.util.List;

public interface DeptService {
    /**
     * 查询所有学院信息
     *
     * @return
     */
    List<Dept> getAllDept();
}
