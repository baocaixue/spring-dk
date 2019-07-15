package com.isaac.ch8;

import com.isaac.ch8.config.JpaConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class SingerJPATest {

	private static Logger logger = LoggerFactory.getLogger(SingerJPATest.class);
	private GenericApplicationContext ctx;
	private SingerService singerService;

	@Before
	public void setUp() {
		ctx = new AnnotationConfigApplicationContext(JpaConfig.class);
		singerService = ctx.getBean( SingerService.class);
		assertNotNull(singerService);
	}

	@Test
	public void testFindByCriteriaQuery() {
		List<Singer> singers = singerService.findByCriteriaQuery("John", "Mayer");
		assertEquals(1, singers.size());
		listSingersWithAlbum(singers);
	}

	private static void listSingersWithAlbum(List<Singer> singers) {
		logger.info(" ---- Listing singers with instruments:");
		singers.forEach(singer -> {
			logger.info(singer.toString());
			if (singer.getAlbums() != null) {
				singer.getAlbums().forEach(a -> logger.info(" \t" + a.toString()));
			}
			if (singer.getInstruments() != null) {
				singer.getInstruments().forEach(i -> logger.info(" \tInstrument: " + i.getInstrumentId()));
			}
		});
	}

	@After
	public void tearDown() {
		ctx.close();
	}
}
