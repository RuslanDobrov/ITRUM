package ru.itrum.springData.task02.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itrum.springData.task02.models.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
