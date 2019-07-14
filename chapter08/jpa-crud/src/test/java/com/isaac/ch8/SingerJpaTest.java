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

import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.*;

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

    @Test
    public void testFindAllWithAlbum() {
        List<Singer> singers = singerService.findAllWithAlbum();
        assertEquals(3, singers.size());
        listSingersWithAlbums(singers);
    }

    @Test
    public void testFindSingerById() {
        Singer eric = singerService.findById(2L);
        assertEquals("Eric", eric.getFirstName());
        listSingersWithAlbums(Collections.singletonList(eric));
    }

    @Test
    public void testInsert() {
        Singer singer = new Singer();
        singer.setFirstName("Isaac");
        singer.setLastName("Bao");
        singer.setBirthDate(new Date((new GregorianCalendar(1995, 1, 3)).getTime().getTime()));
        Singer save = singerService.save(singer);
        assertEquals(4, singerService.findAll().size());
        save.setFirstName("Isaac(1)");
        Singer update = singerService.save(save);
        String firstName = singerService.findById(update.getId()).getFirstName();
        assertEquals("Isaac(1)", firstName);

    }

    @Test
    public void testDelete() {
        Singer singer = singerService.findById(2L);
        assertNotNull(singer);
        singerService.delete(singer);
        listSingersWithAlbums(singerService.findAllWithAlbum());
    }

    @Test
    public void testFindAllByNativeQuery() {
        List<Singer> singers = singerService.findAllByNativeQuery();
        singers.stream().map(Singer::toString).forEach(logger::info);
    }

    private void listSingersWithAlbums(List<Singer> singers) {
        logger.info("--- listing singers with albums---");
        singers.forEach(singer -> {
            logger.info("singer " + singer);
            if (singer.getAlbums() != null)
                singer.getAlbums().forEach(a -> logger.info("\t" + a.toString()));
            if (singer.getInstruments() != null)
                singer.getInstruments().forEach(i -> logger.info("\tInstrument: " + i.getInstrumentId()));
        });
    }

    @After
    public void tearDown(){
        ctx.close();
    }
}
