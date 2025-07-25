package pl.coderslab.Spring01Hibernate.controller;

import org.springframework.web.bind.annotation.*;
import pl.coderslab.Spring01Hibernate.dao.PersonDao;
import pl.coderslab.Spring01Hibernate.dao.PersonDetailsDao;
import pl.coderslab.Spring01Hibernate.entity.Person;
import pl.coderslab.Spring01Hibernate.entity.PersonDetails;

@RestController
@RequestMapping("/people")
public class PersonController {

    private final PersonDao personDao;

    private final PersonDetailsDao personDetailsDao;

    public PersonController(PersonDao personDao, PersonDetailsDao personDetailsDao) {
        this.personDao = personDao;
        this.personDetailsDao = personDetailsDao;
    }


    @PostMapping
    public void createPerson(@RequestBody Person person) {
        personDao.save(person);
    }

    @GetMapping("/{id}")
    public String getPersonById(@PathVariable Long id) {
        Person person = personDao.findById(id);
        return person.toString();
    }

    @PutMapping("/{id}")
    public String updatePerson(@PathVariable Long id, @RequestBody Person updatedPerson) {
        Person existingPerson = personDao.findById(id);
        existingPerson.setLogin(updatedPerson.getLogin());
        existingPerson.setPassword(updatedPerson.getPassword());
        existingPerson.setEmail(updatedPerson.getEmail());

        PersonDetails updatedDetails = updatedPerson.getPersonDetails();
        if (updatedDetails != null) {
            if (existingPerson.getPersonDetails() == null) {
                existingPerson.setPersonDetails(updatedDetails);
            } else {
                PersonDetails existingDetails = existingPerson.getPersonDetails();
                existingDetails.setFirstName(updatedDetails.getFirstName());
                existingDetails.setLastName(updatedDetails.getLastName());
                existingDetails.setStreetNumber(updatedDetails.getStreetNumber());
                existingDetails.setStreet(updatedDetails.getStreet());
                existingDetails.setCity(updatedDetails.getCity());
            }
        }

        personDao.update(existingPerson);
        return existingPerson.toString();
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable Long id) {
        Person person = personDao.findById(id);
        personDao.delete(person);
        return "success";

    }
}
