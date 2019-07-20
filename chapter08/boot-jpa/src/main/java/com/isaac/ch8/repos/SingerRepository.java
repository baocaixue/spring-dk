package com.isaac.ch8.repos;

import com.isaac.ch8.entities.Singer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SingerRepository extends CrudRepository<Singer, Long> {
    List<Singer> findByFirstName(String firstName);

    Singer findDistinctByFirstNameAndLastName(String firstName, String lastName);


}
