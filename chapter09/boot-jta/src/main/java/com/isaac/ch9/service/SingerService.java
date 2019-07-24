package com.isaac.ch9.service;

import com.isaac.ch9.entities.Singer;

public interface SingerService {
    Singer save(Singer singer);

    long count();

}
