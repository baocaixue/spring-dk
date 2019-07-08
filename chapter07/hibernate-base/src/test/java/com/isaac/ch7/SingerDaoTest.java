package com.isaac.ch7;

import com.isaac.ch7.config.AppConfig;
import com.isaac.ch7.dao.SingerDao;
import com.isaac.ch7.entities.Singer;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SingerDaoTest {
    private static Logger logger = LoggerFactory.getLogger(SingerDaoTest.class);

    private GenericApplicationContext ctx;
    private SingerDao singerDao;

    @Before
    public void setUp(){
        ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        singerDao = ctx.getBean(SingerDao.class);
        assertNotNull(singerDao);
    }

    @Test
    public void testFindAll(){
        //List<Singer> singers = singerDao.findAll();
        GenericXmlApplicationContext ctx1 = new GenericXmlApplicationContext();
        ctx1.load("classpath:spring/app-context-xml.xml");
        ctx1.refresh();
        SingerDao singerDao = ctx1.getBean("singerDao", SingerDao.class);
        List singers = singerDao.findAll();
        assertEquals(3, singers.size());
        listSingers(singers);
        ctx1.close();
    }

    private static void listSingers(List<Singer> singers) {
        logger.info(" ---- Listing singers:");
        singers.forEach(singer -> logger.info(singer.toString()));
    }

}
