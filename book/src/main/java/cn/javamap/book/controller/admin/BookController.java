package cn.javamap.book.controller.admin;

import cn.javamap.book.pojo.Book;
import cn.javamap.book.pojo.Bookcategory;
import cn.javamap.book.service.BookCategoryService;
import cn.javamap.book.service.BookService;
import cn.javamap.book.util.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private BookCategoryService bookCategoryService;

    /**
     * 携带book集合进入查询书籍页面
     *
     * @param book
     * @param model
     * @return
     */
    @RequestMapping("/searchBook")
    public String searchBook(Book book, Model model, Page page) {
        if (page == null) {
            page = new Page();
        }
//      PageHelper.startPage要放在查询前面，不然没效果
        PageHelper.startPage(page.getPageNo(), 5);
        List<Book> bookList1 = bookService.getAllBook(book);
        PageInfo<Book> bookList = new PageInfo<>(bookList1);
        model.addAttribute("bookList", bookList);
        if (book != null) {
            model.addAttribute("book", book);
        }
        page.setTotalNum(bookList.getTotal());   //总条数
        page.setPageCount(bookList.getPages());  //总页数
        page.setPageNo(bookList.getPageNum());   //当前页
        page.setPageSize(bookList.getPageSize()); //每页数量

        model.addAttribute("page", page);
        ArrayList<Integer> pageList = new ArrayList<>();
        for (int i = 1; i <= page.getPageCount(); i++) {
            pageList.add(i);
        }
        model.addAttribute("pageList", pageList);
        return "admin/searchBook";
    }

    /**
     * 超链接进入添加书籍的页面
     *
     * @return
     */
    @GetMapping("/addBook")
    public String addBook(Model model) {
        List<Bookcategory> bookCategoryList = bookCategoryService.getAllCategory();
        model.addAttribute("bookCategoryList", bookCategoryList);
        return "admin/addBook";
    }

    /**
     * 表单添加书籍
     *
     * @param book
     * @param model
     * @return
     */
    @PostMapping("/addBook")
    public String addBook(Book book, Model model) {
        boolean b = bookService.addBook(book);
        if (b) {
            return "redirect:/book/searchBook";
        } else {
            model.addAttribute("book", book);
            return "redirect:/book/addBook";
        }
    }

    /**
     * 超链接进入添加书籍的页面
     *
     * @return
     */
    @GetMapping("/update/{bookId}")
    public String updateBook(@PathVariable("bookId") int bookId, Model model) {
        Book book = bookService.getBookById(bookId);
        List<Bookcategory> bookCategoryList = bookCategoryService.getAllCategory();
        model.addAttribute("bookCategoryList", bookCategoryList);
        model.addAttribute("book", book);
        return "admin/updateBook";
    }

    /**
     * 根据ID修改book
     *
     * @param bookId
     * @param book
     * @param model
     * @return
     */
    @PostMapping("/update/{bookId}")
    public String updateBook(@PathVariable("bookId") int bookId, Book book, Model model) {
        boolean b = bookService.modifyBook(book);
        if (b) {
            return "redirect:/book/searchBook";
        } else {
            model.addAttribute("book", book);
            List<Bookcategory> bookCategoryList = bookCategoryService.getAllCategory();
            model.addAttribute("bookCategoryList", bookCategoryList);
            return "admin/updateBook";
        }
    }

    /**
     * 根据ID删除book
     *
     * @param bookId
     * @param model
     * @return
     */
    @GetMapping("/delete/{bookId}")
    public String delete(@PathVariable("bookId") int bookId, Model model) {
        boolean b = bookService.removeBook(bookId);
        if (b) {
            return "redirect:/book/searchBook";
        } else {
            return "error/error";
        }
    }

    /**
     * 错误页面
     *
     * @return
     */
    @GetMapping("/error")
    public String error() {
        return "error/error";
    }

    @GetMapping("/error4xx")
    public String error4xx() {
        return "error/4xx";
    }

    @GetMapping("/error404")
    public String error404() {
        return "error/404";
    }
}
