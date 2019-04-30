package com.waper.service.impl;

import com.waper.dao.BookMapper;
import com.waper.entity.Book;
import com.waper.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * create by ${user} on 2019/4/30
 * *
 **/
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookMapper bookMapper;
    @Override
    public List<Book> getBookList(Map<String, Object> params) {
        return bookMapper.getBookList(params);
    }

    @Override
    public Book getBookById(String id) {
        return bookMapper.getBookById(id);
    }

    @Override
    public Integer deleteById(String id) {
        return bookMapper.deleteById(id);
    }

    @Override
    public Integer updateById(Book book) {
        return bookMapper.updateById(book);
    }
}
