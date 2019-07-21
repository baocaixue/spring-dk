package com.isaac.ch9.repos;

import com.isaac.ch9.entities.Singer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SingerRepository extends JpaRepository<Singer, Long> {
    @Query("select count(s) from Singer s")
    Long countAllSingers();
}
