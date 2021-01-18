package top.scanf.libraryMS.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import top.scanf.libraryMS.pojo.User;
import top.scanf.libraryMS.result.CommonStatus;
import top.scanf.libraryMS.result.LoginStatus;
import top.scanf.libraryMS.service.UserService;

import java.sql.Timestamp;
import java.util.Map;

@Component
@RestController
public class Controller {
    @Autowired
    private UserService userService;

    /*// 演示
    @RequestMapping(value="/returnString", method= RequestMethod.GET)
    public String login(@RequestParam Map map) throws JsonProcessingException {
        return (String)map.get("a");
    }*/


    /**
     * 用户登录，根据用户名和密码
     * @param map,接收前台传递过来的参数
     * @return 200：登录成功；400：登录失败
     * @throws JsonProcessingException
     */
    @RequestMapping(value="/login", method= RequestMethod.GET)
    public String login(@RequestParam Map map) throws JsonProcessingException {
        String username = (String) map.get("username");
        String password = (String) map.get("password");
        User user = userService.loginByUsernameAndPswd(username, password);
        LoginStatus rs;
        if (user != null){
            rs = new LoginStatus(200,"ok","登录成功","fsdjflksdjflkdsjlf");
        } else {
            rs =  new LoginStatus(400,"error","用户名或密码错误",null);
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(rs);
    }
    @RequestMapping(value="/signIn", method= RequestMethod.GET)
    public String signIn(@RequestParam Map map) throws Exception {
        String username = (String) map.get("username");
        String password = (String) map.get("password");
        String email = (String) map.get("email");
        Integer purview_id = Integer.parseInt((String) map.get("purview_id"));
        int card_id = 1;
        int user_status_id = 2;// 1,待激活；2，已激活，正常使用；3，已激活，禁止使用

        User user = new User(username,password,email,purview_id,card_id,user_status_id);
        CommonStatus cs = userService.signIn(user);

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(cs);
    }
    @RequestMapping(value="/deleteUser", method= RequestMethod.GET)
    //@PostMapping(value="/login")
    public String deleteUser(@RequestParam Map map) throws Exception {
        String username = (String) map.get("username");
        String password = (String) map.get("password");

        CommonStatus cs = userService.deleteUser(username,password);

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(cs);
    }
    @RequestMapping(value="/updatePassword", method= RequestMethod.GET)
    //@PostMapping(value="/login")
    public String updatePassword(@RequestParam Map map) throws Exception {
        String username = (String) map.get("username");
        String oldPassword = (String) map.get("oldPassword");
        String newPassword = (String)map.get("newPassword");

        CommonStatus cs = userService.updatePassword(username,oldPassword,newPassword);

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(cs);
    }
    @RequestMapping(value="/getUserInfo", method= RequestMethod.GET)
    //@PostMapping(value="/login")
    public String getUserInfo(@RequestParam Map map) throws Exception {
        String username = (String) map.get("username");

        CommonStatus cs = userService.getUserInfo(username);

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(cs);
    }
    @RequestMapping(value="/addPurview", method= RequestMethod.GET)
    //@PostMapping(value="/login")
    public String addPurview(@RequestParam Map map) throws Exception {
        String purViewName = (String) map.get("purViewName");

        CommonStatus cs = userService.addPurview(purViewName);

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(cs);
    }
    @RequestMapping(value="/updatePurview", method= RequestMethod.GET)
    //@PostMapping(value="/login")
    public String updatePurview(@RequestParam Map map) throws Exception {
        String oldPurViewName = (String) map.get("oldPurViewName");
        String newPurViewName = (String) map.get("newPurViewName");

        CommonStatus cs = userService.updatePurview(oldPurViewName,newPurViewName);

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(cs);
    }
    @RequestMapping(value="/addBook", method= RequestMethod.GET)
    //@PostMapping(value="/login")
    public String addBook(@RequestParam Map map) throws Exception {
        String title = (String) map.get("title");
        String author = (String) map.get("author");
        String ISBN = (String) map.get("ISBN");

        Integer category_id = Integer.parseInt((String) map.get("category_id"));
        String present = (String) map.get("present");
        Integer pub_year = Integer.parseInt((String) map.get("pub_year"));
        String language = (String) map.get("language");
        Integer leftAmount = Integer.parseInt((String) map.get("leftAmount"));

        CommonStatus cs = userService.addBook(title,author,ISBN,category_id,present,pub_year,language,leftAmount);

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(cs);
    }

    @RequestMapping(value="/addBorrow", method= RequestMethod.GET)
    //@PostMapping(value="/login")
    public String addBorrow(@RequestParam Map map) throws Exception {
        Integer borrow_user_id = Integer.parseInt((String) map.get("borrow_user_id"));
        Integer borrow_book_id = Integer.parseInt((String) map.get("borrow_book_id"));
        Integer isReturn = 0;

        CommonStatus cs = userService.addBorrow(borrow_user_id,borrow_book_id);

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(cs);
    }

    @RequestMapping(value="/returnBook", method= RequestMethod.GET)
    //@PostMapping(value="/login")
    public String returnBook(@RequestParam Map map) throws Exception {
        Integer borrow_id = Integer.parseInt((String) map.get("borrow_id"));
        Timestamp RTime = new Timestamp(System.currentTimeMillis());

        CommonStatus cs = userService.returnBook(borrow_id,RTime);

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(cs);
    }

    @RequestMapping(value="/getCard", method= RequestMethod.GET)
    //@PostMapping(value="/login")
    public String getCard(@RequestParam Map map) throws Exception {
        String username = (String) map.get("username");

        CommonStatus cs = userService.getCard(username);

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(cs);
    }
    @RequestMapping(value="/queryBook", method= RequestMethod.GET)
    //@PostMapping(value="/login")
    public String queryBookId(@RequestParam Map map) throws Exception {
        String ISBN = (String) map.get("ISBN");

        CommonStatus cs = userService.getBook(ISBN);

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(cs);
    }

}
