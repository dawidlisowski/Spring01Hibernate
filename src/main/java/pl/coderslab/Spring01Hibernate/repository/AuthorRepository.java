package pl.coderslab.Spring01Hibernate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.coderslab.Spring01Hibernate.entity.Author;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Author findByLastName(String lastName);

    @Query("select a from Author a where a.birthYear >= :birthYear")
    List<Author> findByBirthYear(@Param("birthYear") Integer birthYear);
}
