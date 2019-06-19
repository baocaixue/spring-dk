package com.isaac.ch4.config;

import com.isaac.ch4.MessageDigestFactoryBean;
import com.isaac.ch4.MessageDigester;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;

import java.security.MessageDigest;

public class MessageDigestConfigDemo {
    @Configuration
    static class MessageDigestConfig{
        @Bean
        public MessageDigestFactoryBean shaDigest() {
            MessageDigestFactoryBean factoryOne = new MessageDigestFactoryBean();
            factoryOne.setAlgorithmName("SHA1");
            return factoryOne;
        }

        @Bean
        public MessageDigestFactoryBean defaultDigest() {
            return new MessageDigestFactoryBean();
        }

        @Bean
        public MessageDigester digester() throws Exception {
            MessageDigester messageDigester = new MessageDigester();
            messageDigester.setDigester1(shaDigest().getObject());
            messageDigester.setDigester2(defaultDigest().getObject());
            return messageDigester;
        }
    }

    public static void main(String[] args){
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(MessageDigestConfig.class);
        MessageDigester digester = (MessageDigester) ctx.getBean("digester");
        digester.digest("hello");
        ctx.close();
    }
}
