package com.isaac.ch8;

import com.isaac.ch8.config.EnversConfig;
import com.isaac.ch8.entities.SingerAudit;
import com.isaac.ch8.service.SingerAuditService;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.text.ParseException;
import java.util.List;

public class SpringEnversJPADemo {
    public static void main(String[] args) throws ParseException {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(EnversConfig.class);
        SingerAuditService service = ctx.getBean(SingerAuditService.class);
        System.out.println("----- Add new Singer -----");
        SingerAudit singer = new SingerAudit();
        singer.setLastName("Bao");
        singer.setFirstName("Isaac");
        singer.setBirthDate(DateUtils.parseDate("1995-01-03", "YYYY-MM-dd"));
        service.save(singer);

        List<SingerAudit> singers = service.findAll();
        singers.stream().map(SingerAudit::toString).forEach(System.out::println);

        System.out.println("---- Update Singer ----");
        singer.setFirstName("Test");
        service.save(singer);
        singers.stream().map(SingerAudit::toString).forEach(System.out::println);

        Long id = singer.getId();

        System.out.println();
        System.out.println("old version (created version)" + service.findAuditByRevision(id, 1));
        System.out.println();
        System.out.println("second version (update version)" + service.findAuditByRevision(id, 2));
    }
}
