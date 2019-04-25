package com.waper.service.impl;

import com.waper.dao.BookDao;
import com.waper.entity.Book;
import com.waper.service.BookService;

import java.util.List;

/**
 * create by ${user} on 2019/4/25
 * *
 **/
public class BookServiceImpl implements BookService {
    private BookDao bookDao;

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public Book getBookById(String id) {
        return bookDao.getBookById(id);
    }

    @Override
    public List<Book> getBookList() {
        return bookDao.getBookList();
    }

    @Override
    public void deleteById(String id) {
        bookDao.deleteById(id);
    }

    @Override
    public void update(Book book) {
        bookDao.update(book);
    }
}
