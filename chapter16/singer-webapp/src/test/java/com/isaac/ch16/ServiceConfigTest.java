package com.isaac.ch16;

import com.isaac.ch16.config.DataServiceConfig;
import com.isaac.ch16.entities.Singer;
import com.isaac.ch16.service.SingerService;
import org.junit.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class ServiceConfigTest {
    private SingerService singerService;
    private GenericApplicationContext ctx;

    @Before
    public void setUp() {
        ctx = new AnnotationConfigApplicationContext(DataServiceConfig.class);
        assertNotNull(ctx);
        singerService = ctx.getBean(SingerService.class);
        assertNotNull(singerService);
    }

    @Test
    public void testDataServiceConfig() {
        List<Singer> singers = singerService.findAll();
        assertNotNull(singers);
        assertNotEquals(0, singers.size());
    }

    @After
    public void close() {
        Optional.of(ctx).ifPresent(AbstractApplicationContext::close);
    }
}
