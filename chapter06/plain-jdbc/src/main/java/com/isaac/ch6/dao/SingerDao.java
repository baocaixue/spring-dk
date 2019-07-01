package com.isaac.ch6.dao;

import com.isaac.ch6.entries.Singer;

import java.util.List;

public interface SingerDao {
    List<Singer> findAll();

    List<Singer> findByFirstName();

    String findNameById(Long id);

    String findLastNameById(Long id);

    String findFirstNameById(Long id);

    void insert(Singer singer);

    void update(Singer singer);

    void delete(Long singerId);

    List<Singer> findAllWithAlbums();

    void insertWithAlbum(Singer singer);
}
