package com.isaac.ch11;

import com.isaac.ch11.config.DataServiceConfig;
import com.isaac.ch11.entities.Car;
import com.isaac.ch11.service.CarService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.List;
import java.util.Optional;

public class ConfigTest {
    private static Logger logger = LoggerFactory.getLogger(ConfigTest.class);
    private GenericApplicationContext ctx;
    private CarService carService;

    @Before
    public void setup() {
        ctx = new AnnotationConfigApplicationContext(DataServiceConfig.class);
        carService = ctx.getBean(CarService.class);
    }

    @Test
    public void test() {
        List<Car> cars = carService.findAll();
        Assert.assertNotNull(cars);
        Assert.assertEquals(3, cars.size());
        cars.stream().map(Car::toString).forEach(logger::info);
        carService.updateCarAgeJob();
    }

    @After
    public void close() {
        Optional.ofNullable(ctx).ifPresent(AbstractApplicationContext::close);
    }
}
