package com.isaac.ch9.repos;

import com.isaac.ch9.entities.Album;
import com.isaac.ch9.entities.Singer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlbumRepository extends JpaRepository<Album, Long> {
    List<Album> findBySinger(Singer singer);
}
