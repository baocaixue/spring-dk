package com.isaac.ch8.service;


import com.isaac.ch8.entities.Album;
import com.isaac.ch8.entities.Singer;

import java.util.List;

public interface AlbumService {
	List<Album> findBySinger(Singer singer);

	List<Album> findByTitle(String title);
}
