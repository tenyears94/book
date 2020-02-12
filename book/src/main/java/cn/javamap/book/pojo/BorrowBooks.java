package cn.javamap.book.pojo;

import java.io.Serializable;
import java.util.Date;

public class BorrowBooks implements Serializable {
    private static final long serialVersionUID = 1250393098155879463L;
    private Integer borrowId;

    private Integer userId;

    private Integer bookId;

    //    0-->归还时间不为空，表示"已归还"
//    1-->表示书籍已借，"待归还"
//    2-->表示用户下单，进入"待处理"状态; 归还时间不为空表示"已取消"
    private Integer borrowStatus;

    private Date borrowDate;

    //    private Date returnDate;
    private Date returnDate;

    private User user;

    private Book book;

    public BorrowBooks(Integer borrowId, Integer userId, Integer bookId, Integer borrowStatus, Date borrowDate, Date returnDate) {
        this.borrowId = borrowId;
        this.userId = userId;
        this.bookId = bookId;
        this.borrowStatus = borrowStatus;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public BorrowBooks() {
        super();
    }

    public Integer getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(Integer borrowId) {
        this.borrowId = borrowId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getBorrowStatus() {
        return borrowStatus;
    }

    public void setBorrowStatus(Integer borrowStatus) {
        this.borrowStatus = borrowStatus;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "BorrowBooks{" +
                "borrowId=" + borrowId +
                ", userId=" + userId +
                ", bookId=" + bookId +
                ", borrowStatus=" + borrowStatus +
                ", borrowDate=" + borrowDate +
                ", returnDate=" + returnDate +
                ", user=" + user +
                ", book=" + book +
                '}';
    }
}