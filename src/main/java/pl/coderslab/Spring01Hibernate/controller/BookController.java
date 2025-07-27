package pl.coderslab.Spring01Hibernate.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.Spring01Hibernate.dao.AuthorDao;
import pl.coderslab.Spring01Hibernate.dao.BookDao;
import pl.coderslab.Spring01Hibernate.dao.PublisherDao;
import pl.coderslab.Spring01Hibernate.entity.Author;
import pl.coderslab.Spring01Hibernate.entity.Book;
import pl.coderslab.Spring01Hibernate.entity.Category;
import pl.coderslab.Spring01Hibernate.entity.Publisher;
import pl.coderslab.Spring01Hibernate.repository.AuthorRepository;
import pl.coderslab.Spring01Hibernate.repository.BookRepository;
import pl.coderslab.Spring01Hibernate.repository.CategoryRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private final PublisherDao publisherDao;
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    private final AuthorRepository authorRepository;

    public BookController(BookDao bookDao, AuthorDao authorDao, PublisherDao publisherDao, BookRepository bookRepository, CategoryRepository categoryRepository, AuthorRepository authorRepository) {
        this.bookDao = bookDao;
        this.authorDao = authorDao;
        this.publisherDao = publisherDao;
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
        this.authorRepository = authorRepository;
    }

    @GetMapping("/authorBooks/{id}")
    public String findBooksByAuthor(@PathVariable Long id) {
        Author author = authorDao.findById(id);
        List<Book> books = bookDao.findAllByAuthor(author);
        return books.toString();
    }

    @GetMapping("/publisherBooks/{id}")
    public String findBooksByPublisher(@PathVariable Long id) {
        Publisher publisher = publisherDao.findById(id);
        List<Book> books = bookDao.findAllByPublisher(publisher);
        return books.toString();
    }

    @GetMapping("/havePublisher")
    public String findBooksWithPublisher() {
        List<Book> books = bookDao.findBooksWithPublisher();
        return books.toString();
    }

    @GetMapping("/all")
    public String findAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.toString();
    }

    @GetMapping("/rating/{min}/{max}")
    public String findBooksByRating(@PathVariable int min, @PathVariable int max) {
        List<Book> books = bookRepository.findByRatingBetween(min, max);
        return books.toString();
    }

    @RequestMapping("/add/{title}/{description}/{rating}/{firstName}/{lastName}")
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

    @RequestMapping("/get/{id}")
    public String getBookById(@PathVariable Long id) {
        Book book = bookDao.findById(id);
        return book.toString();
    }

    @GetMapping("/get/{title}")
    public String getBookByTitle(@PathVariable String title) {
        Book book = bookRepository.findByTitle(title);
        return book.toString();
    }

    @GetMapping("/category/{categoryName}")
    public String getBooksByCategory(@PathVariable String categoryName) {
        Category category = categoryRepository.findByName(categoryName);
        return bookRepository.findByCategory(category).toString();
    }

    @GetMapping("/single/category/{categoryName}")
    public String getBookByCategoryOrderByTitle(@PathVariable String categoryName) {
        Category category = categoryRepository.findByName(categoryName);
        return bookRepository.findFirstByCategoryOrderByTitle(category).toString();
    }

    @GetMapping("/author/{lastName}")
    public String getBookByAuthor(@PathVariable String lastName) {
        Author author = authorRepository.findByLastName(lastName);
        return bookRepository.findByAuthor(author).toString();
    }

    @RequestMapping("/update/{id}/{description}")
    public String updateBook(@PathVariable Long id,
                             @PathVariable String description) {
        Book book = bookDao.findById(id);
        book.setDescription(description);
        bookDao.update(book);
        return book.toString();
    }

    @RequestMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        Book book = bookDao.findById(id);
        bookDao.delete(book);
        return "success";
    }
}
