package com.isaac.ch8;

import com.isaac.ch8.config.JpaConfig;
import com.isaac.ch8.service.SingerSummaryService;
import com.isaac.ch8.service.SingerSummaryUntypeImpl;
import com.isaac.ch8.view.SingerSummary;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertNotNull;

public class SingerSummaryJPATest {
    private static Logger logger = LoggerFactory.getLogger(SingerSummaryJPATest.class);
    private GenericApplicationContext ctx;
    private SingerSummaryService singerSummaryService;
    private SingerSummaryUntypeImpl singerSummaryUntype;

    @Before
    public void setUp() {
        ctx = new AnnotationConfigApplicationContext(JpaConfig.class);
        singerSummaryService = ctx.getBean(SingerSummaryService.class);
        singerSummaryUntype = ctx.getBean(SingerSummaryUntypeImpl.class);
        assertNotNull(singerSummaryService);
        assertNotNull(singerSummaryUntype);
    }

    @Test
    public void testFindAllUntype() {
        Iterator<Object[]> it = singerSummaryUntype.findAllSingerSummary();

        while (it.hasNext()) {
            Object[] values = it.next();
            logger.info("Untype value firstName:" + values[0] + " one of album tile:" + values[1]);
        }

    }

    @Test
    public void testFindAll() {
        List<SingerSummary> singers = singerSummaryService.findAll();
        singers.stream().map(SingerSummary::toString).forEach(logger::info);
    }

    @After
    public void tearDown() {
        ctx.close();
    }
}
