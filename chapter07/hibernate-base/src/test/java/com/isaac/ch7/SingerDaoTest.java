package com.isaac.ch7;

import com.isaac.ch7.config.AppConfig;
import com.isaac.ch7.dao.SingerDao;
import com.isaac.ch7.entities.Album;
import com.isaac.ch7.entities.Singer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.*;

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

    @Test
    public void testFindAllWithAlbum(){
        listSingersWithAlbum(singerDao.findAllWithAlbum());
    }

    @Test
    public void testFindById() {
        listSingersWithAlbum(Collections.singletonList(singerDao.findById(1L)));
    }

    @Test
    public void testInsert() {
        Singer singer = new Singer();
        singer.setFirstName("才学");
        singer.setLastName("包");
        singer.setBirthDate(new Date(
                (new GregorianCalendar(1995, 1, 3)).getTime().getTime()));

        Album album = new Album();
        album.setTitle("测试专辑1");
        album.setReleaseDate(new java.sql.Date(
                (new GregorianCalendar(2017, 7, 18)).getTime().getTime()));
        singer.addAlbum(album);

        album = new Album();
        album.setTitle("测试专辑2");
        album.setReleaseDate(new java.sql.Date(
                (new GregorianCalendar(2018 ,3, 20)).getTime().getTime()));
        singer.addAlbum(album);

        singerDao.save(singer);

        List<Singer> allSingers = singerDao.findAllWithAlbum();
        assertEquals(4, allSingers.size());

        listSingersWithAlbum(allSingers);
    }

    @Test
    public void testUpdate() {
        Singer singer = singerDao.findById(1L);
        singer.setFirstName("才学");
        singer.setLastName("包");
        singerDao.save(singer);
        listSingersWithAlbum(singerDao.findAllWithAlbum());
    }

    @Test
    public void testDelte() {
        singerDao.delete(singerDao.findById(1L));

        List allSinger = singerDao.findAll();
        assertEquals(2, allSinger.size());
    }

    private static void listSingers(List<Singer> singers) {
        logger.info(" ---- Listing singers:");
        singers.forEach(singer -> logger.info(singer.toString()));
    }

    private static void listSingersWithAlbum(List<Singer> singers) {
        logger.info(" ---- Listing singers detail:");
        singers.forEach((Singer singer) -> {
            logger.info(singer.toString());
            Optional.ofNullable(singer.getAlbums()).ifPresent(albums -> albums.forEach(album -> logger.info("\t" + album)));
            Optional.ofNullable(singer.getInstruments()).ifPresent(instruments -> instruments.forEach(instrument -> logger.info("\t" + instrument.getInstrumentId())));
        });
    }

    @After
    public void tearDown(){
        ctx.close();
    }

}
