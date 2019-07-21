package com.isaac.ch9.service;

import com.google.common.collect.Lists;
import com.isaac.ch9.entities.Singer;
import com.isaac.ch9.repos.SingerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SingerSerivceImpl implements SingerService {
    @Autowired
    private SingerRepository singerRepository;

    @Override
    public List<Singer> findAll() {
        return Lists.newArrayList(singerRepository.findAll());
    }

    @Override
    public Singer findById(Long id) {
        return singerRepository.findById(id).orElse(null);
    }

    @Override
    public Singer save(Singer singer) {
        return singerRepository.save(singer);
    }

    @Override
    public long countAll() {
        return singerRepository.countAllSingers();
    }
}
