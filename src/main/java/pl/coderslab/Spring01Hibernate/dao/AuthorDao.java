package pl.coderslab.Spring01Hibernate.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.Spring01Hibernate.entity.Author;

import java.util.List;

@Repository
@Transactional
public class AuthorDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(Author author) {
        entityManager.persist(author);
    }

    public void update(Author author) {
        entityManager.merge(author);
    }

    public void delete(Author author) {
        entityManager.remove(entityManager.contains(author) ? author : entityManager.merge(author));
    }

    public Author findById(Long id) {
        return entityManager.find(Author.class, id);
    }

    public List<Author> findAll() {
        Query query = entityManager.createQuery("SELECT a FROM Author a");
        return query.getResultList();
    }
}
