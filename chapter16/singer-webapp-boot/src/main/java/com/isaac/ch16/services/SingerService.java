package com.isaac.ch16.services;

import com.isaac.ch16.entities.Singer;

import java.util.List;

public interface SingerService {
    List<Singer> findAll();
    Singer findById(Long id);
    Singer save(Singer singer);
}
