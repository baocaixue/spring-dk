package com.isaac.ch12;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {
    private static Logger logger = LoggerFactory.getLogger(WeatherService.class);

    /**
     * 因为SimpleRabbitListenerContainerFactory bean是由Spring Boot处理的，因此不需要为@RabbitListener添加containerFactory的值
     * @param stateCode
     */
    @RabbitListener(queues="forecasts")
    public void getForecast(String stateCode) {
        if ("MA".equals(stateCode)) {
            logger.info("Cold");
            return;
        }
        if ("FL".equals(stateCode)) {
            logger.info("Hot");
            return;
        }
        logger.info("Not available at this time");

    }
}
