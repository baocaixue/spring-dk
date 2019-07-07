package com.isaac.ch6;

import com.isaac.ch6.dao.SingerDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class Application {
    private static Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);
        assert ctx != null;

        SingerDao singerDao = ctx.getBean(SingerDao.class);
        String name = singerDao.findNameById(1L);
        logger.info("find singer is " + name);

        System.in.read();
        ctx.close();

    }
}
