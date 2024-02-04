package ru.itrum.springData.task03.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itrum.springData.task03.models.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
