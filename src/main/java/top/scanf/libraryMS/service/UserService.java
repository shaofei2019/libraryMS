package top.scanf.libraryMS.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.scanf.libraryMS.dao.UserDao;
import top.scanf.libraryMS.pojo.*;
import top.scanf.libraryMS.result.CommonStatus;

import java.sql.Timestamp;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public User loginByUsernameAndPswd(String userName, String password) {
        User user = userDao.getUserByUsernameAndPswd(userName, password);
        return user;
    }

    public CommonStatus signIn(User user) {
        User userA = userDao.getUserByUsername(user.getUsername());

        // 检查用户名是否已经存在
        if (userA == null){
            //用户名不存在，再检查邮箱
            User userB = userDao.getUserByEmail(user.getEmail());
            if (userB == null){
                userDao.addUser(user.getUsername(),user.getPassword(),user.getEmail(),user.getPurview_id(),user.getCard_id(),user.getUser_status_id());

                return new CommonStatus(200,"注册成功","注册成功，用户名:" + user.getUsername());
            } else {
                // 改邮箱已被注册使用
                return new CommonStatus(401,"注册失败","该邮箱已被注册");
            }


        } else {
            // 用户名已经存在，不可以注册
            return new CommonStatus(400,"注册失败","该用户名已经存在");
        }
    }

    public CommonStatus deleteUser(String username, String password) {
        // 先验证用户名和密码是否匹配
        User user = userDao.getUserByUsernameAndPswd(username,password);
        if (user == null){
            return new CommonStatus(400,"删除失败","用户名密码不匹配");
        } else {
            userDao.deleteUserByUsernameAndPassword(username,password);
            return new CommonStatus(200,"删除成功","成功删除" + username);
        }
    }


    public CommonStatus updatePassword(String username, String oldPassword, String newPassword) {
        if (oldPassword.equals(newPassword)){
            return new CommonStatus(401,"修改失败","新旧密码不能一致");
        } else if ("".equals(newPassword)){
            return new CommonStatus(402,"修改失败","新密码不能为空");
        }else{
            // 先验证用户名和密码是否匹配
            User user = userDao.getUserByUsernameAndPswd(username,oldPassword);
            if (user == null){
                return new CommonStatus(400,"修改失败","用户名密码不匹配");
            } else {
                userDao.updatePasswordByUsername(newPassword,username);
                return new CommonStatus(200,"修改成功","修改成功用户名：" + username);
            }
        }

    }

    public CommonStatus getUserInfo(String username) {
        UserInfoView view = userDao.getUserInfoViewByUsername(username);
        // 判断用户是否存在
        if (view == null){
            return new CommonStatus(400,"查询失败","用户不存在");
        } else {
            return new CommonStatus(200,"查询成功",view.toString());
        }
    }

    public CommonStatus addPurview(String purViewName) {
        // 先查询purviewName是否已经存在
        Purview purview = userDao.getPurviewByName(purViewName);
        if (purview == null){
            return new CommonStatus(400,"添加失败","角色已经存在");
        } else {
            userDao.addPurview(purViewName);
            return new CommonStatus(200,"添加成功","角色" + purViewName + "添加成功");
        }
    }

    public CommonStatus updatePurview(String oldPurViewName, String newPurViewName) {
        if (oldPurViewName.equals(newPurViewName)){
            return new CommonStatus(401,"修改失败","新旧角色名称不能一致");
        } else if ("".equals(newPurViewName)){
            return new CommonStatus(402,"修改失败","新角色名称不能为空");
        }else{
            // 先验证旧角色是否存在
            Purview purview = userDao.getPurviewByName(oldPurViewName);
            if (purview == null){
                return new CommonStatus(400,"修改失败","旧角色不存在");
            } else {
                userDao.updatePurviewByName(oldPurViewName,newPurViewName);
                return new CommonStatus(200,"修改成功","旧角色名称：" + oldPurViewName + ",改为：" + newPurViewName);
            }
        }
    }

    public CommonStatus addBook(String title, String author, String isbn, Integer category_id, String present, Integer pub_year, String language, Integer leftAmount) {
        // 先检查该图书是否存在，isbn为唯一标识
        Book book = userDao.getBookByISBN(isbn);
        if (book != null){
            return new CommonStatus(400,"添加失败","书本已经存在");
        } else {
            userDao.addBook(title,author,isbn,category_id,present,pub_year,language,leftAmount);
            return new CommonStatus(200,"添加成功","成功添加一本书，书名：" + title);
        }
    }

    public CommonStatus addBorrow(Integer borrow_user_id, Integer borrow_book_id) {
        //检测用户是否存在
        User user = userDao.getUserById(borrow_user_id);
        if (user != null){
            // 检测用户是否有借书卡
            if (this.haveCard(borrow_user_id)){
                // 检查图书剩余数量是否大于0
                if (userDao.getBookLeftAmountById(borrow_book_id).getLeftAmount() > 0){
                    // 新增借阅
                    userDao.addBorrow(borrow_user_id,borrow_book_id);
                    //图书剩余数量 --
                    Integer curAmount = userDao.getBookLeftAmountById(borrow_book_id).getLeftAmount();
                    userDao.setBookLeftAmount(borrow_book_id,curAmount-1);

                    return new CommonStatus(200,"借阅成功","借阅成功");
                } else {
                    return new CommonStatus(400,"借阅失败","借阅失败，图书余量不足");
                }
            } else {
                return new CommonStatus(401,"借阅失败","该用户未办理借书卡，无法借书");
            }
        } else {
            return new CommonStatus(402,"借阅失败","用户不存在");
        }




    }

    private boolean haveCard(Integer borrow_user_id) {
        Integer cardId = userDao.getUserCardId(borrow_user_id);
        if (cardId == 1) {
            return false;
        } else {
            return true;
        }
    }

    public CommonStatus returnBook(Integer borrow_id, Timestamp rTime) {
        // 验证借阅记录是否存在
        Borrow borrow = userDao.geBorrowById(borrow_id);
        if (borrow != null) {
            // 检测是否已经归还
            if (this.isBorrowReturn(borrow_id)) {
                return new CommonStatus(400,"归还失败","已经归还，无需再次归还");
            } else {
                userDao.returnBook(borrow_id,1,rTime);
                return new CommonStatus(200,"归还成功","归还成功");
            }
        } else {
            return new CommonStatus(401,"归还失败","无借阅记录");
        }
    }

    private boolean isBorrowReturn(Integer borrow_id) {
        Integer isReturn = userDao.getIsReturnFromBorrowById(borrow_id);
        if (isReturn == 1) {
            return true;
        }else {
            return false;
        }
    }

    public CommonStatus getCard(String username) {
        // 验证用户是否存在
        if (userDao.getUserByUsername(username) != null){
            Integer card_id = userDao.getMaxIdOfCard() + 1;
            Integer status_id = 2;
            // 添加一张卡
            userDao.addCard(card_id,status_id);
            // 将该卡分配给用户
            userDao.giveCardToUser(username,card_id);
            return new CommonStatus(200,"申请成功","申请成功，CardId：" + card_id);
        } else {
            return new CommonStatus(400,"申请失败","用户不存在");
        }
    }

    public CommonStatus getBook(String ISBN) {
        Book book = userDao.getBookByISBN(ISBN);
        if (book == null){
            return new CommonStatus(400,"查询失败","该图书不存在");
        } else {
            return new CommonStatus(200,"查询成功","图书信息如下：" + book );
        }
    }
}
