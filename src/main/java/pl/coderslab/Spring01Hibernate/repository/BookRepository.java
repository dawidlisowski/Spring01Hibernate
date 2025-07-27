package pl.coderslab.Spring01Hibernate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.coderslab.Spring01Hibernate.entity.Author;
import pl.coderslab.Spring01Hibernate.entity.Book;
import pl.coderslab.Spring01Hibernate.entity.Category;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("select b from Book b where b.title = ?1")
    Book findByTitle(String title);

    List<Book> findByCategory(Category category);

    List<Book> findByAuthor(Author author);

    @Query("select b from Book b where b.rating >= :min and b.rating <= :max")
    List<Book> findByRatingBetween(@Param("min") int min, @Param("max") int max);

    Book findFirstByCategoryOrderByTitle(Category category);
}
