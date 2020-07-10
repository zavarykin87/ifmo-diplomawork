package ru.zavarykin.localfarm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zavarykin.localfarm.entity.Role;
import ru.zavarykin.localfarm.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);

    List<User> findByRoles (Optional<Role> role);

}
