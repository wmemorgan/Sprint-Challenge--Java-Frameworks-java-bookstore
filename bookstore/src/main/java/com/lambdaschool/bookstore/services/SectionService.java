package com.lambdaschool.bookstore.services;

import com.lambdaschool.bookstore.models.Section;

import java.util.List;

public interface SectionService {

    List<Section> findAll();

    Section findBookById(long id);

    Section save(Section section);
}
