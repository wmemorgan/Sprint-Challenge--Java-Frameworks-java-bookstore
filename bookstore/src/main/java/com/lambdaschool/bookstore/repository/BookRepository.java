package com.lambdaschool.bookstore.repository;

import com.lambdaschool.bookstore.models.Book;
import com.lambdaschool.bookstore.views.JustTheCount;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface BookRepository extends CrudRepository<Book, Long> {

    @Query(value = "SELECT COUNT(*) as count FROM booksection WHERE bookid = :bookid AND sectionid = :sectionid",
            nativeQuery = true)
    JustTheCount checkBookSectionCombo(
            long bookid,
            long sectionid);
    
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO booksection(sectionid, bookid, created_by, created_date, last_modified_by, last_modified_date) VALUES (:sectionid, :bookid, :uname, CURRENT_TIMESTAMP, :uname, CURRENT_TIMESTAMP)",
            nativeQuery = true)
    void insertBookSection(
            String uname,
            long bookid,
            long sectionid);
}
