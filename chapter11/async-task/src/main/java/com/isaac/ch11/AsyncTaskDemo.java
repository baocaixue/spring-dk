package com.isaac.ch11;

import com.isaac.ch11.config.AppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class AsyncTaskDemo {
    private final static Logger logger = LoggerFactory.getLogger(AsyncTaskDemo.class);

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        AsyncService service = ctx.getBean(AsyncService.class);
        for (int i = 0; i < 5; i++) {
            service.asyncTask();
        }
        ctx.close();

        Future<String> result1 = service.asyncWithReturn("John Mayer");
        Future<String> result2 = service.asyncWithReturn("Eric Clapton");
        Future<String> result3 = service.asyncWithReturn("BB King");


        Thread.sleep(6000);

        logger.info("Result1: " + result1.get());
        logger.info("Result2: " + result2.get());
        logger.info("Result3: " + result3.get());
    }
}
