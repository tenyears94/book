package cn.javamap.book.service;

import cn.javamap.book.pojo.Book;

import java.util.List;

public interface BookService {
    /**
     * 查询所有书籍
     *
     * @param book
     * @return
     */
    List<Book> getAllBook(Book book);

    /**
     * 添加书籍
     *
     * @param book
     * @return
     */
    boolean addBook(Book book);

    /**
     * 通过表单修改图书
     *
     * @param book
     * @return
     */
    boolean modifyBook(Book book);

    /**
     * 通过bookId查询书
     *
     * @param bookId
     * @return
     */
    Book getBookById(int bookId);

    /**
     * 通过bookId删除书
     *
     * @param bookId
     * @return
     */
    boolean removeBook(int bookId);
}
