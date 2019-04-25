package com.waper.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.waper.entity.Book;
import com.waper.service.BookService;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;


/**
 * create by ${user} on 2019/4/25
 * *
 **/
public class BookAction extends ActionSupport {

    private BookService bookService;
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    Book book = new Book();

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String test(){
        System.out.println("进来了");
        return SUCCESS;
    }
    public String getBookById(){
       Book book =  bookService.getBookById("2");
        System.out.println(book);
        return SUCCESS ;
    }
    public String getBookList(){
       List<Book> list  = bookService.getBookList();
        ActionContext.getContext().put("list",list);
        return "list";
    }


    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }


}
