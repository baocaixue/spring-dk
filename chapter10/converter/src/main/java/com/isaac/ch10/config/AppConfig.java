package com.isaac.ch10.config;

import com.isaac.ch10.Singer;
import com.isaac.ch10.StringToDateTimeConverter;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.converter.Converter;

import java.net.URL;
import java.util.HashSet;
import java.util.Set;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = "com.isaac.ch10")
public class AppConfig {
    @Value("${date.format.pattern}")
    private String dateFormatPattern;

    @Bean
    public Singer taylor(@Value("${countrySinger.firstName}") String firstName,
                         @Value("${countrySinger.lastName}") String lastName,
                         @Value("${countrySinger.personalSite}") URL personalSite,
                         @Value("${countrySinger.birthDate}") DateTime birthDate) {
        Singer singer = new Singer();
        singer.setFirstName(firstName);
        singer.setLastName(lastName);
        singer.setPersonalSite(personalSite);
        singer.setBirthDate(birthDate);
        return singer;
    }

    /**
     * 指示Spring使用各类型转换系统。如果没有定义转换服务bean，Spring将使用基于PropertyEditor的系统
     */
    @Bean
    public ConversionServiceFactoryBean conversionService(Converter converter) {
        ConversionServiceFactoryBean conversionServiceFactoryBean = new ConversionServiceFactoryBean();
        Set<Converter> converters = new HashSet<>();
        converters.add(converter);
        conversionServiceFactoryBean.setConverters(converters);
        conversionServiceFactoryBean.afterPropertiesSet();
        return conversionServiceFactoryBean;
    }

}
