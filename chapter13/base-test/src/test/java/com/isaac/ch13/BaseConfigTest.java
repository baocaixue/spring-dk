package com.isaac.ch13;

import com.isaac.ch13.config.DataConfig;
import com.isaac.ch13.config.ServiceConfig;
import com.isaac.ch13.entities.Singer;
import com.isaac.ch13.service.SingerService;
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

    private SingerService singerService;

    private GenericApplicationContext ctx;

    @Before
    public void setUp() {
        ctx = new AnnotationConfigApplicationContext(ServiceConfig.class);
        ctx.getEnvironment().setActiveProfiles("dev");
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
    public void colse() {

    }
}
