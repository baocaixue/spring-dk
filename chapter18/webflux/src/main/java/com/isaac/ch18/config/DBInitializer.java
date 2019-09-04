package com.isaac.ch18.config;

import com.isaac.ch18.entities.Singer;
import com.isaac.ch18.repos.SingerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@Service
@Slf4j
public class DBInitializer {

    @Autowired
    private SingerRepository singerRepository;

    @PostConstruct
    public void initDatabase() {
        log.info("now init H2 database ...");

        Singer selena = new Singer();
        selena.setFirstName("Selena");
        selena.setLastName("Gomez");
        selena.setBirthDate(LocalDate.of(1992, 7, 22));
        singerRepository.save(selena);

        Singer taylor = new Singer();
        taylor.setFirstName("Taylor");
        taylor.setLastName("Swift");
        taylor.setBirthDate(LocalDate.of(1989, 12, 13));
        singerRepository.save(taylor);

        Singer ed = new Singer();
        ed.setFirstName("Ed");
        ed.setLastName("Sheeran");
        ed.setBirthDate(LocalDate.of(1991, 2, 17));
        singerRepository.save(ed);
        log.info("database init successful");
    }
}
