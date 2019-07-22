package com.isaac.ch9;

import com.isaac.ch9.config.ServiceConfig;
import com.isaac.ch9.entities.Singer;
import com.isaac.ch9.service.SingerService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class TxAnnotationDemo {
    public static void main(String[] args){
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(ServiceConfig.class);
        SingerService singerService = ctx.getBean(SingerService.class);
        singerService.findAll().forEach(System.out::println);

        Singer singer = singerService.findById(1L);
        singer.setFirstName("Isaac");
        singer.setLastName("Bao");
        singerService.save(singer);
        System.out.println("Singer saved Successfully : " + singer);
        System.out.println("Singers count(no transaction) : " + singerService.countAll());

        ctx.close();
    }
}
