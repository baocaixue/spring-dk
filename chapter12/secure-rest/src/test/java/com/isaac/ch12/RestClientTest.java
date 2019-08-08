package com.isaac.ch12;

import com.isaac.ch12.config.RestClientConfig;
import com.isaac.ch12.entities.Singer;
import com.isaac.ch12.entities.Singers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RestClientConfig.class)
public class RestClientTest {
    private final Logger logger = LoggerFactory.getLogger(RestClientTest.class);
    private static final String URL_GET_ALL_SINGERS = "http://localhost:8080/secure-rest/rest/singer/listdata";

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void testFindAll() {
        logger.info("--> Testing retrieve all singers");
        Singers singers = restTemplate.getForObject(URL_GET_ALL_SINGERS, Singers.class);
        assertNotNull(singers);
        assertEquals(3, singers.getSingers().size());
        singers.getSingers().stream().map(Singer::toString).forEach(logger::info);
    }
}
