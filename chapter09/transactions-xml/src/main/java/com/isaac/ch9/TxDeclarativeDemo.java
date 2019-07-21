package com.isaac.ch9;

import com.isaac.ch9.entities.Singer;
import com.isaac.ch9.service.SingerService;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TxDeclarativeDemo {
    public static void main(String[] args){
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/tx-declarative-app-context.xml");
        ctx.refresh();

        SingerService singerService = ctx.getBean(SingerService.class);
        singerService.findAll().forEach(System.out::println);

        Singer singer = singerService.findById(1L);
        singer.setFirstName("Isaac");
        singerService.save(singer);
        System.out.println("Singer saved successfully: " + singer);
        System.out.println("Singer count: " + singerService.countAll());

        ctx.close();
    }
}
