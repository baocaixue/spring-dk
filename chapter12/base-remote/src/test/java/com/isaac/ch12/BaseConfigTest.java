package com.isaac.ch12;

import com.isaac.ch12.config.DataServiceConfig;
import com.isaac.ch12.entities.Singer;
import com.isaac.ch12.service.SingerService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.List;

import static org.junit.Assert.*;

public class BaseConfigTest {
    private final Logger logger = LoggerFactory.getLogger(BaseConfigTest.class);

    private GenericApplicationContext ctx;
    private SingerService singerService;

    @Before
    public void setUp() {
        ctx = new AnnotationConfigApplicationContext(DataServiceConfig.class);
        assertNotNull(ctx);
        singerService = ctx.getBean(SingerService.class);
        assertNotNull(singerService);
    }

    @Test
    public void test() {
        List<Singer> singers = singerService.findAll();
        assertNotNull(singers);
        assertEquals(3, singers.size());
        singers.stream().map(Singer::toString).forEach(logger::info);
    }

    @After
    public void close() {
        ctx.close();
    }
}
