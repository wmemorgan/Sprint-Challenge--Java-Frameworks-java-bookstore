package com.lambdaschool.bookstore.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "book",
        cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "book", allowSetters = true)
    private List<BookSection> bookSections = new ArrayList<>();

    public Book() {
    }

    public Book(String booktitle, String isbn, int copyrightyear) {
        this.booktitle = booktitle;
        this.isbn = isbn;
        this.copyrightyear = copyrightyear;
    }

    public Book(
            String booktitle,
            String isbn,
            int copyrightyear,
            Section section) {
        this.booktitle = booktitle;
        this.isbn = isbn;
        this.copyrightyear = copyrightyear;

        bookSections.add(new BookSection(this, section));
    }

    public Book(
            String booktitle,
            String isbn,
            int copyrightyear,
            List<BookSection> bookSections
            ) {
        this.booktitle = booktitle;
        this.isbn = isbn;
        this.copyrightyear = copyrightyear;

        for (BookSection c : bookSections) {
            c.setBook(this);
            this.bookSections.add(c);
        }
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

    public List<BookSection> getBookSections() {
        return bookSections;
    }

    public void setBookSections(List<BookSection> bookSections) {
        this.bookSections = bookSections;
    }
}
