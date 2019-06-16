package com.isaac.ch3.config;

import com.isaac.ch3.annotated.Singer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Map;

public class AliasConfigDemo {
    @Configuration static class AliasBeanConfig {
        @Bean(name = {"singer", "Taylor", "Jack"}) public Singer singer() {
            return new Singer();
        }
    }

    public static void main(String[] args){
        ApplicationContext context = new AnnotationConfigApplicationContext(AliasBeanConfig.class);
        Map<String, Singer> beans = context.getBeansOfType(Singer.class);
        beans.forEach((key, value) -> System.out.println("id: " + key + "\t aliases: " + Arrays.toString(context.getAliases(key))));
    }
}
