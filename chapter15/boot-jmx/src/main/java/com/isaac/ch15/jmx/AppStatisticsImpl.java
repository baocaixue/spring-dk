package com.isaac.ch15.jmx;

import com.isaac.ch15.entities.Singer;
import com.isaac.ch15.service.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@ManagedResource(description = "自定义监控Demo", objectName = "jmxDemo:name=SpringDKSingerApp")
public class AppStatisticsImpl implements AppStatistics {
    @Autowired
    private SingerService singerService;

    @ManagedAttribute(description = "总共歌手数量")//调用方法结果显示在Attributes选项卡中
    @Override
    public int getTotalSingerCount() {
        return singerService.findAll().size();
    }

    @ManagedOperation
    @Override
    public String findTaylor() {
        return Optional.ofNullable(singerService.findByFirstName("Taylor")).map(li -> li.get(0)).map(Singer::toString).orElse("Sorry, Not found Taylor");
    }
}
