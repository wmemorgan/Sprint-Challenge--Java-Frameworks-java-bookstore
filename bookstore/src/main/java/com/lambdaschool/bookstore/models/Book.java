package com.lambdaschool.bookstore.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The entity allowing interaction with the books table
 */
@Entity
@Table(name = "books")
public class Book extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bookid;

    @Column(nullable = false)
    private String booktitle;

    @Column(nullable = false, unique = true)
    private String isbn;

    private int copyrightyear;

    @OneToOne(mappedBy = "book")
    private SectionBooks sectionBooks;

    /**
     * Part of the join relationship between book and author
     * connects books to the book author combination
     */
    @ApiModelProperty(name = "authors",
            value = "List of authors for this book")
    @OneToMany(mappedBy = "book",
        cascade = CascadeType.ALL,
        orphanRemoval = true)
    @JsonIgnoreProperties(value = "book",
        allowSetters = true)
    private List<AuthorBooks> authors = new ArrayList<>();

    public Book() {
    }

    public Book(String booktitle, String isbn, int copyrightyear, SectionBooks sectionBooks) {
        this.booktitle = booktitle;
        this.isbn = isbn;
        this.copyrightyear = copyrightyear;
        this.sectionBooks = sectionBooks;
    }

    public Book(String booktitle, String isbn, int copyrightyear, SectionBooks sectionBooks, List<AuthorBooks> authors) {
        this.booktitle = booktitle;
        this.isbn = isbn;
        this.copyrightyear = copyrightyear;
        this.sectionBooks = sectionBooks;
        this.authors = authors;
    }

    public long getBookid() {
        return bookid;
    }

    public void setBookid(long bookid) {
        this.bookid = bookid;
    }

    public String getBooktitle() {
        return booktitle;
    }

    public void setBooktitle(String booktitle) {
        this.booktitle = booktitle;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getCopyrightyear() {
        return copyrightyear;
    }

    public void setCopyrightyear(int copyrightyear) {
        this.copyrightyear = copyrightyear;
    }

    public SectionBooks getSectionBooks() {
        return sectionBooks;
    }

    public void setSectionBooks(SectionBooks sectionBooks) {
        this.sectionBooks = sectionBooks;
    }

    public List<AuthorBooks> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorBooks> authors) {
        this.authors = authors;
    }
}
