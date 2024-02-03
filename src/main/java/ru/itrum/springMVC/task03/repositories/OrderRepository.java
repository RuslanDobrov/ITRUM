package ru.itrum.springMVC.task03.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itrum.springMVC.task03.models.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}
