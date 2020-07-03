package com.lambdaschool.bookstore.services;

import com.lambdaschool.bookstore.exceptions.ResourceFoundException;
import com.lambdaschool.bookstore.exceptions.ResourceNotFoundException;
import com.lambdaschool.bookstore.models.Author;
import com.lambdaschool.bookstore.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "authorService")
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookService bookService;

    @Autowired
    UserAuditing userAuditing;

    @Override
    public List<Author> findAll() {

        List<Author> list = new ArrayList<>();

        authorRepository.findAll()
                .iterator()
                .forEachRemaining(list::add);

        return list;
    }

    @Transactional
    @Override
    public Author save(Author author) {

        Author newAuthor = new Author();

        newAuthor.setFirstname(author.getFirstname());
        newAuthor.setLastname(author.getLastname());

        return authorRepository.save(newAuthor);
    }

    @Transactional
    @Override
    public void addAuthorBook(long authorid, long bookid) {
        authorRepository.findById(authorid)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Author id " + authorid + " not found!"));
        bookService.findBookById(bookid);

        if (authorRepository.checkAuthorBookCombo(authorid,
                bookid)
            .getCount() <= 0) {
            authorRepository.insertAuthorBook(
                    userAuditing.getCurrentAuditor().get(),
                    authorid, bookid);
        }  else {
            throw new ResourceFoundException("Author and Book Combination Already Exists");
        }
    }
}
