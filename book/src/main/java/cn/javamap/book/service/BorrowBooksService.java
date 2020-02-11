package cn.javamap.book.service;

import cn.javamap.book.pojo.BorrowBooks;

import java.util.List;

public interface BorrowBooksService {
    /**
     * 查询所有借书记录
     *
     * @param borrowBooks
     * @param idList
     * @return
     */
    List<BorrowBooks> getAllBorrow(BorrowBooks borrowBooks, List<Integer> idList);

    /**
     * 添加借书记录
     *
     * @param borrowBooks
     * @return
     */
    String addBorrow(BorrowBooks borrowBooks);

    /**
     * 根据主键查询借书类
     *
     * @param borrowId
     * @return
     */
    BorrowBooks getBorrowById(Integer borrowId);

    /****
     * 取消借书操作
     * @param borrowBooks
     * @return
     */
    String modifyBorrow(BorrowBooks borrowBooks);

    /**
     * 根据主键删除借书记录
     *
     * @param borrowId
     * @return
     */
    boolean removeBorrow(Integer borrowId);

    /**
     * 管理员操作："领书"  --> 将借书记录的借书状态改为1，借书记录变更到待归还
     * "归还"  --> 将借书记录的借书状态改为0，并添加归还时间，借书记录变更到已归还
     *
     * @param borrowBooks
     * @return
     */
    String modifyBorrowBook(BorrowBooks borrowBooks);
}
