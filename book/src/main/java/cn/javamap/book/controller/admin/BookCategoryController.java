package cn.javamap.book.controller.admin;

import cn.javamap.book.pojo.Bookcategory;
import cn.javamap.book.service.BookCategoryService;
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
@RequestMapping("/category")
public class BookCategoryController {
    @Autowired
    private BookCategoryService bookCategoryService;

    /**
     * 进入书籍类别添加页面，要展示所有书籍类别
     *
     * @param model
     * @return
     */
    @GetMapping("/addCategory")
    public String addCategory(Model model, Page page) {
        if (page == null) {
            page = new Page();
        }
//      PageHelper.startPage要放在查询前面，不然没效果
        PageHelper.startPage(page.getPageNo(), 5);
        List<Bookcategory> bookCategoryList1 = bookCategoryService.getAllCategory();
        PageInfo<Bookcategory> bookCategoryList = new PageInfo<>(bookCategoryList1);
        model.addAttribute("bookCategoryList", bookCategoryList);

        page.setTotalNum(bookCategoryList.getTotal());   //总条数
        page.setPageCount(bookCategoryList.getPages());  //总页数
        page.setPageNo(bookCategoryList.getPageNum());   //当前页
        page.setPageSize(bookCategoryList.getPageSize()); //每页数量

        model.addAttribute("page", page);
        ArrayList<Integer> pageList = new ArrayList<>();
        for (int i = 1; i <= page.getPageCount(); i++) {
            pageList.add(i);
        }
        model.addAttribute("pageList", pageList);
        return "admin/addCategory";
    }

    /**
     * 异步验证添加书籍名是否存在
     *
     * @param categoryName
     * @return
     */
    @PostMapping(value = "/checkCategory", produces = {"text/plain;charset=utf-8"})
    @ResponseBody
    public String checkCategory(String categoryName) {
        boolean b = bookCategoryService.getCategoryName(categoryName);
        return b == true ? "exist" : "noExist";
    }

    /**
     * 表单添加书籍类型
     *
     * @param bookcategory
     * @return
     */
    @PostMapping("/addCategory")
    public String addCategory(Bookcategory bookcategory) {
        if (!bookCategoryService.getCategoryName(bookcategory.getCategoryName())) {
            boolean b = bookCategoryService.addCategory(bookcategory);
        }
        return "redirect:/category/addCategory";
    }

    /**
     * 超链接修改书籍类型名称
     *
     * @param categoryId
     * @param model
     * @return
     */
    @GetMapping(value = "/update/{categoryId}", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Bookcategory updateBookCategory(@PathVariable("categoryId") int categoryId, Model model) {
        Bookcategory category = bookCategoryService.getBookCategoryById(categoryId);
        return category;
    }

    /**
     * 表单修改类型名称
     *
     * @param categoryId
     * @param bookCategory
     * @param model
     * @return
     */
    @PostMapping(value = "/update/{categoryId}", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Bookcategory updateBookCategory(@PathVariable("categoryId") int categoryId, Bookcategory bookCategory, Model model) {
//        System.out.println("===========>进入修改"+"<==============");
        boolean b = bookCategoryService.modifyBookCategory(bookCategory);
        if (b) {
//            System.out.println("===========>进入修改146435"+"<==============");
            Bookcategory category = bookCategoryService.getBookCategoryById(categoryId);
            return category;
        } else {
            return null;
        }
    }

    @GetMapping("/delete/{categoryId}")
    public String deleteBookCategory(@PathVariable("categoryId") int categoryId, Model model) {
        boolean b = bookCategoryService.removeBookCategory(categoryId);
        if (b) {
            return "redirect:/category/addCategory";
        } else {
            return "error/error";
        }
    }
}
