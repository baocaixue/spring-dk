package com.isaac.ch6;

import com.isaac.ch6.config.TemplateJdbcCfg;
import com.isaac.ch6.dao.SingerDao;
import com.isaac.ch6.entries.Singer;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class TemplateJdbcCfgTest {
    private static Logger logger = LoggerFactory.getLogger(TemplateJdbcCfgTest.class);

    @Test
    public void test() {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(TemplateJdbcCfg.class);
        SingerDao singerDao = ctx.getBean("singerDao", SingerDao.class);
        singerDao.findAll().stream().map(Singer::toString).forEach(logger::info);
    }
}
