package com.isaac.ch10.config;

import com.isaac.ch10.ApplicationConversionServiceFactoryBean;
import com.isaac.ch10.Singer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.Locale;

@Configuration
public class AppConfig {
    @Bean
    public ApplicationConversionServiceFactoryBean conversionService() {
        return new ApplicationConversionServiceFactoryBean();
    }

    @Bean
    public Singer taylor() throws MalformedURLException, ParseException {
        Singer taylor = new Singer();
        taylor.setFirstName("Taylor");
        taylor.setLastName("Swift");
        //Parsing date string
        taylor.setBirthDate(conversionService().getLocalDateFormatter().parse("1989-12-13", Locale.CHINA));
        taylor.setPersonalSite(new URL("https://www.taylorswift.com/"));
        return taylor;
    }
}
