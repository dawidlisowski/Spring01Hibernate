package pl.coderslab.Spring01Hibernate.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.Spring01Hibernate.dao.AuthorDao;
import pl.coderslab.Spring01Hibernate.entity.Author;
import pl.coderslab.Spring01Hibernate.repository.AuthorRepository;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {


    private final AuthorDao authorDao;
    private final AuthorRepository authorRepository;

    public AuthorController(AuthorDao authorDao, AuthorRepository authorRepository) {
        this.authorDao = authorDao;
        this.authorRepository = authorRepository;
    }

    @RequestMapping("/add/{firstName}/{lastName}")
    public String addAuthor(@PathVariable String firstName,
                          @PathVariable String lastName) {
        Author author = new Author();
        author.setFirstName(firstName);
        author.setLastName(lastName);

        authorDao.save(author);
        return "success " + author.getLastName() + " id: " + author.getId();
    }

    @RequestMapping("/get/{id}")
    public String getAuthor(@PathVariable Long id){
        Author author = authorDao.findById(id);
        return author.toString();
    }

    @RequestMapping("/update/{id}/{lastName}")
    public String updateAuthor(@PathVariable Long id,
                             @PathVariable String lastName){
        Author author = authorDao.findById(id);
        author.setLastName(lastName);
        authorDao.update(author);
        return author.toString();
    }

    @RequestMapping("/delete/{id}")
    public String deleteAuthor(@PathVariable Long id){
        Author author = authorDao.findById(id);
        authorDao.delete(author);
        return "success";
    }

    @GetMapping("/birthYear/{year}")
    public String getByBirthYear(@PathVariable Integer year){
        List<Author> authors = authorRepository.findByBirthYear(year);
        return authors.toString();
    }
}
