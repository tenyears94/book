package cn.javamap.book.service;

import cn.javamap.book.pojo.Book;
import cn.javamap.book.pojo.Bookcategory;

import java.util.List;

public interface BookCategoryService {
    /**
     * 查询所有书籍类别
     *
     * @return
     */
    List<Bookcategory> getAllCategory();

    /**
     * 添加图书类别时，异步验证类别名是否存在
     *
     * @return
     */
    boolean getCategoryName(String categoryName);

    /**
     * 添加书籍类型
     *
     * @param bookCategory
     * @return
     */
    boolean addCategory(Bookcategory bookCategory);

    /**
     * 通过表单修改图书类型
     *
     * @param bookCategory
     * @return
     */
    boolean modifyBookCategory(Bookcategory bookCategory);

    /**
     * 通过bookId删除书
     *
     * @param categoryId
     * @return
     */
    boolean removeBookCategory(int categoryId);

    /**
     * 根据主键ID查询Bookcategory
     *
     * @param categoryId
     * @return
     */
    Bookcategory getBookCategoryById(int categoryId);
}
