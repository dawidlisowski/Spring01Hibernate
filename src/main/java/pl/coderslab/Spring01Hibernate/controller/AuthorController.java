package pl.coderslab.Spring01Hibernate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.Spring01Hibernate.dao.AuthorDao;
import pl.coderslab.Spring01Hibernate.entity.Author;

@RestController
public class AuthorController {

    @Autowired
    private AuthorDao authorDao;

    @RequestMapping("/author/add/{firstName}/{lastName}")
    public String addAuthor(@PathVariable String firstName,
                          @PathVariable String lastName) {
        Author author = new Author();
        author.setFirstName(firstName);
        author.setLastName(lastName);

        authorDao.save(author);
        return "success " + author.getLastName() + " id: " + author.getId();
    }

    @RequestMapping("/author/get/{id}")
    public String getAuthor(@PathVariable Long id){
        Author author = authorDao.findById(id);
        return author.toString();
    }

    @RequestMapping("/author/update/{id}/{lastName}")
    public String updateAuthor(@PathVariable Long id,
                             @PathVariable String lastName){
        Author author = authorDao.findById(id);
        author.setLastName(lastName);
        authorDao.update(author);
        return author.toString();
    }

    @RequestMapping("/author/delete/{id}")
    public String deleteAuthor(@PathVariable Long id){
        Author author = authorDao.findById(id);
        authorDao.delete(author);
        return "success";
    }
}
