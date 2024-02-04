package ru.itrum.springData.task03.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itrum.springData.task03.models.Product;
import java.util.Set;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Set<Product> findByIdIn(Set<Long> productIds);
}
