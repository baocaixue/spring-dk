package com.isaac.ch8;

import com.isaac.ch8.entities.Singer;
import com.isaac.ch8.repos.SingerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class Application implements CommandLineRunner{
    private final Logger logger = LoggerFactory.getLogger(Application.class);
    @Autowired
    private SingerRepository singerRepository;

    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);
        System.in.read();
        ctx.close();
    }

    @Override
    public void run(String... args) throws Exception {
        Singer isaac = singerRepository.findDistinctByFirstNameAndLastName("Isaac", "Bao");
        logger.info(isaac.toString());
    }
}
