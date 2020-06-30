package ru.zavarykin.localfarm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zavarykin.localfarm.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
