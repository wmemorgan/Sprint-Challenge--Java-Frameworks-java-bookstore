package com.lambdaschool.bookstore.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "authors")
public class Author extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long authorid;

    private String lastname;

    private String firstname;

    @OneToMany(mappedBy = "author",
        cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "author",
     allowSetters = true)
    private List<AuthorBooks> books = new ArrayList<>();

    public Author() {
    }

    public Author(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public long getAuthorid() {
        return authorid;
    }

    public void setAuthorid(long authorid) {
        this.authorid = authorid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public List<AuthorBooks> getBooks() {
        return books;
    }

    public void setBooks(List<AuthorBooks> books) {
        this.books = books;
    }
}
