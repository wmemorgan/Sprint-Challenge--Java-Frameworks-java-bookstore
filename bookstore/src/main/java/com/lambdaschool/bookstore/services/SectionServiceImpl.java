package com.lambdaschool.bookstore.services;

import com.lambdaschool.bookstore.exceptions.ResourceNotFoundException;
import com.lambdaschool.bookstore.models.Section;
import com.lambdaschool.bookstore.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "sectionService")
public class SectionServiceImpl implements SectionService {

    @Autowired
    SectionRepository sectionRepository;

    @Override
    public List<Section> findAll() {

        List<Section> list = new ArrayList<>();

        sectionRepository.findAll()
                .iterator()
                .forEachRemaining(list::add);

        return list;
    }

    @Override
    public Section findSectionById(long id) {

        return sectionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Section id " + id + " not found!"));
    }

    @Transactional
    @Override
    public Section save(Section section) {

        Section newSection = new Section();

        newSection.setSectionname(section.getSectionname());

        return sectionRepository.save(newSection);
    }
}
