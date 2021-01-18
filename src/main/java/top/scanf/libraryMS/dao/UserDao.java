package top.scanf.libraryMS.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import top.scanf.libraryMS.pojo.*;
import top.scanf.libraryMS.result.CommonStatus;

import java.sql.Timestamp;

@Mapper
@Repository
public interface UserDao {
    /*
    通过用户名和用户密码获取用户信息，用于用户登录
     */
    @Select("select * from user where (username = #{username} and password = #{password} )")
    User getUserByUsernameAndPswd(@Param("username") String userName, @Param("password") String password);

    /*
    用户注册
     */
    @Insert("insert into user (username,password,email,purview_id,card_id,user_status_id) values(#{username},#{password},#{email},#{purview_id},#{card_id},#{user_status_id})")
    void addUser(@Param("username") String username, @Param("password") String password,@Param("email") String email, @Param("purview_id") int purview_id,  @Param("card_id") int card_id,  @Param("user_status_id") int user_status_id);

    /*
    根据用户名获取用户信息
     */
    @Select("select * from user where username = #{username}")
    User getUserByUsername(@Param("username") String username);

    /*
    根据用户邮箱获取用户信息
     */
    @Select("select * from user where email = #{email}")
    User getUserByEmail(@Param("email") String email);

    @Delete("delete from user where username = #{username} and password = #{password}")
    void deleteUserByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    @Update("update user set password = #{newPassword} where username = #{username}")
    void updatePasswordByUsername(@Param("newPassword") String newPassword,@Param("username") String username);

    @Select("select * from UserInfoView where username = #{username}")
    UserInfoView getUserInfoViewByUsername(@Param("username") String username);

    @Insert("insert into purview (name) values(#{name})")
    void addPurview(@Param("name") String purViewName);

    @Select("select * from purview where name = #{name}")
    Purview getPurviewByName(@Param("name") String purViewName);

    @Update("update purview set name = #{new} where name = #{old}")
    void updatePurviewByName(@Param("old") String oldPurViewName, @Param("new") String newPurViewName);

    @Select("select * from book where isbn = #{isbn}")
    Book getBookByISBN(@Param("isbn") String isbn);

    @Insert("insert into book (title,author,ISBN,category_id,present,pub_year,language,leftAmount) values(#{title},#{author},#{isbn},#{category_id},#{present},#{pub_year},#{language},#{leftAmount})")
    void addBook(@Param("title") String title, @Param("author")String author, @Param("isbn")String isbn, @Param("category_id")Integer category_id, @Param("present")String present, @Param("pub_year")Integer pub_year, @Param("language")String language, @Param("leftAmount")Integer leftAmount);

    @Insert("insert into borrow (borrow_user_id,borrow_book_id) values(#{borrow_user_id},#{borrow_book_id})")
    void addBorrow(@Param("borrow_user_id") Integer borrow_user_id,@Param("borrow_book_id") Integer borrow_book_id);

    @Select("select leftAmount from book where id = #{id}")
    Book getBookLeftAmountById(@Param("id") Integer borrow_book_id);

    @Update("update book set leftAMount = #{leftAmount} where id = #{borrow_book_id}")
    void setBookLeftAmount(Integer borrow_book_id,Integer leftAmount);

    @Select("select card_id from user where id = #{id}")
    Integer getUserCardId(@Param("id") Integer borrow_user_id);

    @Select("select * from user where id = #{id}")
    User getUserById(@Param("id") Integer borrow_user_id);

    @Select("select * from borrow where id = #{id}")
    Borrow geBorrowById(@Param("id") Integer borrow_id);

    @Select("select isReturn from borrow where id = #{id}")
    Integer getIsReturnFromBorrowById(@Param("id") Integer borrow_id);

    @Update("update borrow set isReturn = #{isReturn}, RDate = #{rtime} where id = #{id}")
    void returnBook(@Param("id") Integer borrow_id, @Param("isReturn") int i, @Param("rtime") Timestamp rTime);

    @Update("update user ")
    void getCard(String username, Integer card_id);

    @Insert("insert into card (id,status_id) values(#{id},#{status_id})")
    void addCard(@Param("id")Integer id, @Param("status_id") Integer status_id);

    @Update("update user set card_id = #{card_id} where username = #{username}")
    void giveCardToUser(String username, Integer card_id);

    @Select("select MAX(id) from card")
    Integer getMaxIdOfCard();
}
