package com.waper.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Time;
import java.util.Date;

/**
 * create by ${user} on 2019/4/25
 * *
 **/
@Entity
public class Book {
    private String id;
    private String name;
    private long page;
    private Date publishdate;

    @Id
    @Column(name = "ID")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "PAGE")
    public long getPage() {
        return page;
    }

    public void setPage(long page) {
        this.page = page;
    }

    @Basic
    @Column(name = "PUBLISHDATE")
    public Date getPublishdate() {
        return publishdate;
    }

    public void setPublishdate(Date publishdate) {
        this.publishdate = publishdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (page != book.page) return false;
        if (id != null ? !id.equals(book.id) : book.id != null) return false;
        if (name != null ? !name.equals(book.name) : book.name != null) return false;
        if (publishdate != null ? !publishdate.equals(book.publishdate) : book.publishdate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (int) (page ^ (page >>> 32));
        result = 31 * result + (publishdate != null ? publishdate.hashCode() : 0);
        return result;
    }
}
