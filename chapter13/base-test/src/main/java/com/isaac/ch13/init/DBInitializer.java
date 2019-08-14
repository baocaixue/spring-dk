package com.isaac.ch13.init;

import com.isaac.ch13.entities.Singer;
import com.isaac.ch13.repos.SingerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@Component
public class DBInitializer {
    private final Logger logger = LoggerFactory.getLogger(DBInitializer.class);

    @Autowired
    private SingerRepository singerRepository;

    @PostConstruct
    public void initDatabase() {
        logger.info("start H2 database init ...");

        Singer ed = new Singer();
        ed.setFirstName("Ed");
        ed.setLastName("Sheeran");
        ed.setBirthDate(LocalDate.of(1991, 2, 17));
        singerRepository.save(ed);

        Singer taylor = new Singer();
        taylor.setFirstName("Taylor");
        taylor.setLastName("Swift");
        taylor.setBirthDate(LocalDate.of(1989, 12, 13));
        singerRepository.save(taylor);

        Singer selena = new Singer();
        selena.setFirstName("Selena");
        selena.setLastName("Gomez");
        selena.setBirthDate(LocalDate.of(1992, 7, 22));
        singerRepository.save(selena);


        logger.info("finish database init.");
    }
}
