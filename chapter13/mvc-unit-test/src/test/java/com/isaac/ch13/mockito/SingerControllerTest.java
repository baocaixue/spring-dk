package com.isaac.ch13.mockito;

import com.isaac.ch13.SingerController;
import com.isaac.ch13.entities.Singer;
import com.isaac.ch13.service.SingerService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.ExtendedModelMap;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.val;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@Slf4j
public class SingerControllerTest {
    private final List<Singer> singers = new ArrayList<>();

    private SingerService singerService;

    private SingerController singerController;

    @Before
    public void initSingers() {
        singers.add(Singer.builder().id(1L).firstName("Taylor").lastName("Swift").birthDate(LocalDate.of(1989, 12, 13)).build());
        singerService = mock(SingerService.class);
        singerController = new SingerController();

        //使用模拟实例来设置singerService变量（通常情况下由Spring注入）
        ReflectionTestUtils.setField(singerController, "singerService", singerService);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testListData() {
        when(singerService.findAll()).thenReturn(singers);

        List<Singer> controllerReturn = singerController.listData();
        controllerReturn.forEach(singer -> log.info(singer.toString()));

        //ui model
        ExtendedModelMap uiModel = new ExtendedModelMap();
        uiModel.addAttribute("singers", controllerReturn);

        List<Singer> modelSingers = (List<Singer>) uiModel.get("singers");

        assertEquals(1, modelSingers.size());
    }

    @Test
    public void testCreate() {
        val newSinger = Singer.builder().id(999L).firstName("TEST").lastName("TEST").build();
        when(singerService.save(newSinger)).thenAnswer(invocation -> {singers.add(newSinger);return newSinger;});
        Singer singer = singerController.create(newSinger);
        assertEquals(999L, singer.getId().longValue());
        assertEquals("TEST", singer.getFirstName());

        assertEquals(2, singers.size());
    }
}
