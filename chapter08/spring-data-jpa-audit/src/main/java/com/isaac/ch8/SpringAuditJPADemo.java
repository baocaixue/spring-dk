package com.isaac.ch8;

import com.isaac.ch8.config.AuditConfig;
import com.isaac.ch8.entities.SingerAudit;
import com.isaac.ch8.service.SingerAuditService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

public class SpringAuditJPADemo {
    private static final Logger logger = LoggerFactory.getLogger(SpringAuditJPADemo.class);

    public static void main(String[] args){
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AuditConfig.class);
        SingerAuditService service = ctx.getBean(SingerAuditService.class);
        listSingers(service.findAll());

        System.out.println("-------- add new --------");
        SingerAudit singerAudit = new SingerAudit();
        singerAudit.setFirstName("test");
        singerAudit.setLastName("test");
        singerAudit.setBirthDate(new Date((new GregorianCalendar(1940, 8, 16)).getTime().getTime()));
        service.save(singerAudit);

        Optional<SingerAudit> singerOpt = service.findById(4L);
        System.out.println("");
        singerOpt.map(SingerAudit::toString).ifPresent(logger::info);
        System.out.println("");

        System.out.println("-------- update   --------");
        singerOpt.map(it->{it.setFirstName("update");return it;}).ifPresent(service::save);

        listSingers(service.findAll());
        ctx.close();
    }

    private static void listSingers(List<SingerAudit> singers) {
        logger.info(" ---- Listing singers:");
        singers.forEach(s -> logger.info(s.toString()));
    }
}
