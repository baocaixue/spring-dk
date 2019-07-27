package com.isaac.ch10;

import org.springframework.core.convert.converter.Converter;

/**
 * 将Singer类的任何实例转换为AnotherSinger类，转换时，Singer类的firstName和lastName分别称为AnotherSinger类的lastName和firstName
 */
public class SingerToAnotherSingerConverter implements Converter<Singer, AnotherSinger> {
    @Override
    public AnotherSinger convert(Singer source) {
        AnotherSinger anotherSinger = new AnotherSinger();
        anotherSinger.setFirstName(source.getLastName());
        anotherSinger.setLastName(source.getFirstName());
        anotherSinger.setBirthDate(source.getBirthDate());
        anotherSinger.setPersonalSite(source.getPersonalSite());
        return anotherSinger;
    }
}
