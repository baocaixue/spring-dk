package com.isaac.ch15;

import com.isaac.ch12.entities.Singer;
import com.isaac.ch12.service.SingerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/singer")
@Slf4j
public class SingerController {
    @Autowired
    private SingerService singerService;

    @GetMapping(value = "/listdata")
    public List<Singer> listData() {
        return singerService.findAll();
    }

    @GetMapping(value = "/{id}")
    public Singer findSingerById(@PathVariable Long id){
        return singerService.findById(id);
    }

    @PostMapping
    public Singer create(@RequestBody Singer singer) {
        log.info("Creating singer: " + singer);
        singerService.save(singer);
        log.info("Singer created.");
        return singer;
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id){
        log.info("Deleting singer with id: " + id);
        singerService.delete(singerService.findById(id));
        log.info("Singer deleted.");
    }
}
