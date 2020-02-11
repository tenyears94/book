package cn.javamap.book.controller.admin;

import cn.javamap.book.pojo.Book;
import cn.javamap.book.pojo.BorrowBooks;
import cn.javamap.book.service.BookService;
import cn.javamap.book.service.BorrowBooksService;
import cn.javamap.book.util.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/borrow")
public class BorrowBooksController {
    @Autowired
    private BorrowBooksService borrowBooksService;
    @Autowired
    private BookService bookService;

    /**
     * 进入 "借书记录" 页面 + 页面搜索功能
     *
     * @param borrowBooks
     * @param model
     * @param key
     * @param page
     * @return
     */
    @RequestMapping("/list")
    public String borrowList(BorrowBooks borrowBooks, Model model, String key, Page page, Integer userId) {
        if (page == null) {
            page = new Page();
        }
        if (!StringUtils.isEmpty(userId)) {
            borrowBooks.setUserId(userId);
        }
        //      PageHelper.startPage要放在查询前面，不然没效果
        PageHelper.startPage(page.getPageNo(), 5);
        List<Integer> idList = new ArrayList<>();
        if (!StringUtils.isEmpty(key)) {
            List<Book> allBook = bookService.getAllBook(new Book(key.trim()));
            for (Book book : allBook) {
                idList.add(book.getBookId());
            }
        }
        List<BorrowBooks> allBorrow = borrowBooksService.getAllBorrow(borrowBooks, idList);
        PageInfo<BorrowBooks> borrowBooksList = new PageInfo<>(allBorrow);

        model.addAttribute("borrowBooksArrayList", borrowBooksList);
        page.setTotalNum(borrowBooksList.getTotal());   //总条数
        page.setPageCount(borrowBooksList.getPages());  //总页数
        page.setPageNo(borrowBooksList.getPageNum());   //当前页
        page.setPageSize(borrowBooksList.getPageSize()); //每页数量

        model.addAttribute("page", page);
        ArrayList<Integer> pageList = new ArrayList<>();
        for (int i = 1; i <= page.getPageCount(); i++) {
            pageList.add(i);
        }
        model.addAttribute("pageList", pageList);
        model.addAttribute("key", key);
        model.addAttribute("userId", userId);
        if (borrowBooks != null && borrowBooks.getBorrowStatus() != null) {
            model.addAttribute("status", borrowBooks.getBorrowStatus());
        }
        if (borrowBooks != null && borrowBooks.getBorrowStatus() != null && 2 == borrowBooks.getBorrowStatus()) {
            return "admin/pendingBorrow";
        }
        return "admin/borrowBook";
    }

    /**
     * 管理员"归还"和用户的"借书"功能，更新借书记录的状态
     *
     * @param borrow
     * @return
     */
    @RequestMapping(value = "/update", produces = {"text/plain;charset=utf-8"})
    @ResponseBody
    public String updateBorrow(@RequestBody BorrowBooks borrow) {
        return borrowBooksService.modifyBorrowBook(borrow);
    }
}
