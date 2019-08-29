package com.isaac.ch16.controller;

import com.google.common.collect.Lists;
import com.isaac.ch16.entities.Singer;
import com.isaac.ch16.service.SingerService;
import com.isaac.ch16.util.Message;
import com.isaac.ch16.util.SingerGrid;
import com.isaac.ch16.util.UrlUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/singers")
@Slf4j
@AllArgsConstructor
public class SingerController {
    private SingerService singerService;
    //检索具有国际化支持的消息
    private MessageSource messageSource;

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model uiModel) {
        log.info("Listing singers");
        List<Singer> singers = singerService.findAll();
        uiModel.addAttribute("singers", singers);

        log.info("No. of singers:" + singers.size());
        return "singers/list";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String show(@PathVariable Long id, Model uiModel){
        Singer singer = singerService.findById(id);
        uiModel.addAttribute("singer", singer);
        return "singers/show";
    }

    @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
    public String updateForm(@PathVariable Long id, Model uiModel) {
        uiModel.addAttribute("singer", singerService.findById(id));
        return "singers/update";
    }

    @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.POST)
    public String update(@Valid Singer singer, BindingResult bindingResult, Model uiModel,
                         HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes,
                         Locale locale) {
        log.info("Updating singer");
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("message", new Message("error",
                    messageSource.getMessage("singer_save_fail", new Object[]{}, locale)));
            uiModel.addAttribute("singer", singer);
            return "singers/update";
        }
        uiModel.asMap().clear();
        redirectAttributes.addFlashAttribute("message", new Message("success",
                messageSource.getMessage("singer_save_success", new Object[]{}, locale)));
        singerService.save(singer);
        return "redirect:/singers/" + UrlUtil.encodeUrlPathSegment(singer.getId().toString(),
                httpServletRequest);
    }

    @RequestMapping(method = RequestMethod.POST)
    public String create(@Valid Singer singer, BindingResult bindingResult, Model uiModel,
                         HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes,
                         Locale locale, @RequestParam(value="file", required=false) Part file) {
        log.info("Creating singer");
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("message", new Message("error",
                    messageSource.getMessage("singer_save_fail", new Object[]{}, locale)));
            uiModel.addAttribute("singer", singer);
            return "singers/create";
        }
        uiModel.asMap().clear();
        redirectAttributes.addFlashAttribute("message", new Message("success",
                messageSource.getMessage("singer_save_success", new Object[]{}, locale)));

        log.info("Singer id: " + singer.getId());

        // Process upload file
        if (file != null) {
            log.info("File name: " + file.getName());
            log.info("File size: " + file.getSize());
            log.info("File content type: " + file.getContentType());
            byte[] fileContent = null;
            try {
                InputStream inputStream = file.getInputStream();
                if (inputStream == null) log.info("File inputstream is null");
                fileContent = IOUtils.toByteArray(inputStream);
            } catch (IOException ex) {
                log.error("Error saving uploaded file");
            }
            singer.setPhoto(fileContent);
        }

        singerService.save(singer);
        return "redirect:/singers/";
    }

    @RequestMapping(value = "/photo/{id}", method = RequestMethod.GET)
    @ResponseBody
    public byte[] downloadPhoto(@PathVariable("id") Long id) {
        Singer singer = singerService.findById(id);

        if (singer.getPhoto() != null) {
            log.info("Downloading photo for id: {} with size: {}", singer.getId(),
                    singer.getPhoto().length);
        }

        return singer.getPhoto();
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(params = "form", method = RequestMethod.GET)
    public String createForm(Model uiModel) {
        Singer singer = new Singer();
        uiModel.addAttribute("singer", singer);

        return "singers/create";
    }

    @ResponseBody
    @RequestMapping(value = "/listgrid", method = RequestMethod.GET, produces="application/json")
    public SingerGrid listGrid(@RequestParam(value = "page", required = false) Integer page,
                               @RequestParam(value = "rows", required = false) Integer rows,
                               @RequestParam(value = "sidx", required = false) String sortBy,
                               @RequestParam(value = "sord", required = false) String order) {

        log.info("Listing singers for grid with page: {}, rows: {}", page, rows);
        log.info("Listing singers for grid with sort: {}, order: {}", sortBy, order);

        // Process order by
        Sort sort = null;
        String orderBy = sortBy;
        if (orderBy != null && orderBy.equals("birthDateString"))
            orderBy = "birthDate";

        if (orderBy != null && order != null) {
            if (order.equals("desc")) {
                sort = new Sort(Sort.Direction.DESC, orderBy);
            } else
                sort = new Sort(Sort.Direction.ASC, orderBy);
        }

        // Constructs page request for current page
        // Note: page number for Spring Data JPA starts with 0, while jqGrid starts with 1
        PageRequest pageRequest = null;

        if (sort != null) {
            pageRequest =  PageRequest.of(page - 1, rows, sort);
        } else {
            pageRequest = PageRequest.of(page - 1, rows);
        }

        Page<Singer> singerPage = singerService.findAllByPage(pageRequest);

        // Construct the grid data that will return as JSON data
        SingerGrid singerGrid = new SingerGrid();

        singerGrid.setCurrentPage(singerPage.getNumber() + 1);
        singerGrid.setTotalPages(singerPage.getTotalPages());
        singerGrid.setTotalRecords(singerPage.getTotalElements());

        singerGrid.setSingerData(Lists.newArrayList(singerPage.iterator()));

        return singerGrid;
    }
}
