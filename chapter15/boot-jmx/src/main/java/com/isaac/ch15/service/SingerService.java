package com.isaac.ch15.service;


import com.isaac.ch15.entities.Singer;

import java.util.List;

public interface SingerService {

	List<Singer> findAll();

	List<Singer> findByFirstName(String firstName);

	List<Singer> findByFirstNameAndLastName(String firstName, String lastName);

	Singer findById(Long id);

	Singer save(Singer singer);

	void delete(Singer singer);
}
