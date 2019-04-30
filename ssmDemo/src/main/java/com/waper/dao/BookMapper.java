package com.waper.dao;

import com.waper.entity.Book;

import java.util.List;
import java.util.Map;

/**
 * create by ${user} on 2019/4/30
 * *
 **/
public interface BookMapper {
    List<Book> getBookList(Map<String,Object> params);

    Book getBookById(String id);

    Integer deleteById(String id);

    Integer updateById(Book book);

}
