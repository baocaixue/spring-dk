package com.isaac.ch13.service;

import com.isaac.ch13.entities.Singer;

import java.util.List;

public interface SingerService {

	List<Singer> findAll();

	List<Singer> findByFirstName(String firstName);

	Singer findById(Long id);

	Singer save(Singer singer);

	void delete(Singer singer);

	Singer findByFirstNameAndLastName(String firstName, String lastName);

}
