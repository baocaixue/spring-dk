package com.isaac.ch6;

import com.isaac.ch6.config.NamedJdbcTemplateCfg;
import com.isaac.ch6.dao.SingerDao;
import com.isaac.ch6.entries.Album;
import com.isaac.ch6.entries.Singer;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.List;
import java.util.Optional;

public class ResultSetExtractorTest {

    @Test
    public void test() {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(NamedJdbcTemplateCfg.class);
        SingerDao singerDao = ctx.getBean("singerDao", SingerDao.class);
        List<Singer> singers = singerDao.findAllWithAlbums();
        Assert.assertEquals(3, singers.size());
        singers.forEach(singer -> {
            System.out.println(singer);
            Optional.ofNullable(singer.getAlbums()).ifPresent(albums -> albums.stream().map(Album::toString).forEach(album -> System.out.println("\t>>" + album)));
        });
        ctx.close();
    }
}
