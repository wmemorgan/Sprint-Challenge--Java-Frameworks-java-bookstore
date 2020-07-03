package com.lambdaschool.bookstore.services;

import com.lambdaschool.bookstore.exceptions.ResourceFoundException;
import com.lambdaschool.bookstore.exceptions.ResourceNotFoundException;
import com.lambdaschool.bookstore.models.Book;
import com.lambdaschool.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "bookService")
public class BookServiceImpl
        implements BookService
{
    @Autowired
    BookRepository bookRepository;

    @Autowired
    SectionService sectionService;

    @Autowired
    UserAuditing userAuditing;

    @Override
    public List<Book> findAll() {
        List<Book> list = new ArrayList<>();

        bookRepository.findAll()
                .iterator()
                .forEachRemaining(list::add);

        return list;
    }

    @Override
    public Book findBookById(long id) {
        return bookRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("" +
                                "Book id " + id + " not found!"));
    }

    @Transactional
    @Override
    public Book save(Book book) {

        return bookRepository.save(book);
    }

    @Transactional
    @Override
    public void delete(long bookid) {
        bookRepository.findById(bookid)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Book id " + bookid + " not found!"));
        bookRepository.deleteById(bookid);
    }

    @Transactional
    @Override
    public void addBookSection(long bookid, long sectionid) {
        bookRepository.findById(bookid)
                .orElseThrow(() -> new ResourceNotFoundException("Book id " + bookid + " not found!"));
        sectionService.findSectionById(sectionid);

        if (bookRepository.checkBookSectionCombo(bookid, sectionid)
        .getCount() <= 0) {
            bookRepository.insertBookSection(
                    userAuditing.getCurrentAuditor().get(),
                    bookid, sectionid);
        } else {
            throw new ResourceFoundException("Book and Section Combination Already Exists");
        }

    }
}
