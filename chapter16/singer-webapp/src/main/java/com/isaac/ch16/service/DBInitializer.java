package com.isaac.ch16.service;

import com.isaac.ch16.entities.Singer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@Service
@Slf4j
public class DBInitializer {
    @Autowired
    SingerService singerService;

    @PostConstruct
    public void initDB() {
        log.info("Starting database initialization...");
        Singer singer = new Singer();
        singer.setFirstName("John");
        singer.setLastName("Mayer");
        singer.setBirthDate(LocalDate.of(1977, 9, 16));
        singerService.save(singer);

        singer = new Singer();
        singer.setFirstName("Eric");
        singer.setLastName("Clapton");
        singer.setBirthDate(LocalDate.of(1945, 2, 23));
        singerService.save(singer);

        singer = new Singer();
        singer.setFirstName("John");
        singer.setLastName("Butler");
        singer.setBirthDate(LocalDate.of(1975, 3, 1));
        singerService.save(singer);

        singer = new Singer();
        singer.setFirstName("B.B.");
        singer.setLastName("King");
        singer.setBirthDate(LocalDate.of(1925, 9, 16));
        singerService.save(singer);

        singer = new Singer();
        singer.setFirstName("Jimi");
        singer.setLastName("Hendrix");
        singer.setBirthDate(LocalDate.of(1942, 11, 27));
        singerService.save(singer);

        singer = new Singer();
        singer.setFirstName("Jimmy");
        singer.setLastName("Page");
        singer.setBirthDate(LocalDate.of(1944, 1, 9));
        singerService.save(singer);

        singer = new Singer();
        singer.setFirstName("Eddie");
        singer.setLastName("Van Halen");
        singer.setBirthDate(LocalDate.of(1955, 1, 26));
        singerService.save(singer);

        singer = new Singer();
        singer.setFirstName("Saul (Slash)");
        singer.setLastName("Hudson");
        singer.setBirthDate(LocalDate.of(1965, 7, 23));
        singerService.save(singer);

        singer = new Singer();
        singer.setFirstName("Stevie");
        singer.setLastName("Ray Vaughan");
        singer.setBirthDate(LocalDate.of(1954, 10, 3));
        singerService.save(singer);

        singer = new Singer();
        singer.setFirstName("David");
        singer.setLastName("Gilmour");
        singer.setBirthDate(LocalDate.of(1946, 3, 6));
        singerService.save(singer);

        singer = new Singer();
        singer.setFirstName("Kirk");
        singer.setLastName("Hammett");
        singer.setBirthDate(LocalDate.of(1992, 11, 18));
        singerService.save(singer);

        singer = new Singer();
        singer.setFirstName("Angus");
        singer.setLastName("Young");
        singer.setBirthDate(LocalDate.of(1955, 3, 31));
        singerService.save(singer);

        singer = new Singer();
        singer.setFirstName("Dimebag");
        singer.setLastName("Darrell");
        singer.setBirthDate(LocalDate.of(1966, 8, 20));
        singerService.save(singer);

        singer = new Singer();
        singer.setFirstName("Carlos");
        singer.setLastName("Santana");
        singer.setBirthDate(LocalDate.of(1947, 7, 20));
        singerService.save(singer);

        log.info("Database initialization finished.");
    }
}
