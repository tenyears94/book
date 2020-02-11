package cn.javamap.book.service.impl;

import cn.javamap.book.dao.DeptMapper;
import cn.javamap.book.pojo.Dept;
import cn.javamap.book.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;

    @Override
    public List<Dept> getAllDept() {
        return deptMapper.selectAll();
    }
}
