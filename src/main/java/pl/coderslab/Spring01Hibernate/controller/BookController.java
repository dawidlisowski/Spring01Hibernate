package pl.coderslab.Spring01Hibernate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.Spring01Hibernate.dao.AuthorDao;
import pl.coderslab.Spring01Hibernate.dao.BookDao;
import pl.coderslab.Spring01Hibernate.dao.PublisherDao;
import pl.coderslab.Spring01Hibernate.entity.Author;
import pl.coderslab.Spring01Hibernate.entity.Book;
import pl.coderslab.Spring01Hibernate.entity.Publisher;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookController {

    private final BookDao bookDao;

    private final AuthorDao authorDao;

    private final PublisherDao publisherDao;

    public BookController(BookDao bookDao, AuthorDao authorDao, PublisherDao publisherDao) {
        this.bookDao = bookDao;
        this.authorDao = authorDao;
        this.publisherDao = publisherDao;
    }

    @GetMapping("/book/authorBooks/{id}")
    public String findBooksByAuthor(@PathVariable Long id) {
        Author author = authorDao.findById(id);
        List<Book> books = bookDao.findAllByAuthor(author);
        return books.toString();
    }

    @GetMapping("/book/publisherBooks/{id}")
    public String findBooksByPublisher(@PathVariable Long id) {
        Publisher publisher = publisherDao.findById(id);
        List<Book> books = bookDao.findAllByPublisher(publisher);
        return books.toString();
    }

    @GetMapping("/book/havePublisher")
    public String findBooksWithPublisher() {
        List<Book> books = bookDao.findBooksWithPublisher();
        return books.toString();
    }

    @GetMapping("/book/all")
    public String findAllBooks() {
        List<Book> books = bookDao.findAll();
        return books.toString();
    }

    @GetMapping("/book/rating/{rating}")
    public String findBooksByRating(@PathVariable int rating) {
        List<Book> books = bookDao.findAllByRating(rating);
        return books.toString();
    }

    @RequestMapping("/book/add/{title}/{description}/{rating}/{firstName}/{lastName}")
    public String addBook(@PathVariable String title,
                          @PathVariable String description,
                          @PathVariable int rating,
                          @PathVariable String firstName,
                          @PathVariable String lastName) {
        List<Publisher> publishers = new ArrayList<>();
        publishers.add(publisherDao.findById(1L));
        publishers.add(publisherDao.findById(2L));

        Author author = new Author();
        author.setFirstName(firstName);
        author.setLastName(lastName);
        authorDao.save(author);

        Book book = new Book();
        book.setTitle(title);
        book.setDescription(description);
        book.setRating(rating);
        book.setAuthor(author);
        book.setPublishers(publishers);

        bookDao.save(book);
        return "success " + book.getTitle() + " id: " + book.getId();
    }

    @RequestMapping("/book/get/{id}")
    public String getBook(@PathVariable Long id){
        Book book = bookDao.findById(id);
        return book.toString();
    }

    @RequestMapping("/book/update/{id}/{description}")
    public String updateBook(@PathVariable Long id,
                             @PathVariable String description){
        Book book = bookDao.findById(id);
        book.setDescription(description);
        bookDao.update(book);
        return book.toString();
    }

    @RequestMapping("/book/delete/{id}")
    public String deleteBook(@PathVariable Long id){
        Book book = bookDao.findById(id);
        bookDao.delete(book);
        return "success";
    }
}
