package cn.javamap.book.pojo;

import java.io.Serializable;

/**
 * 对应数据表 `book` 的书籍实体类
 */
public class Book implements Serializable {
    private static final long serialVersionUID = 8396178481491052507L;
    private Integer bookId;

    private String bookName;

    private String bookAuthor;

    private String bookPublish;

    private Integer categoryId;

    private Bookcategory bookCategory;

    private Double bookPrice;

    private String bookIntroduction;

    private Integer bookNum;

    private Integer borrowNum;

    public Book(Integer bookId, String bookName, String bookAuthor, String bookPublish, Integer categoryId, Double bookPrice, String bookIntroduction, Integer bookNum, Integer borrowNum) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookPublish = bookPublish;
        this.categoryId = categoryId;
        this.bookPrice = bookPrice;
        this.bookIntroduction = bookIntroduction;
        this.bookNum = bookNum;
        this.borrowNum = borrowNum;
    }

    public Book(String bookName) {
        this.bookName = bookName;
    }

    public Book() {
        super();
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName == null ? null : bookName.trim();
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor == null ? null : bookAuthor.trim();
    }

    public String getBookPublish() {
        return bookPublish;
    }

    public void setBookPublish(String bookPublish) {
        this.bookPublish = bookPublish == null ? null : bookPublish.trim();
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Double getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(Double bookPrice) {
        this.bookPrice = bookPrice;
    }

    public String getBookIntroduction() {
        return bookIntroduction;
    }

    public void setBookIntroduction(String bookIntroduction) {
        this.bookIntroduction = bookIntroduction == null ? null : bookIntroduction.trim();
    }

    public Integer getBookNum() {
        return bookNum;
    }

    public void setBookNum(Integer bookNum) {
        this.bookNum = bookNum;
    }

    public Integer getBorrowNum() {
        return borrowNum;
    }

    public void setBorrowNum(Integer borrowNum) {
        this.borrowNum = borrowNum;
    }

    public Bookcategory getBookCategory() {
        return bookCategory;
    }

    public void setBookCategory(Bookcategory bookCategory) {
        this.bookCategory = bookCategory;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", bookId=").append(bookId);
        sb.append(", bookName=").append(bookName);
        sb.append(", bookAuthor=").append(bookAuthor);
        sb.append(", bookPublish=").append(bookPublish);
        sb.append(", categoryId=").append(categoryId);
        sb.append(", bookPrice=").append(bookPrice);
        sb.append(", bookIntroduction=").append(bookIntroduction);
        sb.append(", bookNum=").append(bookNum);
        sb.append(", borrowNum=").append(borrowNum);
        sb.append("]");
        return sb.toString();
    }
}