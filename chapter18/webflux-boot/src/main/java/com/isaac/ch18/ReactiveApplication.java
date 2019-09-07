package com.isaac.ch18;

import com.isaac.ch18.entities.Singer;
import com.isaac.ch18.repos.ReactiveSingerRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;

@Slf4j
@RestController
@SpringBootApplication
public class ReactiveApplication {
    @Autowired
    private ReactiveSingerRepo singerRepo;

    @GetMapping(value = "/all", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Singer> oneByOne() {
        Flux<Singer> singers = singerRepo.findAll();
        Flux<Long> periodFlux = Flux.interval(Duration.ofSeconds(2));
        return Flux.zip(singers, periodFlux).map(Tuple2::getT1);
    }

    @Bean
    public WebClient webClient() {
        return WebClient.create("http://localhost:8080");
    }

    @Bean
    public CommandLineRunner clr(WebClient webClient) {
        return args -> webClient.get().uri("/all").accept(MediaType.TEXT_EVENT_STREAM).exchange().flatMapMany(cr -> cr.bodyToFlux(Singer.class)).subscribe(System.out::println);
    }

    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext ctx = new SpringApplicationBuilder(ReactiveApplication.class).properties(new HashMap<String, Object>() {{
            put("server.port", "8080");
            put("spring.jpa.hibernate.ddl-auto", "create-drop");
        }}).run(args);
        assert ctx != null;
        log.info("Reactive Application Started.");
        System.in.read();
        ctx.close();
    }
}
