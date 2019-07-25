package com.isaac.ch10;

import lombok.Getter;
import lombok.Setter;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 自定义转换器：将String格式的日期转换为Singer类的birthDate属性。
 */
@Component
public class StringToDateTimeConverter implements Converter<String, DateTime> {
    private static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
    private DateTimeFormatter dateFormatter;
    @Getter @Setter
    private String datePattern = DEFAULT_DATE_PATTERN;

    @PostConstruct
    public void init() {
        this.dateFormatter = DateTimeFormat.forPattern(datePattern);
    }

    @Override
    public DateTime convert(String source) {
        return dateFormatter.parseDateTime(source);
    }
}
