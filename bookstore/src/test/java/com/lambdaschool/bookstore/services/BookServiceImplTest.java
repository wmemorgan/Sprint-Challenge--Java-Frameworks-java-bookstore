package com.lambdaschool.bookstore.services;

import com.lambdaschool.bookstore.BookstoreApplication;
import com.lambdaschool.bookstore.exceptions.ResourceNotFoundException;
import com.lambdaschool.bookstore.models.Book;
import com.lambdaschool.bookstore.models.Section;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BookstoreApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
//**********
// Note security is handled at the controller, hence we do not need to worry about security here!
//**********
public class BookServiceImplTest
{

    @Autowired
    private BookService bookService;

    @Autowired
    private SectionService sectionService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        System.out.println("\n*** BEFORE ***");
        List<Book> mylist = bookService.findAll();

        for (Book u : mylist) {
            System.out.println(u.getBookid() + " " + u.getTitle());
        }

        System.out.println();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("\n*** AFTER ***");
        List<Book> mylist = bookService.findAll();

        for (Book u : mylist) {
            System.out.println(u.getBookid() + " " + u.getTitle());
        }
    }

    @Test
    public void A_findAll() throws Exception {

        List<Book> testList = bookService.findAll();
        System.out.println("Expect: 5");
        System.out.println("Actual: " + testList.size());

        assertEquals(5, testList.size());
    }

    @Test
    public void B_findBookById() {
        Book testBook = bookService.findBookById(27);
        System.out.println("Expect: Digital Fortress");
        System.out.println("Actual: " + testBook.getTitle());

        assertEquals("Digital Fortress", bookService.findBookById(27).getTitle());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void C_notFindBookById() {
        assertEquals(643, bookService.findBookById(643).getBookid());
    }

    @Test
    public void D_delete() {

        bookService.delete(30);

        System.out.println("Expect: 4");
        System.out.println("Actual: " + bookService.findAll().size());

        assertEquals(4, bookService.findAll().size());
    }

    @Test
    public void E_save() {
        Section section = sectionService.findSectionByName("Fiction");
        Book book = new Book("The Shadow Rising",
                "9780312854317", 1992, section);

        Book addBook = bookService.save(book);

        assertNotNull(addBook);
        assertEquals("The Shadow Rising", addBook.getTitle());

        System.out.println("Expect: The Shadow Rising");
        System.out.println("Actual: " + addBook.getTitle());
    }

    @Test
    public void update()
    {
    }

    @Test
    public void deleteAll()
    {
    }
}