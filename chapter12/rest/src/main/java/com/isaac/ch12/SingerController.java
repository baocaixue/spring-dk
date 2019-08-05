package com.isaac.ch12;

import com.isaac.ch12.entities.Singers;
import com.isaac.ch12.service.SingerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("singer")
public class SingerController {
    private static final Logger logger = LoggerFactory.getLogger(SingerController.class);

    @Autowired
    private SingerService singerService;

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping("listData")
    public Singers listData() {
        return new Singers(singerService.findAll());
    }
}
