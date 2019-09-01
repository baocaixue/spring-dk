package com.isaac.ch17.controller;

import com.isaac.ch17.domain.Stock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
@Slf4j
public class StockController {
    @Autowired
    private TaskScheduler taskScheduler;
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    private List<Stock> stocks = new ArrayList<>();
    private Random random = new Random(System.currentTimeMillis());

    @PostConstruct
    public void initStocks() {
        stocks.add(new Stock("VMW", 1.00d));
        stocks.add(new Stock("EMC", 1.00d));
        stocks.add(new Stock("GOOG", 1.00d));
        stocks.add(new Stock("IBM", 1.00d));
        stocks.add(new Stock("IBM", 1.00d));
    }

    /**
     * 向所有订阅客户端连续广播，每秒一次
     */
    @PostConstruct
    private void broadcastTimePeriodically() {
        taskScheduler.scheduleAtFixedRate(this::broadcastUpdatedPrices, 1000);
    }

    @MessageMapping("/addStock")
    public void addStock(Stock stock) throws Exception {
        stocks.add(stock);
        broadcastUpdatedPrices();
    }


    /**
     * 股票更新，广播给所有订阅者
     */
    private void broadcastUpdatedPrices() {
        stocks.forEach(stock -> stock.setPrice(stock.getPrice() + (getUpdatedStockPrice() * stock.getPrice())));
        simpMessagingTemplate.convertAndSend("/topic/price", stocks);
    }

    /**
     * 随机更新价格
     */
    public Double getUpdatedStockPrice() {
        double priceChange = random.nextDouble() * 5.0;

        if (random.nextInt(2) == 1) {
            priceChange = -priceChange;
        }

        return priceChange / 100.0;
    }
}
