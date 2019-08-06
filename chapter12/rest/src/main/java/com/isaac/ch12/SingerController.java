package com.isaac.ch12;

import com.isaac.ch12.entities.Singer;
import com.isaac.ch12.entities.Singers;
import com.isaac.ch12.service.SingerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("singer")
public class SingerController {
    private static final Logger logger = LoggerFactory.getLogger(SingerController.class);

    @Autowired
    private SingerService singerService;

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping("listdata")
    public Singers listData() {
        return new Singers(singerService.findAll());
    }

    @ResponseBody
    @GetMapping(value = "{id}")
    public Singer findSingerById(@PathVariable Long id) {
        return singerService.findById(id);
    }

    @ResponseBody
    @PostMapping
    public Singer create(@RequestBody Singer singer){
        logger.info("now create singer: " + singer);
        singerService.save(singer);
        logger.info("create successful with info:" + singer);
        return singer;
    }

    @ResponseBody
    @PutMapping
    public void update(@RequestBody Singer singer) {
        logger.info("Updating singer: " + singer);
        singerService.save(singer);
        logger.info("Singer updated successfully with info: " + singer);
    }

    @ResponseBody
    @DeleteMapping(value="/{id}")
    public void delete(@PathVariable Long id) {
        logger.info("Deleting singer with id: " + id);
        Singer singer = singerService.findById(id);
        singerService.delete(singer);
        logger.info("Singer deleted successfully");
    }
}
