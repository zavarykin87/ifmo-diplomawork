package ru.zavarykin.localfarm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zavarykin.localfarm.entity.Farmer;

public interface FarmerRepository extends JpaRepository<Farmer, Integer> {

}
