package top.scanf.libraryMS.pojo;

import java.sql.Timestamp;

public class Borrow {
    private Integer id;
    private Integer borrow_user_id;
    private Integer borrow_book_id;
    private Timestamp BDate;
    private Integer isReturn;
    private Timestamp RDate;

    public Borrow() {
    }

    public Borrow(Integer id, Integer borrow_user_id, Integer borrow_book_id, Timestamp BDate, Integer isReturn, Timestamp RDate) {
        this.id = id;
        this.borrow_user_id = borrow_user_id;
        this.borrow_book_id = borrow_book_id;
        this.BDate = BDate;
        this.isReturn = isReturn;
        this.RDate = RDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBorrow_user_id() {
        return borrow_user_id;
    }

    public void setBorrow_user_id(Integer borrow_user_id) {
        this.borrow_user_id = borrow_user_id;
    }

    public Integer getBorrow_book_id() {
        return borrow_book_id;
    }

    public void setBorrow_book_id(Integer borrow_book_id) {
        this.borrow_book_id = borrow_book_id;
    }

    public Timestamp getBDate() {
        return BDate;
    }

    public void setBDate(Timestamp BDate) {
        this.BDate = BDate;
    }

    public Integer getIsReturn() {
        return isReturn;
    }

    public void setIsReturn(Integer isReturn) {
        this.isReturn = isReturn;
    }

    public Timestamp getRDate() {
        return RDate;
    }

    public void setRDate(Timestamp RDate) {
        this.RDate = RDate;
    }

    @Override
    public String toString() {
        return "Borrow{" +
                "id=" + id +
                ", borrow_user_id=" + borrow_user_id +
                ", borrow_book_id=" + borrow_book_id +
                ", BDate=" + BDate +
                ", isReturn=" + isReturn +
                ", RDate=" + RDate +
                '}';
    }
}
