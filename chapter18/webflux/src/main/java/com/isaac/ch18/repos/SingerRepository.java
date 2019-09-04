package com.isaac.ch18.repos;

import com.isaac.ch18.entities.Singer;
import org.springframework.data.repository.CrudRepository;

public interface SingerRepository extends CrudRepository<Singer, Long> {
}
