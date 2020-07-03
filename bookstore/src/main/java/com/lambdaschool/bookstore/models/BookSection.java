package com.lambdaschool.bookstore.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "booksection",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"bookid", "sectionid"})})
public class BookSection extends Auditable
        implements Serializable
{
    @Id
    @ManyToOne
    @JoinColumn(name = "bookid")
    @JsonIgnoreProperties(value = "booksections",
            allowSetters = true)
    private Book book;

    @Id
    @ManyToOne
    @JoinColumn(name = "sectionid")
    @JsonIgnoreProperties(value = "books",
        allowSetters = true)
    private Section section;

    public BookSection() {
    }

    public BookSection(Book book, Section section) {
        this.book = book;
        this.section = section;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookSection that = (BookSection) o;
        return section.equals(that.section) &&
                book.equals(that.book);
    }

    @Override
    public int hashCode() {
        return Objects.hash(section, book);
    }
}
