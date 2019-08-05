package com.isaac.ch12;

import org.exolab.castor.mapping.GeneralizedFieldHandler;
import org.exolab.castor.mapping.ValidityException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class DateTimeFieldHandler extends GeneralizedFieldHandler {
    private static final Logger logger = LoggerFactory.getLogger(DateTimeFieldHandler.class);
    private static String dateFormatPattern;

    @Override
    public Object convertUponGet(Object value) {
        LocalDate localDate = (LocalDate) value;
        return format(localDate);
    }

    private String format(final LocalDate localDate) {
        String dateStr = "";
        if(localDate != null) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern(dateFormatPattern);
            dateStr = dtf.format(localDate);
        }
        return dateStr;
    }

    @Override
    public Object convertUponSet(Object value) {
        String dateStr = (String) value;
        return parse(dateStr);
    }

    private LocalDate parse(String dateStr) {
        LocalDate date = LocalDate.now();
        if(dateStr != null) {
            date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern(dateFormatPattern));
        }
        return date;
    }

    @Override
    public Class getFieldType() {
        return LocalDate.class;
    }

    @Override
    public void setConfiguration(Properties config) throws ValidityException {
        dateFormatPattern = config.getProperty("date-format");
    }
}
