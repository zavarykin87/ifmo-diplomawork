package ru.zavarykin.localfarm.repository;

import org.springframework.data.repository.CrudRepository;
import ru.zavarykin.localfarm.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {

}
