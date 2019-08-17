package com.isaac.ch13;

import com.isaac.ch13.entities.Singer;
import com.isaac.ch13.service.SingerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/singer")
@Slf4j
public class SingerController {
    @Autowired
    private SingerService singerService;

    @GetMapping(value = "/listData")
    @ResponseBody
    public List<Singer> listData() {
        return singerService.findAll();
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public Singer findSingerById(@PathVariable Long id) {
        return singerService.findById(id);
    }

    @PostMapping
    @ResponseBody
    public Singer create(@RequestBody Singer singer) {
        log.info("Creating singer: " + singer);
        singerService.save(singer);
        log.info("Singer created. " + singer);
        return singer;
    }

    @PutMapping
    @ResponseBody
    public Singer update(@RequestBody Singer singer){
        assert  singer.getId() != null;
        log.info("Updating singer: " + singer);
        singerService.save(singer);
        log.info("Singer updated. " + singer);
        return singer;
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        log.info("Deleting singer with id: " + id);
        singerService.delete(singerService.findById(id));
        log.info("Deleted. ");
    }
}
