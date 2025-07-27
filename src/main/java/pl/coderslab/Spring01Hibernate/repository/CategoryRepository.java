package pl.coderslab.Spring01Hibernate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.Spring01Hibernate.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String name);
}
