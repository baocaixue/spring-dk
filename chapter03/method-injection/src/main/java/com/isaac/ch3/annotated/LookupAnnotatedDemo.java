package com.isaac.ch3.annotated;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StopWatch;

public class LookupAnnotatedDemo {
    @Configuration
    @ComponentScan(basePackages = {"com.isaac.ch3.annotated"})
    static class LookupConfig {

    }

    public static void main(String... args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(LookupConfig.class);

        DemoBean abstractBean = ctx.getBean("abstractLookupBean", DemoBean.class);
        DemoBean standardBean = ctx.getBean("standardLookupBean", DemoBean.class);

        testFast("abstractLookupBean", abstractBean);
        testFast("standardLookupBean", standardBean);
    }

    public static void testFast(String beanName, DemoBean bean) {
        Singer singer1 = bean.getMySinger();
        Singer singer2 = bean.getMySinger();

        System.out.println("[" + beanName + "]: Singer Instances the Same?  "
                + (singer1 == singer2));

        StopWatch stopWatch = new StopWatch();
        stopWatch.start("lookupDemo");

        for (int x = 0; x < 100000; x++) {
            Singer singer = bean.getMySinger();
            singer.sing();
        }

        stopWatch.stop();
        System.out.println("100000 gets took " + stopWatch.getTotalTimeMillis() + " ms");
    }
}
