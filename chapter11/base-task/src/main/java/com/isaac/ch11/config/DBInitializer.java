package com.isaac.ch11.config;

import com.isaac.ch11.entities.Car;
import com.isaac.ch11.repos.CarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DBInitializer implements InitializingBean {
    private static Logger logger = LoggerFactory.getLogger(DBInitializer.class);

    @Autowired
    private CarRepository carRepository;

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("starting database init....");
        Car car = new Car();
        car.setLicensePlate("GRAVITY-0405");
        car.setManufacturer("Ford");
        car.setManufactureDate(LocalDate.of(2006,9,12));
        carRepository.save(car);

        car = new Car();
        car.setLicensePlate("CLARITY-0432");
        car.setManufacturer("Toyota");
        car.setManufactureDate(LocalDate.of(2003, 9, 9));
        carRepository.save(car);

        car = new Car();
        car.setLicensePlate("ROSIE-0402");
        car.setManufacturer("Toyota");
        car.setManufactureDate(LocalDate.of(2017, 4, 16));
        carRepository.save(car);
        logger.info("database init finish.");
    }
}
