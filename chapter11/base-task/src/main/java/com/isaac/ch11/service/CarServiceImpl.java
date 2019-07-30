package com.isaac.ch11.service;

import com.google.common.collect.Lists;
import com.isaac.ch11.entities.Car;
import com.isaac.ch11.repos.CarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service("carService")
@Transactional
public class CarServiceImpl implements CarService {
    private final Logger logger = LoggerFactory.getLogger(CarServiceImpl.class);

    public boolean done;

    @Autowired
    private CarRepository carRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Car> findAll() {
        return Lists.newArrayList(carRepository.findAll());
    }

    @Override
    public Car save(Car car) {
        return carRepository.save(car);
    }

    @Override
    public void updateCarAgeJob() {
        List<Car> cars = findAll();
        LocalDate today = LocalDate.now();
        logger.info("Car age update job start");

        cars.forEach(car -> {
            int age = Period.between(car.getManufactureDate(), today).getYears();
            car.setAge(age);
            save(car);
            logger.info("Car age already update " + car);
        });

        logger.info("Car age update job completed successfully");
        done = true;
    }

    @Override
    public boolean isDone() {
        return done;
    }
}
