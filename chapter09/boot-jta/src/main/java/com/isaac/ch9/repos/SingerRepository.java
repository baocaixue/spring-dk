package com.isaac.ch9.repos;

import com.isaac.ch9.entities.Singer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SingerRepository extends CrudRepository<Singer, Long> {
}
