package com.isaac.ch12;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.isaac.ch12.entities.Singer;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class RestRequestTest {
    private static final Logger logger = LoggerFactory.getLogger(RestRequestTest.class);
    private static final String URL_GET_ALL_SINGERS = "http://localhost:8080/singer/listdata";
    private static final ObjectMapper mapper = new ObjectMapper();

    private RestTemplate restTemplate = new RestTemplate();

    //@Before
    public void setUp() {
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.BASIC_ISO_DATE));
        mapper.registerModule(javaTimeModule);
    }

    //@Test
    public void testFindAll() throws IOException {
        String singersJson = restTemplate.getForObject(URL_GET_ALL_SINGERS, String.class);

        List<Singer> singers = mapper.readValue(singersJson, new TypeReference<List<Singer>>() {
        });
        singers.stream().map(Singer::toString).forEach(logger::info);
    }
}
