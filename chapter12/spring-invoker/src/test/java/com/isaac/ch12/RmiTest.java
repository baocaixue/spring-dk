package com.isaac.ch12;

import com.isaac.ch12.config.RmiClientConfig;
import com.isaac.ch12.entities.Singer;
import com.isaac.ch12.service.SingerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

import java.util.List;

@ContextConfiguration(classes = RmiClientConfig.class)
@RunWith(SpringRunner.class)
public class RmiTest {
    private Logger logger = LoggerFactory.getLogger(RmiTest.class);

    @Autowired
    private SingerService singerService;

    @Test
    public void testRmiAll() {
        List<Singer> singers = singerService.findAll();
        assertEquals(3, singers.size());
        singers.stream().map(Singer::toString).forEach(logger::info);
    }

}
