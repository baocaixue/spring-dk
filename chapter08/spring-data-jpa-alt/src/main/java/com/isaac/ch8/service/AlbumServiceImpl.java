package com.isaac.ch8.service;

import com.isaac.ch8.entities.Album;
import com.isaac.ch8.entities.Singer;
import com.isaac.ch8.repos.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    private AlbumRepository albumRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Album> findBySinger(Singer singer) {
        return albumRepository.findBySinger(singer);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Album> findByTitle(String title) {
        return albumRepository.findByTitle(title);
    }
}
