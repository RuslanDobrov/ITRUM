package ru.itrum.springData.task02.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itrum.springData.task02.models.Employee;
import ru.itrum.springData.task02.projection.EmployeeProjection;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<EmployeeProjection> findProjectedById(Long id);
}
