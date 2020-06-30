package ru.zavarykin.localfarm.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.zavarykin.localfarm.entity.Role;

public interface RoleRepository extends JpaRepository <Role, Integer> {

}