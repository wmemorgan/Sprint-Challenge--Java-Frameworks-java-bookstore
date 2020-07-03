package com.lambdaschool.bookstore.repository;

import com.lambdaschool.bookstore.models.Author;
import com.lambdaschool.bookstore.views.JustTheCount;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface AuthorRepository extends CrudRepository<Author, Long> {

    @Query(value = "SELECT COUNT(*) as count FROM authorbooks WHERE authorid = :authorid AND bookid = :bookid",
            nativeQuery = true)
    JustTheCount checkAuthorBookCombo(
            long authorid,
            long bookid);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO authorbooks(bookid, authorid, created_by, created_date, last_modified_by, last_modified_date) VALUES (:bookid, :authorid, :uname, CURRENT_TIMESTAMP, :uname, CURRENT_TIMESTAMP)",
            nativeQuery = true)
    void insertAuthorBook(
            String uname,
            long authorid,
            long bookid);
}
