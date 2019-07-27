package com.isaac.ch10;

import lombok.Getter;
import lombok.Setter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 自定义转换器：将String格式的日期转换为Singer类的birthDate属性。
 */
@Component
public class StringToDateTimeConverter implements Converter<String, LocalDate> {
    private static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
    private DateTimeFormatter dateFormatter;
    @Getter @Setter
    private String datePattern = DEFAULT_DATE_PATTERN;

    @PostConstruct
    public void init() {
        this.dateFormatter = DateTimeFormatter.ofPattern(datePattern);
    }

    @Override
    public LocalDate convert(String source) {
        return LocalDate.parse(source,dateFormatter);
    }
}
