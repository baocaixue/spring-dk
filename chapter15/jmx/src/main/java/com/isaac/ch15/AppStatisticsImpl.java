package com.isaac.ch15;

import com.isaac.ch12.service.SingerService;
import org.springframework.beans.factory.annotation.Autowired;

public class AppStatisticsImpl implements AppStatistics {
    @Autowired
    private SingerService singerService;

    @Override
    public int getTotalSingerCount() {
        return singerService.findAll().size();
    }
}
