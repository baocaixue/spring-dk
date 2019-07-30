package com.isaac.ch11.service;

import com.isaac.ch11.entities.Car;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service("scheduledCarService")
@Transactional
public class ScheduledCarServiceImpl extends CarServiceImpl {
    private final Logger logger = LoggerFactory.getLogger(CarServiceImpl.class);

    @Override
    @Scheduled(cron = "0 * * * * *")
    public void updateCarAgeJob() {
        List<Car> cars = findAll();
        LocalDate today = LocalDate.now();
        logger.info("Car age update job start");

        cars.forEach(car -> {
            int age = Period.between(car.getManufactureDate(), today).getYears();
            car.setAge(age);
            save(car);
            logger.info("Car age update " + car);
        });

        logger.info("Car age update job completed successfully");
    }
}
