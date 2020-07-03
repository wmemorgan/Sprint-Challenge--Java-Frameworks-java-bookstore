package com.lambdaschool.bookstore.services;


import com.lambdaschool.bookstore.models.Author;

import java.util.List;

public interface AuthorService {
    List<Author> findAll();

    Author save(Author author);

    void addAuthorBook(long authorid, long bookid);
}
