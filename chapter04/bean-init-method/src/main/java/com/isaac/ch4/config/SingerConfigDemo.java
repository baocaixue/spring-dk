package com.isaac.ch4.config;

import com.isaac.ch4.Singer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import static com.isaac.ch4.Singer.getBean;

public class SingerConfigDemo {
    @Configuration
    static class SingerConfig {
        @Lazy
        @Bean(initMethod = "init")
        Singer singerOne() {
            Singer singerOne = new Singer();
            singerOne.setName("John Mayer");
            singerOne.setAge(39);
            return singerOne;
        }

        @Lazy
        @Bean(initMethod = "init")
        Singer singerTwo() {
            Singer singerTwo = new Singer();
            singerTwo.setAge(31);
            return singerTwo;
        }

        @Lazy
        @Bean(initMethod = "init")
        Singer singerThree() {
            Singer singerThree = new Singer();
            singerThree.setName("John Mayer");
            return singerThree;
        }
    }

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SingerConfig.class);

        getBean("singerOne", ctx);
        getBean("singerTwo", ctx);
        getBean("singerThree", ctx);
    }
}
