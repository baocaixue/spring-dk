package com.isaac.ch14;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.io.IOException;
import java.time.LocalDate;

@Slf4j
public class RuleEngineDemo {
    public static void main(String[] args) throws IOException {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/app-context.xml");
        ctx.refresh();

        SingerService singerService = ctx.getBean(SingerService.class);
        Singer taylor = Singer.builder().id(1L).firstName("Taylor").lastName("Swift").birthDate(LocalDate.of(1989, 12, 13)).build();
        singerService.applyRule(taylor);

        log.info("Singer: " + taylor);
        System.in.read();

        singerService.applyRule(taylor);
        log.info("Singer: " + taylor);

        ctx.close();
    }
}
