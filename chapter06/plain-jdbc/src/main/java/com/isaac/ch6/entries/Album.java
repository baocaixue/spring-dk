package com.isaac.ch6.entries;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
public class Album implements Serializable{
    private Long id;
    private Long singerId;
    private String title;
    private Date releaseDate;

    @Override
    public String toString() {
        return "Album - Id: " + id + ", Singer id: " + singerId
                + ", Title: " + title + ", Release Date: " + releaseDate;
    }
}
