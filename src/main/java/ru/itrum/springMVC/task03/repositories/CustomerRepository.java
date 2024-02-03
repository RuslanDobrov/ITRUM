package ru.itrum.springMVC.task03.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itrum.springMVC.task03.models.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
