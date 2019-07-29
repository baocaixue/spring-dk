package com.isaac.ch11.service;

import com.isaac.ch11.entities.Car;

import java.util.List;

public interface CarService {
    List<Car> findAll();
    Car save(Car car);
    void updateCarAgeJob();
    boolean isDone();
}
