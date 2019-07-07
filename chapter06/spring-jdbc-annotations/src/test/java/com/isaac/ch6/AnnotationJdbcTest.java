package com.isaac.ch6;

import com.isaac.ch6.config.AppConfig;
import com.isaac.ch6.dao.SingerDao;
import com.isaac.ch6.entries.Album;
import com.isaac.ch6.entries.Singer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.sql.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.*;

public class AnnotationJdbcTest {
    private GenericApplicationContext ctx;
    private SingerDao singerDao;

    @Before
    public void setUp() {
        ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        singerDao = ctx.getBean("singerDao", SingerDao.class);
        assertNotNull(singerDao);
    }

    @Test
    public void testFindAll(){
        List<Singer> singers = singerDao.findAll();
        assertNotEquals(0, singers.size());
        listSingers(singers);
    }

    @Test
    public void testFindByFirstName() {
        List<Singer> john = singerDao.findByFirstName("John");
        assertEquals(1, john.size());
        listSingers(john);
    }

    @Test
    public void testFindFirstNameById() {
        String firstName = singerDao.findFirstNameById(1L);
        assertEquals("John", firstName);
    }

    @Test
    public void testInsertSinger() {
        Singer isaac = new Singer();
        isaac.setFirstName("Isaac");
        isaac.setLastName("Bao");
        isaac.setBirthDate(new Date((new GregorianCalendar(1995, 1, 3)).getTime().getTime()));
        singerDao.insert(isaac);
        assertNotEquals(0L, isaac.getId().longValue());
    }

    @Test
    public void testUpdate() {
        Singer isaac = new Singer();
        isaac.setId(4L);
        isaac.setFirstName("Isaac1");
        isaac.setLastName("Bao1");
        isaac.setBirthDate(new Date((new GregorianCalendar(1995, 1, 3)).getTime().getTime()));
        singerDao.update(isaac);
    }

    @Test
    public void testDelete() {
        singerDao.delete(5L);
    }

    private void listSingers(List<Singer> singers){
        singers.forEach(singer -> {
            System.out.println(singer);
            if (singer.getAlbums() != null) {
                for (Album album : singer.getAlbums()) {
                    System.out.println("\t--> " + album);
                }
            }
        });
    }

    @After
    public void close() {
        ctx.close();
    }
}
