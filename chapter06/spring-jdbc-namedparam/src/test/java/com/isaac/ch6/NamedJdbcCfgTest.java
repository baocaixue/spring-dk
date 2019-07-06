package com.isaac.ch6;

import com.isaac.ch6.config.NamedJdbcCfg;
import com.isaac.ch6.dao.SingerDao;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class NamedJdbcCfgTest {
    @Test
    public void testCfg() {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(NamedJdbcCfg.class);
        SingerDao singerDao = ctx.getBean("singerDao", SingerDao.class);
        String singerName = singerDao.findNameById(1L);
        Assert.assertEquals("John Mayer", singerName);
        ctx.close();
    }
}
