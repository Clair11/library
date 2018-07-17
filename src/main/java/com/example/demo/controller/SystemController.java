package com.example.demo.controller;

import com.example.demo.constant.CommonCode;
import com.example.demo.dto.BookDto;
import com.example.demo.dto.UserDto;
import com.example.demo.exception.SpringBootException;
import com.example.demo.pojo.Book;
import com.example.demo.pojo.Borrow;
import com.example.demo.pojo.User;
import com.example.demo.service.BookService;
import com.example.demo.service.BorrowMapperService;
import com.example.demo.service.UserService;
import com.example.demo.shiro1.realm.MyRealm2;
import com.example.demo.util.ResponseVoUtil;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * @Author :caijx
 * @Description:
 * @Date :Created in 16:07 2018/5/3
 * @Modified By:
 */
@Controller
public class SystemController {
    @Autowired
    UserService userService;

    @Autowired
    BookService bookService;

    @Autowired
    BorrowMapperService borrowMapperService;

    @Autowired
    private MyRealm2 myRealm2;

    @Autowired
    private DefaultSecurityManager defaultSecurityManager;


    @RequestMapping("/start")
    public String start(){
        return "account";
    }

    @RequestMapping("/public/login")
    public String login(){
        return "login";
    }


    @RequestMapping("/public/toLogin")
    @ResponseBody
    public String toLogin(UserDto userDto){
        if(userDto.getUserName() != null & userDto.getAccount()!=null &userDto.getPassword()!=null){
            userService.insertSelective(userDto);
            return ResponseVoUtil.success(userDto);
        }else{
            throw new SpringBootException(CommonCode.PARAMERROR);
        }
    }

    @RequestMapping("/public/toAccount")
    public String testUser(UserDto userDto){
            if(userDto == null){
                throw new SpringBootException(CommonCode.PARAMERROR);
            }
           // SecurityUtils.setSecurityManager(defaultSecurityManager);
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(userDto.getAccount().toString(),userDto.getPassword());
            subject.login(token); //系统会自动去调/用realm
            return "index";

    }

    @RequestMapping("/toSuccess")
    public String toSuccess(){
        return "index";
    }

    @RequestMapping("/toAboutMe")
    public String toAboutMe(){
        return "aboutMe";
    }

    @RequestMapping("/toAddUser")
    public ModelAndView toAddUser(){
        ModelAndView v = new ModelAndView();
        v.setViewName("manager");
        v.addObject("page","addUser.html");
        return v;
    }

    @RequestMapping("/toDeleteUser")
    public ModelAndView toDeleteUser(){
        ModelAndView view = new ModelAndView();
        view.setViewName("manager");
        view.addObject("page","deleteUser.html");
        return view;
    }

    @RequestMapping("/deleteUser")
    @ResponseBody
    public String deleteUser(UserDto userDto){
        if(userDto != null){
            Integer acc = userDto.getAccount();
            userService.deleteByAccount(acc);
            return ResponseVoUtil.success();
        }else{
            throw new SpringBootException(CommonCode.PARAMERROR);
        }
    }

    @RequestMapping("/toUpdateUser")
    public ModelAndView toUpdateUser(){
        ModelAndView v = new ModelAndView();
        v.setViewName("manager");
        v.addObject("page","updateUser.html"); //點擊查看后给一个指定的页面
        return v;
    }

    @RequestMapping("/updateUser")
    @ResponseBody
    public String updateUser(UserDto userDto){
        if(userDto != null){
            userService.updateByName(userDto);
            return ResponseVoUtil.success();
        }else{
            throw new SpringBootException(CommonCode.PARAMERROR);
        }
    }
/*
    @RequestMapping("/changeStatus")
    @ResponseBody
    public String changeStatus(){

        return ResponseVoUtil.success();
    }*/

    @RequestMapping("/selectUser")
    public ModelAndView selectUser(HttpServletRequest request, HttpServletResponse res){
        ModelAndView v = new ModelAndView();
        List<User> list = userService.select();
        v.setViewName("manager");
        v.addObject("list",list);
        v.addObject("page","userView.html"); //點擊查看后给一个指定的页面

       /* List<User> list = userService.select();
        Map<String,Object> map = new HashMap<>();
        map.put("list",list);
        String jsonStr = JSON.toJSONString(map);*/
        return v;
    }

    @RequestMapping("/addBook")
    public ModelAndView toAdd(){
        ModelAndView v = new ModelAndView();
        v.setViewName("manager");
        v.addObject("page","addBook");
        return v;
    }

    @RequestMapping("/add/book")
    @ResponseBody
    public String addBook(BookDto bookDto){
        if(bookDto != null){
            bookService.insertSelective(bookDto);
            return ResponseVoUtil.success();
        }else{
            throw new SpringBootException(CommonCode.PARAMERROR);
        }
    }

    @RequestMapping("/deleteBook")
    public ModelAndView toDeleteBook(){
        ModelAndView v = new ModelAndView();
        v.setViewName("manager");
        v.addObject("page","deleteBook");
        return v;
    }

    @RequestMapping("/delete/book")
    @ResponseBody
    public String deleteBook(BookDto bookDto){
        if(bookDto != null){
            String name = bookDto.getBookName();
            bookService.deleteByName(name);
            return ResponseVoUtil.success();
        }else{
            throw new SpringBootException(CommonCode.PARAMERROR);
        }

    }

    @RequestMapping("/toUpdateBook")
    public ModelAndView toUpdateBook(){
        ModelAndView v = new ModelAndView();
        v.setViewName("manager");
        v.addObject("page","updateBook");
        return v;
    }

    @RequestMapping("/updateBook")
    @ResponseBody
    public String updateBook(BookDto bookDto){
        if(bookDto != null){
            bookService.updateByPrimaryKeySelective(bookDto);
            return ResponseVoUtil.success();
        }else{
            throw new SpringBootException(CommonCode.PARAMERROR);
        }
    }

    @RequestMapping("/get/first/page/book")
    public ModelAndView getBooks(){
        ModelAndView v = new ModelAndView("bookView");
        BookDto bookDto = new BookDto();
        List<Book> list = bookService.select(bookDto);
        v.addObject("list",list);
        return v;
    }

    @RequestMapping("/get/all/book")
    public ModelAndView getAllBook(){
        ModelAndView view = new ModelAndView();
        BookDto bookDto = new BookDto();
        List<Book> list = bookService.select(bookDto);
        view.setViewName("manager");
        view.addObject("list",list);
        view.addObject("page","getAllBook.html");
        return view;
    }

    @RequestMapping("/get/xx/page/book")

    public ModelAndView getBooks(BookDto bookDto){  //传入当前页码
        if(bookDto == null){
            throw new SpringBootException(CommonCode.PARAMERROR);
        }
        ModelAndView v = new ModelAndView("bookView");
        List<Book> list = bookService.select(bookDto);
        v.addObject("list",list);
        return v;
    }

    @RequestMapping("/toBorrow")
    public String toBorrow(){
        return "borrow";
    }

    @RequestMapping("/borrow")
    @ResponseBody
    public String borrow(Borrow borrow1){
        if(borrow1.getUserId()==null | borrow1.getBookId() == null){
            throw new SpringBootException(CommonCode.PARAMERROR);
        }else{
            Book book = bookService.selectByPrimaryKey(borrow1.getBookId());
            if(book.getIsBorrow()==false & book.getIsSell()==false){
                Book book1 = book;
                book1.setIsBorrow(true);
                bookService.updateByPrimaryKey(book1);
                borrowMapperService.insertSelective(borrow1);
                return ResponseVoUtil.success();
            }else{
                throw new SpringBootException(CommonCode.BORROWERROR);
            }
        }
    }

    @RequestMapping("/toManager")
    public ModelAndView toManager(){
        ModelAndView v = new ModelAndView();
        v.setViewName("manager");
        v.addObject("page","test.html");//先給div一個空白頁
        return v;
    }


    @RequestMapping("/toBack")
    public String toBack(){
        return "back";
    }

    @RequestMapping("/back")
    @ResponseBody
    public String back(Borrow borrow){
        if(borrow.getUserId()==null | borrow.getBookId() == null){
            throw new SpringBootException(CommonCode.PARAMERROR);
        }else{
            Integer bookId = borrowMapperService.getBookId(borrow);
            if(borrow.getBookId() == bookId){
                Book book = bookService.selectByPrimaryKey(bookId);
                book.setIsBorrow(false);
                bookService.updateByPrimaryKey(book);
                return ResponseVoUtil.success();
            }else{
                throw new SpringBootException(CommonCode.BACKERROR);
            }
        }
    }
}
