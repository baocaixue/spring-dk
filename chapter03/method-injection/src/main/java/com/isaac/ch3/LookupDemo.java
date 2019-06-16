package com.isaac.ch3;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.util.StopWatch;

public class LookupDemo {
    public static void main(String[] args){
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/app-context-xml.xml");
        ctx.refresh();

        DemoBean abstractLookupBean = ctx.getBean("abstractLookupBean", DemoBean.class);
        DemoBean standardLookupBean = ctx.getBean("standardLookupBean", DemoBean.class);

        testFast("abstractLookupBean", abstractLookupBean);
        testFast("standardLookupBean", standardLookupBean);

        ctx.close();
    }

    private static void testFast(String beanName, DemoBean bean) {
        Singer singer1 = bean.getMySinger();
        Singer singer2 = bean.getMySinger();
        System.out.println("[" + beanName + "]: Singer instance is same? " + (singer1 == singer2));

        StopWatch stopWatch = new StopWatch();
        stopWatch.start("lookupDemo");

        for (int i = 0; i < 100000; i++) {
            Singer singer = bean.getMySinger();
            singer.sing();
        }

        stopWatch.stop();
        System.out.println("100000 gets took: " + stopWatch.getTotalTimeSeconds() + "s");
    }
}
