package cn.javamap.book.service.impl;

import cn.javamap.book.dao.BookMapper;
import cn.javamap.book.dao.BorrowBooksMapper;
import cn.javamap.book.pojo.Book;
import cn.javamap.book.pojo.BorrowBooks;
import cn.javamap.book.service.BorrowBooksService;
import cn.javamap.book.util.DateUtil;
import com.sun.scenario.effect.impl.prism.PrRenderInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BorrowBooksServiceImpl implements BorrowBooksService {
    @Autowired
    private BorrowBooksMapper borrowBooksMapper;
    @Autowired
    private BookMapper bookMapper;

    /**
     * 查询所有借书记录
     *
     * @param borrowBooks
     * @return
     */
    @Override
    public List<BorrowBooks> getAllBorrow(BorrowBooks borrowBooks, List<Integer> idList) {
        List<BorrowBooks> borrowBooksList = borrowBooksMapper.selectAllSelective(borrowBooks, idList);
        return borrowBooksList;
    }

    /**
     * 借书操作
     * --> 1. 判断是否有可借数量，失败返回-->"less"
     * --> 2. 更新书籍类借出数量，成功返回-->"success"，失败返回-->"defeat"
     *
     * @param borrowBooks
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String addBorrow(BorrowBooks borrowBooks) {
        if (borrowBooks != null && borrowBooks.getBookId() != null) {
            Book book = bookMapper.selectByPrimaryKey(borrowBooks.getBookId());
            if (book.getBookNum() <= book.getBorrowNum()) {
                return "less";
            } else {
                book.setBorrowNum(book.getBorrowNum() + 1);
                int i = bookMapper.updateByPrimaryKeySelective(book);
                if (i > 0) {
                    borrowBooksMapper.insertSelective(borrowBooks);
//                    int a = 1/0;验证事务
                    return "success";
                }
                return "defeat";
            }
        }
        return "defeat";
    }

    /**
     * 取消借书操作
     *
     * @param borrowBooks
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String modifyBorrow(BorrowBooks borrowBooks) {
        if (borrowBooks != null && borrowBooks.getBookId() != null) {
            Book book = bookMapper.selectByPrimaryKey(borrowBooks.getBookId());
            if (book.getBorrowNum() > 0) {
                book.setBorrowNum(book.getBorrowNum() - 1);
                int i = bookMapper.updateByPrimaryKeySelective(book);
                if (i > 0) {
                    borrowBooksMapper.updateByPrimaryKeySelective(borrowBooks);
                    return "success";
                }
                return "defeat";
            }
            return "less";
        }
        return "defeat";
    }


    /**
     * 根据主键删除借书记录
     *
     * @param borrowId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeBorrow(Integer borrowId) {
        return borrowBooksMapper.deleteByPrimaryKey(borrowId) > 0 ? true : false;
    }

    /**
     * 管理员操作："领书"  --> 将借书记录的借书状态改为1，借书记录变更到待归还
     * "归还"  --> 将借书记录的借书状态改为0，并添加归还时间，借书记录变更到已归还
     *
     * @param borrowBooks
     * @return
     */
    @Override
    public String modifyBorrowBook(BorrowBooks borrowBooks) {
        if (borrowBooks != null && borrowBooks.getBorrowStatus() == 0) {
            borrowBooks.setReturnDate(DateUtil.creatDate());
        }
        int i = borrowBooksMapper.updateByPrimaryKeySelective(borrowBooks);
        return i > 0 ? "success" : "defeat";
    }

    /**
     * 根据主键查询借书类信息
     *
     * @param borrowId
     * @return
     */
    @Override
    public BorrowBooks getBorrowById(Integer borrowId) {
        return borrowBooksMapper.selectByPrimaryKey(borrowId);
    }
}
