package cn.javamap.book.service.impl;

import cn.javamap.book.dao.BookcategoryMapper;
import cn.javamap.book.pojo.Book;
import cn.javamap.book.pojo.Bookcategory;
import cn.javamap.book.service.BookCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
@Transactional
public class BookCategoryServiceImpl implements BookCategoryService {
    @Autowired
    private BookcategoryMapper bookcategoryMapper;

    /**
     * 查询所有书籍
     *
     * @return
     */
    @Override
    public List<Bookcategory> getAllCategory() {
        List<Bookcategory> bookCategoryList = bookcategoryMapper.selectAll();
        return bookCategoryList;
    }

    /**
     * 异步验证类别名是否存在，false不存在，true存在
     *
     * @param categoryName
     * @return
     */
    @Override
    public boolean getCategoryName(String categoryName) {
        List<Bookcategory> bookCategoryList = bookcategoryMapper.selectAll();
        Bookcategory targetBookCategory = null;
        for (Bookcategory bookcategory : bookCategoryList) {
            if (categoryName.equals(bookcategory.getCategoryName())) {
                targetBookCategory = bookcategory;
                break;
            }
        }
        return targetBookCategory == null ? false : true;
    }

    /**
     * 添加书籍类型
     *
     * @param bookCategory
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addCategory(Bookcategory bookCategory) {
        int i = bookcategoryMapper.insertSelective(bookCategory);
        return i > 0 ? true : false;
    }

    /**
     * 通过ID修改书籍类型
     *
     * @param bookCategory
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean modifyBookCategory(Bookcategory bookCategory) {
        int i = bookcategoryMapper.updateByPrimaryKeySelective(bookCategory);
        return i > 0 ? true : false;
    }

    /**
     * 通过ID删除书籍类型
     *
     * @param categoryId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeBookCategory(int categoryId) {
        int i = bookcategoryMapper.deleteByPrimaryKey(categoryId);
        return i > 0 ? true : false;
    }

    /**
     * 根据主键ID查询Bookcategory
     *
     * @param categoryId
     * @return
     */
    @Override
    public Bookcategory getBookCategoryById(int categoryId) {
        return bookcategoryMapper.selectByPrimaryKey(categoryId);
    }


}
