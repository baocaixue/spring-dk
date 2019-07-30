package com.isaac.ch11;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Service
public class AsyncServiceImpl implements AsyncService {
    private final Logger logger = LoggerFactory.getLogger(AsyncServiceImpl.class);

    @Override
    @Async
    public void asyncTask() {
        logger.info("Start execution of async. task");
        try {
            Thread.sleep(10000);
        } catch (Exception ex) {
            logger.error("Task Interruption", ex);
        }

        logger.info("Complete execution of async. task");
    }

    @Override
    @Async
    public Future<String> asyncWithReturn(String name) {
        logger.info("Start execution of async. task with return for "+ name);

        try {
            Thread.sleep(5000);
        } catch (Exception ex) {
            logger.error("Task Interruption", ex);
        }

        logger.info("Complete execution of async. task with return for " + name);

        return new AsyncResult<>("Hello: " + name);
    }
}
