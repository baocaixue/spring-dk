package com.isaac.ch10;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.Formatter;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class ApplicationConversionServiceFactoryBean extends FormattingConversionServiceFactoryBean {
    private static Logger logger = LoggerFactory.getLogger(ApplicationConversionServiceFactoryBean.class);

    private static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";

    private DateTimeFormatter dateTimeFormatter;
    @Setter @Getter
    private String datePattern = DEFAULT_DATE_PATTERN;
    //自定义格式化器
    private Set<Formatter<?>> formatters = new HashSet<>();

    @PostConstruct
    public void init() {
        dateTimeFormatter = DateTimeFormatter.ofPattern(datePattern);
        formatters.add(getLocalDateFormatter());
        super.setFormatters(formatters);
    }

    public Formatter<LocalDate> getLocalDateFormatter() {
        return new Formatter<LocalDate>() {
            @Override
            public LocalDate parse(String text, Locale locale) throws ParseException {
                logger.info("Parsing date string : " + text);
                return LocalDate.parse(text, dateTimeFormatter);
            }

            @Override
            public String print(LocalDate object, Locale locale) {
                logger.info("Formatting datetime : " + object);
                return object.toString();
            }
        };
    }
}
