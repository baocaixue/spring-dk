package com.isaac.ch11.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import java.time.LocalDate;

@Entity
@Table(name = "car")
@Data
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name="LICENSE_PLATE")
    private String licensePlate;

    @Column(name="MANUFACTURER")
    private String manufacturer;

    @Column(name="MANUFACTURE_DATE")
    private LocalDate manufactureDate;

    @Column(name="AGE")
    private int age;

    @Version
    private int version;

    @Override
    public String toString() {
        return String.format("{License: %s, Manufacturer: %s, Manufacture Date: %s, Age: %d}",
                licensePlate, manufacturer, manufactureDate, age);
    }
}
