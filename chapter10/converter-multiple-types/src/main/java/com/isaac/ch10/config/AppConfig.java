package com.isaac.ch10.config;

import com.isaac.ch10.Singer;
import com.isaac.ch10.SingerToAnotherSingerConverter;
import com.isaac.ch10.StringToDateTimeConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;

@Configuration
@ComponentScan(basePackages = "com.isaac.ch10")
public class AppConfig {
    @Bean
    public Singer taylor(StringToDateTimeConverter converter) throws MalformedURLException {
        Singer singer = new Singer();
        singer.setFirstName("Taylor");
        singer.setLastName("Swift");
        singer.setBirthDate(converter.convert("1989-12-13"));
        singer.setPersonalSite(new URL("https://www.taylorswift.com/"));
        return singer;
    }

    @Bean
    public ConversionServiceFactoryBean conversionService(StringToDateTimeConverter converter) {
        ConversionServiceFactoryBean serviceFactoryBean = new ConversionServiceFactoryBean();
        serviceFactoryBean.setConverters(new HashSet<>(Arrays.asList(converter, new SingerToAnotherSingerConverter())));
        serviceFactoryBean.afterPropertiesSet();
        return serviceFactoryBean;
    }
}
