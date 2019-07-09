package com.isaac.ch7;

import com.isaac.ch7.config.AppConfig;
import com.isaac.ch7.dao.SingerDao;
import com.isaac.ch7.entities.Album;
import com.isaac.ch7.entities.Instrument;
import com.isaac.ch7.entities.Singer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.Arrays;
import java.util.List;

public class SpringHibernateDemo {
    private static Logger logger = LoggerFactory.getLogger(SpringHibernateDemo.class);

    public static void main(String[] args){
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        SingerDao singerDao = ctx.getBean(SingerDao.class);
        Singer singer = singerDao.findById(1L);

        //System.out.println(singer);
        //LazyInitializationException
        //System.out.println(singer.getAlbums());
        logger.info(singer.toString());
        singer.getAlbums().stream().map(Album::toString).forEach(logger::info);
        singer.getInstruments().stream().map(Instrument::toString).forEach(logger::info);

        singerDao.delete(singer);

        List<Singer> allSinger = singerDao.findAllWithAlbum();
        allSinger.forEach(s ->{
            logger.info(">> Singer " + s);
            logger.info("\t albums " + s.getAlbums());
            logger.info("\t Instruments " + s.getInstruments());
        });
    }
}
