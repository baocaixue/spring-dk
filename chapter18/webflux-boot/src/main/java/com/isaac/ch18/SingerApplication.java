package com.isaac.ch18;

import com.isaac.ch18.web.SingerHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ServletHttpHandlerAdapter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.io.IOException;
import java.util.HashMap;

//@SpringBootApplication
@Slf4j
public class SingerApplication {
    @Autowired
    private SingerHandler singerHandler;

    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext ctx = new SpringApplicationBuilder(SingerApplication.class).properties(new HashMap<String, Object>() {{
            put("server.port", "8080");
            put("spring.jpa.hibernate.ddl-auto", "create-drop");
        }}).run(args);
        assert ctx != null;
        log.info("SingerApplication Started...");

        System.in.read();
        ctx.close();
    }

    private RouterFunction<ServerResponse> routerFunction() {
        return RouterFunctions.route(RequestPredicates.GET("/test"), request -> ServerResponse.ok().body(BodyInserters.fromObject("Hello World")))
                .andRoute(RequestPredicates.GET("/singers"), singerHandler.list)
                .andRoute(RequestPredicates.GET("/singers/{id}"), singerHandler.show)
                .andRoute(RequestPredicates.POST("/singers"), singerHandler.save)
                .filter((request, next) -> {
                    log.info("Before handler invocation. " + request.path());
                    return next.handle(request);
                });
    }

    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        HttpHandler httpHandler = RouterFunctions.toHttpHandler(routerFunction());
        ServletRegistrationBean<ServletHttpHandlerAdapter> registrationBean = new ServletRegistrationBean<>(new ServletHttpHandlerAdapter(httpHandler), "/");
        registrationBean.setLoadOnStartup(1);
        registrationBean.setAsyncSupported(true);
        return registrationBean;
    }
}
