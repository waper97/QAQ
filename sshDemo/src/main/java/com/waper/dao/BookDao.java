package com.waper.dao;

import com.waper.entity.Book;

import java.util.List;

/**
 * create by ${user} on 2019/4/25
 * *
 **/
public interface BookDao {
    public Book getBookById(String id);
    //查询所有书
    List<Book> getBookList(Book book);

    void deleteById(String id);

    void update(Book book);

    List<Book> getBookByName(Book book);
}
