package cn.javamap.book.controller.User;

import cn.javamap.book.pojo.BorrowBooks;
import cn.javamap.book.service.BookService;
import cn.javamap.book.service.BorrowBooksService;
import cn.javamap.book.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RequestMapping("/user/borrow")
@RestController
public class UserBorrowBookController {
    @Autowired
    private BorrowBooksService borrowBooksService;
    @Autowired
    private BookService bookService;

    /**
     * 用户点击 "借书" 按钮， 添加借书记录
     *
     * @param borrow
     * @return
     */
    @RequestMapping(value = "/ask", produces = {"text/plain;charset=utf-8"})
    public String borrowList(@RequestBody BorrowBooks borrow) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        List<BorrowBooks> allBorrow = borrowBooksService.getAllBorrow(borrow, arrayList);
        if (allBorrow != null && allBorrow.size() > 0) {
            for (BorrowBooks borrowBooks : allBorrow) {
                if (borrowBooks.getReturnDate() == null && borrow.getBookId() == borrowBooks.getBookId()
                        && borrow.getUserId() == borrowBooks.getUserId() && borrowBooks.getBorrowStatus() != 0) {
                    return "exist";
                }
            }
        }
        borrow.setBorrowStatus(2);
        borrow.setBorrowDate(DateUtil.creatDate());
        return borrowBooksService.addBorrow(borrow);
    }

    /**
     * 点击取消按钮，取消借书
     *
     * @param borrowId
     * @return
     */
    @RequestMapping(value = "/cancel", produces = {"text/plain;charset=utf-8"})
    public String borrowCancel(@RequestParam("borrowId") Integer borrowId) {
        BorrowBooks borrowBooks = borrowBooksService.getBorrowById(borrowId);
        borrowBooks.setBorrowStatus(2);
        borrowBooks.setReturnDate(DateUtil.creatDate());
        return borrowBooksService.modifyBorrow(borrowBooks);
    }

    @RequestMapping(value = "/delete", produces = {"text/plain;charset=utf-8"})
    public String deleteBorrow(@RequestParam("borrowId") Integer borrowId) {
        System.out.println("------------" + borrowId);
        BorrowBooks borrowBooks = borrowBooksService.getBorrowById(borrowId);
        if (borrowBooks.getReturnDate() != null) {
            boolean b = borrowBooksService.removeBorrow(borrowId);
            return b == true ? "success" : "less";
        }
        return "defeat";
    }
}
