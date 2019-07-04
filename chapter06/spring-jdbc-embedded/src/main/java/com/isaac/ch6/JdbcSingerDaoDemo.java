package com.isaac.ch6;

import com.isaac.ch6.dao.SingerDao;
import org.springframework.context.support.GenericXmlApplicationContext;

public class JdbcSingerDaoDemo {
    public static void main(String[] args){
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/embedded-h2-cfg.xml");
        ctx.refresh();

        SingerDao singerDao = ctx.getBean("singerDao", SingerDao.class);
        String name = singerDao.findNameById(1L);
        System.out.println("Id 1L 's Name is " + name);
        ctx.close();
    }
}
