package pl.coderslab.Spring01Hibernate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.Spring01Hibernate.dao.BookDao;
import pl.coderslab.Spring01Hibernate.entity.Book;

@RestController
public class BookController {

    @Autowired
    private BookDao bookDao;

    @RequestMapping("/book/add/{title}/{description}/{rating}")
    public String addBook(@PathVariable String title,
                          @PathVariable String description,
                          @PathVariable int rating) {
        Book book = new Book();
        book.setTitle(title);
        book.setDescription(description);
        book.setRating(rating);

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
