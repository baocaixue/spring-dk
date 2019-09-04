package com.isaac.ch18;

import com.isaac.ch18.config.ServerConfig;
import com.isaac.ch18.web.Server;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

@Slf4j
public class WebRunner {
    public static void main(String[] args) throws Exception {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(ServerConfig.class);
        Server server = ctx.getBean(Server.class);
        server.startTomcatServer();
        log.info("Tomcat started ");
        ctx.close();
    }
}
