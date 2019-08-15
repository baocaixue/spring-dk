package com.isaac.ch13;

import com.isaac.ch13.config.ServiceConfig;
import com.isaac.ch13.entities.Singer;
import com.isaac.ch13.service.SingerService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@Slf4j
@ContextConfiguration(classes = ServiceConfig.class)
@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
public class BaseConfigTest {
    @Autowired
    private SingerService singerService;

    @Test
    public void test() {
        List<Singer> singers = singerService.findAll();
        assertNotNull(singers);
        assertEquals(3, singers.size());
        singers.stream().map(Singer::toString).forEach(log::info);
    }

}
