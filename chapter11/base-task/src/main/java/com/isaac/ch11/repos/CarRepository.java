package com.isaac.ch11.repos;

import com.isaac.ch11.entities.Car;
import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<Car, Long> {
}
