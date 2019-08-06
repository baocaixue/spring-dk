package com.isaac.ch12;

import com.isaac.ch12.config.RestClientConfig;
import com.isaac.ch12.entities.Singer;
import com.isaac.ch12.entities.Singers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RestClientConfig.class})
public class RestClientTest {
    private static final Logger logger = LoggerFactory.getLogger(RestClientTest.class);

    private static final String URL_GET_ALL_SINGERS = "http://localhost:8080/rest/singer/listdata";
    private static final String URL_GET_SINGER_BY_ID = "http://localhost:8080/rest/singer/{id}";
    private static final String URL_CREATE_SINGER = "http://localhost:8080/rest/singer/";
    private static final String URL_UPDATE_SINGER = "http://localhost:8080/rest/singer/";
    private static final String URL_DELETE_SINGER = "http://localhost:8080/rest/singer/{id}";

    @Autowired
    private RestTemplate restTemplate;

    @Before
    public void setUp() {
        assertNotNull(restTemplate);
    }

    @Test
    public void testFindAll() {
        logger.info("---> test findAll");
        Singers singers = restTemplate.getForObject(URL_GET_ALL_SINGERS, Singers.class);
        assertNotNull(singers);
        assertEquals(3, singers.getSingers().size());
        listSingers(singers);
    }

    @Test
    public void testFindById() {
        logger.info("---> test findById");
        Singer singer = restTemplate.getForObject(URL_GET_SINGER_BY_ID, Singer.class, 1L);
        assertNotNull(singer);
        logger.info(singer.toString());
    }

    @Test
    public void testCreate() {
        logger.info("--> Testing create singer");
        Singer singerNew = new Singer();
        singerNew.setFirstName("BB");
        singerNew.setLastName("King");
        singerNew.setBirthDate(LocalDate.of(1940,8,16));
        singerNew = restTemplate.postForObject(URL_CREATE_SINGER, singerNew, Singer.class);

        logger.info("Singer created successfully: " + singerNew);

        Singers singers = restTemplate.getForObject(URL_GET_ALL_SINGERS, Singers.class);
        assertNotNull(singers);
        listSingers(singers);
    }

    @Test
    public void testUpdate() {
        logger.info("--> Testing update singer by id : 1");
        Singer singer = restTemplate.getForObject(URL_UPDATE_SINGER, Singer.class, 1);
        assertNotNull(singer);
        singer.setFirstName("John Clayton");
        restTemplate.put(URL_UPDATE_SINGER, singer);
        logger.info("Singer update successfully: " + singer);
    }

    @Test
    public void testDelete() {
        logger.info("--> Testing delete singer by id : 3");
        restTemplate.delete(URL_DELETE_SINGER, 3);
        Singers singers = restTemplate.getForObject(URL_GET_ALL_SINGERS, Singers.class);
        assertNotNull(singers);
        singers.getSingers().forEach(singer -> {
            if(singer.getId() == 3) {
                throw new RuntimeException("id 3 already delete, why founded?");
            }
        });
        listSingers(singers);
    }

    private void listSingers(Singers singers) {
        singers.getSingers().forEach(s -> logger.info(s.toString()));
    }
}
