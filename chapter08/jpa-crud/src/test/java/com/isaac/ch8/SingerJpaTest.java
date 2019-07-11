package com.isaac.ch8;

import com.isaac.ch8.config.JpaConfig;
import com.isaac.ch8.entities.Singer;
import com.isaac.ch8.service.SingerService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.List;

import static org.junit.Assert.assertNotNull;

public class SingerJpaTest {
    private static Logger logger = LoggerFactory.getLogger(SingerJpaTest.class);

    private GenericApplicationContext ctx;
    private SingerService singerService;

    @Before
    public void setUp(){
        ctx = new AnnotationConfigApplicationContext(JpaConfig.class);
        singerService = ctx.getBean(SingerService.class);
        assertNotNull(singerService);
    }

    @Test
    public void testFindAll() {
        List<Singer> singers = singerService.findAll();
        singers.stream().map(Singer::toString).forEach(logger::info);
    }

    @After
    public void tearDown(){
        ctx.close();
    }
}
