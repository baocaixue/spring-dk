package com.issac.ch6;

import com.isaac.ch6.config.EmbeddedJdbcConfig;
import com.isaac.ch6.config.PopulatorJdbcConfig;
import com.isaac.ch6.dao.SingerDao;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import static org.junit.Assert.*;

public class JdbcCfgTest {
    private static Logger logger = LoggerFactory.getLogger(JdbcCfgTest.class);
    @Test
    public void testH2DB(){
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/embedded-h2-cfg.xml");
        ctx.refresh();
        testDao(ctx.getBean("singerDao", SingerDao.class));
        ctx.close();
    }

    @Test
    public void testDerby() {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/embedded-derby-cfg.xml");
        ctx.refresh();
        testDao((SingerDao) ctx.getBean("singerDao"));
        ctx.close();
    }

    @Test
    public void testEmbeddedJavaConfig() {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(EmbeddedJdbcConfig.class);
        SingerDao singerDao = ctx.getBean("singerDao", SingerDao.class);
        testDao(singerDao);
        ctx.close();
    }

    @Test
    public void testPopulatorJdbcConfig() {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(PopulatorJdbcConfig.class);
        testDao((SingerDao) ctx.getBean("singerDao"));
        ctx.close();
    }

    private void testDao(SingerDao singerDao) {
        assertNotNull(singerDao);
        String singerName = singerDao.findNameById(1L);
        logger.info(singerName);
        assertTrue("John Mayer".equals(singerName));
    }
}
