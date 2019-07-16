package com.isaac.ch8;

import com.isaac.ch8.config.DataJpaConfig;
import com.isaac.ch8.entities.Singer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.List;

import static org.junit.Assert.*;

public class SingerDataJPATest {
    private static Logger logger = LoggerFactory.getLogger(SingerDataJPATest.class);

    private GenericApplicationContext ctx;
    private SingerService singerService;

    @Before
    public void setUp() {
        ctx = new AnnotationConfigApplicationContext(DataJpaConfig.class);
        assertNotNull(ctx);
        singerService = ctx.getBean(SingerService.class);
        assertNotNull(singerService);
    }

    @Test
    public void testFindAll() {
        List<Singer> singers = singerService.findAll();
        assertEquals(3, singers.size());
        singers.stream().map(Singer::toString).forEach(logger::info);
    }

    @Test
    public void testFindByFirstName() {
        List<Singer> singers = singerService.findByFirstName("John");
        assertEquals(2, singers.size());
        singers.stream().map(Singer::toString).forEach(logger::info);
    }

    @Test
    public void testFindByFirstNameAndLastName() {
        List<Singer> singers = singerService.findByFirstNameAndLastName("John", "Mayer");
        assertEquals(1, singers.size());
        logger.info(singers.get(0).toString());
    }

    @After
    public void close() {
        ctx.close();
    }
}
