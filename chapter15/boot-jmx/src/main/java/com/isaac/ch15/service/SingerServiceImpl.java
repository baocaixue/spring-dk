package com.isaac.ch15.service;

import com.google.common.collect.Lists;
import com.isaac.ch15.entities.Singer;
import com.isaac.ch15.repos.SingerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("singerService")
@Transactional
public class SingerServiceImpl implements SingerService {

	@Autowired
	private SingerRepository singerRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Singer> findAll() {
		return Lists.newArrayList(singerRepository.findAll());
	}

	@Override
	@Transactional(readOnly = true)
	public List<Singer> findByFirstName(String firstName) {
		return singerRepository.findByFirstName(firstName);
	}

	@Override public List<Singer> findByFirstNameAndLastName(String firstName, String lastName) {
		return singerRepository.findByFirstNameAndLastName(firstName,lastName);
	}

	@Override
	@Transactional(readOnly = true)
	public Singer findById(Long id) {
		return singerRepository.findById(id).orElseThrow(()->new RuntimeException("no singer exception"));
	}

	@Override
	public Singer save(Singer singer) {
		return singerRepository.save(singer);
	}

	@Override
	public void delete(Singer singer) {
		singerRepository.delete(singer);
	}
}
