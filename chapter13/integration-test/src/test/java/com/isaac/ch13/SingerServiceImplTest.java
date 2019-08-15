package com.isaac.ch13;

import com.isaac.ch13.config.ServiceTestConfig;
import com.isaac.ch13.entities.Singer;
import com.isaac.ch13.service.SingerService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {ServiceTestConfig.class})
@ActiveProfiles("test")
@TestExecutionListeners(ServiceTestExecutionListener.class)
@Slf4j
public class SingerServiceImplTest extends AbstractTransactionalJUnit4SpringContextTests/*Spring对Junit提供的支持，包括Spring的DI和事务管理机制*/ {
    @Autowired
    private SingerService singerService;

    @PersistenceContext
    private EntityManager em;

    @Test
    @DataSets(setUpDataSet = "/SingerServiceImplTest.xls")
    public void testFindAll() {
        List<Singer> singers = singerService.findAll();
        assertEquals(1, singers.size());
        log.info(singers.get(0).toString());
    }

    @Test
    public void testAddSinger() throws Exception {
        deleteFromTables("SINGER");

        Singer singer = new Singer();
        singer.setFirstName("Stevie");
        singer.setLastName("Vaughan ");

        singerService.save(singer);
        em.flush();

        List<Singer> singers = singerService.findAll();
        assertEquals(1, singers.size());
    }

    @Test(expected=AssertionError.class)
    public void testAddSingerWithJSR349Error() throws Exception {
        deleteFromTables("SINGER");

        Singer singer = new Singer();

        singerService.save(singer);
        em.flush();

        List<Singer> singers = singerService.findAll();
        assertEquals(0, singers.size());
    }
}
