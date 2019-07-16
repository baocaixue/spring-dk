package com.isaac.ch8;

import com.isaac.ch8.config.DataJpaConfig;
import com.isaac.ch8.entities.Album;
import com.isaac.ch8.entities.Singer;
import com.isaac.ch8.service.AlbumService;
import com.isaac.ch8.service.SingerService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class SingerDataJpaTest {
    private static Logger logger = LoggerFactory.getLogger(SingerDataJpaTest.class);

    private GenericApplicationContext ctx;
    private SingerService singerService;
    private AlbumService albumService;


    @Before
    public void setUp(){
        ctx = new AnnotationConfigApplicationContext(DataJpaConfig.class);
        singerService = ctx.getBean(SingerService.class);
        albumService = ctx.getBean(AlbumService.class);
        assertNotNull(singerService);
        assertNotNull(albumService);
    }

    @Test
    public void testFindAll(){
        List<Singer> singers = singerService.findAll();
        assertTrue(singers.size() > 0);
        listSingers(singers);
    }

    @Test
    public void testFindByFirstName(){
        List<Singer> singers = singerService.findByFirstName("John");
        assertTrue(singers.size() > 0);
        assertEquals(2, singers.size());
        listSingers(singers);
    }

    @Test
    public void testFindByFirstNameAndLastName(){
        List<Singer> singers = singerService.findByFirstNameAndLastName("John", "Mayer");
        assertTrue(singers.size() > 0);
        assertEquals(1, singers.size());
        listSingers(singers);
    }


    @Test
    public void testFindBySinger(){
        List<Singer> singers = singerService.findByFirstNameAndLastName("John", "Mayer");
        assertTrue(singers.size() > 0);
        assertEquals(1, singers.size());

        Singer singer = singers.get(0);
        List<Album> albums = albumService.findBySinger(singer);
        assertTrue(albums.size() > 0);
        assertEquals(2, albums.size());
        albums.forEach(a -> logger.info(a.toString()));
    }

    @Test
    public void testFindByTitle(){
        List<Album> albums = albumService.findByTitle("The");
        assertTrue(albums.size() > 0);
        assertEquals(2, albums.size());
        albums.forEach(a -> logger.info(a.toString() + ", Singer: "+ a.getSinger().getFirstName() + " "
                + a.getSinger().getLastName()));
    }

    private static void listSingers(List<Singer> singers) {
        logger.info(" ---- Listing singers:");
        singers.forEach(s-> logger.info(s.toString()));
    }

    @After
    public void tearDown() {
        ctx.close();
    }
}
