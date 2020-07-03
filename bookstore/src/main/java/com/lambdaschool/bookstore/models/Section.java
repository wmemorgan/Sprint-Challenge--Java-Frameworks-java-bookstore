package com.lambdaschool.bookstore.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The entity allowing interaction with the sections table
 */
@Entity
@Table(name = "sections")
public class Section extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long sectionid;

    @Column(nullable = false, unique = true)
    private String sectionname;

    @ApiModelProperty(name = "section books",
            value = "List of books in this section")
    @OneToMany(mappedBy = "book",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonIgnoreProperties(value = "book",
            allowSetters = true)
    private List<Book> books = new ArrayList<>();

    public Section() {
    }

    public Section(String sectionname) {
        this.sectionname = sectionname;
    }

    public long getSectionid() {
        return sectionid;
    }

    public void setSectionid(long sectionid) {
        this.sectionid = sectionid;
    }

    public String getSectionname() {
        return sectionname;
    }

    public void setSectionname(String sectionname) {
        this.sectionname = sectionname;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
