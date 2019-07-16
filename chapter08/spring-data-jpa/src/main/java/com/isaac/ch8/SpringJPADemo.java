package com.isaac.ch8;

import com.isaac.ch8.entities.Singer;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.List;

public class SpringJPADemo {
    public static void main(String[] args){
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/app-context-xml.xml");
        ctx.refresh();

        SingerService service = ctx.getBean(SingerService.class);
        listSingers("FindAll ", service.findAll());
        listSingers("FindByFirstName  ", service.findByFirstName("John"));
        listSingers("FindByFirstNameAndLastName  ", service.findByFirstNameAndLastName("John", "Mayer"));
    }

    private static void listSingers(String message, List<Singer> singers) {
        System.out.println(message);
        singers.stream().map(Singer::toString).forEach(System.out::println);
    }
}
