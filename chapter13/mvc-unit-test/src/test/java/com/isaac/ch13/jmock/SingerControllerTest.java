package com.isaac.ch13.jmock;

import com.isaac.ch13.SingerController;
import com.isaac.ch13.entities.Singer;
import com.isaac.ch13.service.SingerService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.ExtendedModelMap;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@Slf4j
public class SingerControllerTest {
    private List<Singer> singers = new ArrayList<>();
    private Mockery mockery = new Mockery();

    private SingerService singerService;
    private SingerController singerController;

    @Before
    public void initSingers() {
        singers.add(Singer.builder().id(1L).firstName("Taylor").lastName("Swift").birthDate(LocalDate.of(1989, 12, 13)).build());
        singerService = mockery.mock(SingerService.class);
        singerController = new SingerController();
        ReflectionTestUtils.setField(singerController, "singerService", singerService);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testList() {
        mockery.checking(new Expectations(){
            {
                oneOf(singerService).findAll();
                will(returnValue(singers));
            }
        });

        ExtendedModelMap uiModel = new ExtendedModelMap();

        uiModel.addAttribute("singers", singerController.listData());
        List<Singer> modelSingers = (List<Singer>) uiModel.get("singers");
        assertEquals(1, modelSingers.size());
        mockery.assertIsSatisfied();
    }

    @Test
    public void testCreate() {
        val newSinger = Singer.builder().id(999L).firstName("TEST").lastName("TEST").build();
        mockery.checking(new Expectations(){{
            oneOf(singerService).save(newSinger);
            singers.add(newSinger);
            will(returnValue(newSinger));
        }});
        Singer singer = singerController.create(newSinger);
        assertEquals(Long.valueOf(999L), singer.getId());
        assertEquals("TEST", singer.getFirstName());
        assertEquals("TEST", singer.getLastName());

        assertEquals(2, singers.size());
        mockery.assertIsSatisfied();
    }
}
