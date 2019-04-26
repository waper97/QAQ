package com.waper.dao;

import com.waper.entity.Book;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * create by ${user} on 2019/4/25
 * *
 **/
@Transactional
public class BookDaoImpl implements  BookDao {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Book getBookById(String id) {
        Book book = new Book();
        book.setId(id);
        return sessionFactory.getCurrentSession().get(Book.class,id);
    }

    @Override
    public List<Book> getBookList(Book book) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Book where name = ?");
        query.setParameter(0,book.getName());
        return query.list();
    }

    @Override
    public void deleteById(String id) {
        Book book = new Book();
        book.setId(id);
        sessionFactory.getCurrentSession().delete(book);
    }

    @Override
    public void update(Book book) {
        sessionFactory.getCurrentSession().update(book);
    }
}
