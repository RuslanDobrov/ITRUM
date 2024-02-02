package ru.itrum.springMVC.task01.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itrum.springMVC.task01.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
