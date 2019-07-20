package com.isaac.ch8.config;

import com.isaac.ch8.entities.Singer;
import com.isaac.ch8.repos.SingerRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.GregorianCalendar;

@Service
public class DBInitializer implements InitializingBean {
    @Autowired
    private SingerRepository singerRepository;

    @Override
    public void afterPropertiesSet() throws Exception {
        Singer isaac = new Singer();
        isaac.setFirstName("Isaac");
        isaac.setLastName("Bao");
        isaac.setBirthDate(new GregorianCalendar(1995, 1,3).getTime());
        singerRepository.save(isaac);

    }
}
