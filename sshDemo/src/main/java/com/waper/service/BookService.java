package com.waper.service;


import com.waper.entity.Book;

import java.util.List;

/**
 * create by ${user} on 2019/4/25
 * *
 **/
public interface BookService {
    public Book getBookById(String id);

    List<Book> getBookList();

    void deleteById(String id);

    void update(Book book);

}
