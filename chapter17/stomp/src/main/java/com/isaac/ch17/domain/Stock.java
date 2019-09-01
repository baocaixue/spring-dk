package com.isaac.ch17.domain;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
//@Accessors(chain = true)
public class Stock implements Serializable {
    private static final long serialVersionUID = 1L;

    private String code;
    private Double price;
    private LocalDateTime dateTime;

    public Stock() { }

    public Stock(String code, double price) {
        this.code = code;
        this.price = price;
    }
}
