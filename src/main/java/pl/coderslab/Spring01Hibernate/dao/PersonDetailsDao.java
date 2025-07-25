package pl.coderslab.Spring01Hibernate.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.Spring01Hibernate.entity.PersonDetails;

@Repository
@Transactional
public class PersonDetailsDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(PersonDetails personDetails) {
        entityManager.persist(personDetails);
    }

    public void update(PersonDetails personDetails) {
        entityManager.merge(personDetails);
    }

    public void delete(PersonDetails personDetails) {
        entityManager.remove(entityManager.contains(personDetails) ? personDetails : entityManager.merge(personDetails));
    }

    public PersonDetails findById(Long id) {
        return entityManager.find(PersonDetails.class, id);
    }
}
