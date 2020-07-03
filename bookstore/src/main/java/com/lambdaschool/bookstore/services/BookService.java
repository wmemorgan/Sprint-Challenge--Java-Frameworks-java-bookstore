package com.lambdaschool.bookstore.services;

import com.lambdaschool.bookstore.models.Book;

import java.util.List;

public interface BookService {

    List<Book> findAll();

    Book findBookById(long id);

    Book save(Book book);

    void addBookSection(long bookid, long sectionid);
}
