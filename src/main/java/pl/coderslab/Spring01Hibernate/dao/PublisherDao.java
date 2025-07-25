package pl.coderslab.Spring01Hibernate.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.Spring01Hibernate.entity.Publisher;

import java.util.List;

@Repository
@Transactional
public class PublisherDao {
    @PersistenceContext
    private EntityManager entityManager;

    public void save(Publisher publisher) {
        entityManager.persist(publisher);
    }

    public Publisher findById(Long id) {
        return entityManager.find(Publisher.class, id);
    }

    public List<Publisher> findAll() {
        Query query = entityManager.createQuery("SELECT p FROM Publisher p");
        return query.getResultList();
    }
}
