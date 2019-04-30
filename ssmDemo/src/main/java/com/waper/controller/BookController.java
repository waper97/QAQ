package com.waper.controller;

import com.waper.entity.Book;
import com.waper.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * create by ${user} on 2019/4/30
 * *
 **/
@Controller
//@RequestMapping("/test")
public class BookController {
    @Autowired
    BookService bookService;

    @RequestMapping("/getBookList")
    @ResponseBody
    public ModelAndView  getBookList(HttpServletRequest request){
        ModelAndView mv = new ModelAndView("index");
        Map<String,Object> params = new HashMap<>();
        List<Book> bookList = bookService.getBookList(params);

        request.setAttribute("list",bookList);

        return mv;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping("/deleteById")
    @ResponseBody
    public Object deleteById(String id){
       int result =  bookService.deleteById(id);
        return new BaseController(null);
    }

    /**
     * 修改
     * @param book
     * @return
     */
    @RequestMapping("/updateBook")
    @ResponseBody
    public Object updateBook(Book book){
     int result =  bookService.updateById(book);
        return new BaseController(null);
    }
    /**
     * 条件查询
     * @param id
     * @return
     */
    public Object getBookById(String id){
     Book book =   bookService.getBookById(id);
     return  new BaseController(book);
    }
}
