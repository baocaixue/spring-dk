package com.isaac.ch16.controller;

import com.isaac.ch16.entities.Singer;
import com.isaac.ch16.service.SingerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/singers")
@Slf4j
public class SingerController {
    @Autowired
    private SingerService singerService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model uiModel) {
        log.info("Listing singers");
        List<Singer> singers = singerService.findAll();
        uiModel.addAttribute("singers", singers);

        log.info("No. of singers:" + singers.size());
        return "/singers/list";
    }
}
