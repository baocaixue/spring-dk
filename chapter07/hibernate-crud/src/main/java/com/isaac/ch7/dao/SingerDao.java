package com.isaac.ch7.dao;

import com.isaac.ch7.entities.Singer;

import java.util.List;

public interface SingerDao {

    List<Singer> findAll();

    List findAllWithAlbum();

    Singer findById(Long id);

    Singer save(Singer singer);

    void delete(Singer singer);
}
