package com.isaac.ch9;

import com.isaac.ch9.entities.Singer;
import com.isaac.ch9.service.SingerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;

@SpringBootApplication
public class Application implements CommandLineRunner{
    private static Logger logger = LoggerFactory.getLogger(Application.class);
    @Autowired
    private SingerService singerService;

    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);
        System.in.read();
        ctx.close();
    }

    @Override
    public void run(String... args) throws Exception {
        Singer singer = new Singer();
        singer.setFirstName("Isaac");
        singer.setLastName("Bao");
        singer.setBirthDate(new Date((new GregorianCalendar(1995, 9, 16)).getTime().getTime()));
        singerService.save(singer);

        long count = singerService.count();
        if (count == 1) {
            logger.info("--> Singer saved successfully");
        }  else {
            logger.error("--> Singer was not saved, check the configuration!!");
        }

        try {
            singerService.save(null);
        } catch (Exception ex) {
            logger.error(ex.getMessage() + "Final count:" + singerService.count());
        }
    }
}
