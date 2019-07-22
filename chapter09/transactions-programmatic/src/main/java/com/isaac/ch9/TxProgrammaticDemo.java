package com.isaac.ch9;

import com.isaac.ch9.config.ServiceConfig;
import com.isaac.ch9.service.SingerService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class TxProgrammaticDemo {
    public static void main(String[] args){
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(ServiceConfig.class);
        SingerService singerService = ctx.getBean(SingerService.class);
        System.out.println(singerService.countAll());
        ctx.close();
    }
}
