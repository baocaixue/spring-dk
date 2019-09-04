package com.isaac.ch18.web;

import com.isaac.ch18.config.TestConfig;
import com.isaac.ch18.entities.Singer;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
public class SingerHandlerTest {
    @Autowired
    private WebTestClient testClient;

    @Test
    public void getAll() throws Exception {
        testClient.get().uri("/")//.accept(MediaType.TEXT_EVENT_STREAM)
                .exchange()
                .expectStatus().isOk();
//                .expectHeader().contentType(MediaType.APPLICATION_JSON)
//                .expectBodyList(Singer.class).hasSize(3).consumeWith(Assertions::assertNotNull);
    }
}
