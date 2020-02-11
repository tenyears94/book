package cn.javamap.book.service.impl;

import cn.javamap.book.dao.BookMapper;
import cn.javamap.book.pojo.Book;
import cn.javamap.book.pojo.User;
import cn.javamap.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BookServiceImpl implements BookService {
    @Autowired
    private BookMapper bookMapper;

    /**
     * 查询所有书籍,搜索查询是根据"book_name"简单查询
     *
     * @param book
     * @return
     */
    @Override
    public List<Book> getAllBook(Book book) {
        List<Book> bookList = bookMapper.selectAllSelective(book);
        return bookList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addBook(Book book) {
        int i = bookMapper.insertSelective(book);
//        int j = 1/0;  试验事务回滚
        return i > 0 ? true : false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean modifyBook(Book book) {
        int i = bookMapper.updateByPrimaryKeySelective(book);
        return i > 0 ? true : false;
    }

    @Override
    public Book getBookById(int bookId) {
        Book book = bookMapper.selectByPrimaryKey(bookId);
        return book;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeBook(int bookId) {
        int i = bookMapper.deleteByPrimaryKey(bookId);
        return i > 0 ? true : false;
    }
}
