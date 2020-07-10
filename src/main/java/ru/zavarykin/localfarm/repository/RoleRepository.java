package ru.zavarykin.localfarm.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.zavarykin.localfarm.entity.Role;

import java.util.List;

public interface RoleRepository extends JpaRepository <Role, Integer> {
}