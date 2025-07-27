package pl.coderslab.Spring01Hibernate.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.Spring01Hibernate.entity.Author;
import pl.coderslab.Spring01Hibernate.entity.Book;
import pl.coderslab.Spring01Hibernate.entity.Publisher;

import java.util.List;

@Repository
@Transactional
public class BookDao {
    @PersistenceContext
    private EntityManager entityManager;

    public void save(Book book) {
        entityManager.persist(book);
    }

    public void update(Book book) {
        entityManager.merge(book);
    }

    public void delete(Book book) {
        entityManager.remove(entityManager.contains(book) ? book : entityManager.merge(book));
    }

    public Book findById(Long id) {
        return entityManager.find(Book.class, id);
    }

    public List<Book> findAll() {
        Query query = entityManager.createQuery("SELECT b FROM Book b");
        return query.getResultList();
    }

    public List<Book> findAllByRating(int rating) {
        return entityManager.createQuery("SELECT b FROM Book b WHERE b.rating > :rating")
                .setParameter("rating", rating).getResultList();
    }

    public List<Book> findBooksWithPublisher() {
        return entityManager.createQuery("SELECT b FROM Book b JOIN b.publishers")
                .getResultList();
    }

    public List<Book> findAllByPublisher(Publisher publisher) {
        return entityManager.createQuery("SELECT b FROM Book b JOIN FETCH b.publishers WHERE :publisher MEMBER OF b.publishers")
                .setParameter("publisher", publisher).getResultList();
    }

    public List<Book> findAllByAuthor(Author author) {
        return entityManager.createQuery("SELECT b FROM Book b JOIN FETCH b.author WHERE b.author = :author")
                .setParameter("author", author).getResultList();
    }
}
