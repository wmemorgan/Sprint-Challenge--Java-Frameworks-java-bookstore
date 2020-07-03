package com.lambdaschool.bookstore.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

/**
 * The entity allowing interaction with the sectionbooks table.
 * The join table between users and roles.
 * <p>
 * Table enforces a unique constraint of the combination of sectionid and bookid.
 * These two together form the primary key.
 * <p>
 * When you have a compound primary key, you must implement Serializable for Hibernate
 * When you implement Serializable you must implement equals and hash code
 */
@Entity
@Table(name = "sectionbooks",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"sectionid", "bookid"})})
public class SectionBooks extends Auditable {

    @Id
    @ManyToOne
    @JoinColumn(name = "sectionid")
    @JsonIgnoreProperties(value = "books",
        allowSetters = true)
    private Section section;

    @Id
    @OneToOne
    @JoinColumn(name = "bookid")
    @JsonIgnoreProperties(value = "sections",
        allowSetters = true)
    private Book book;

    public SectionBooks() {
    }

    public SectionBooks(Section section, Book book) {
        this.section = section;
        this.book = book;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
