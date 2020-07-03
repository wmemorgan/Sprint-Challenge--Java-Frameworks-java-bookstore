package com.lambdaschool.bookstore.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

/**
 * The entity allowing interaction with the authorbooks table.
 * The join table between authors and books.
 * <p>
 * Table enforces a unique constraint of the combination of authorid and bookid.
 * These two together form the primary key.
 * <p>
 * When you have a compound primary key, you must implement Serializable for Hibernate
 * When you implement Serializable you must implement equals and hash code
 */
@Entity
@Table(name = "authorbooks",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"authorid", "bookid"})})
public class AuthorBooks extends Auditable {

    @Id
    @ManyToOne
    @JoinColumn(name = "authorid")
    @JsonIgnoreProperties(value = "books",
        allowSetters = true)
    private Author author;

    @Id
    @ManyToOne
    @JoinColumn(name = "bookid")
    @JsonIgnoreProperties(value = "authors",
        allowSetters = true)
    private Book book;

    public AuthorBooks() {
    }

    public AuthorBooks(Author author, Book book) {
        this.author = author;
        this.book = book;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
