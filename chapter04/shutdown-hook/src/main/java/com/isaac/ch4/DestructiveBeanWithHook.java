package com.isaac.ch4;

import org.springframework.context.support.GenericXmlApplicationContext;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.File;

/**
 * 使用关闭钩子：
 *
 *  在Spring中销毁回调函数的唯一缺点是它们不会自动触发；需要记住在应用程序关闭之前调用AbstractApplicationContext.destroy()。当应用程序作为
 * Servlet运行时，可以简单地在Servlet的destroy()方法中调用destroy()。但是，在独立的应用程序中，事情并不那么简单，尤其是在应用程序存在多个退出点时。
 *
 *  Java允许创建一个关闭钩子（shutdown hook），它是在应用程序关闭之前执行的一个线程。这里是调用AbstractApplicationContext(所有具体的
 * ApplicationContext实现都扩展了AbstractApplicationContext)destroy（）方法的一种理想方式。
 *
 *  利用此机制最简单方法是使用AbstractApplicationContext的registerShutdownHook()方法。该方法自动指示Spring注册底层JVM运行时的关闭钩子。
 */
public class DestructiveBeanWithHook {
    private File file;
    private String filePath;

    @PostConstruct
    public void afterPropertiesSet() throws Exception {
        System.out.println("Initializing Bean");

        if (filePath == null) {
            throw new IllegalArgumentException(
                    "You must specify the filePath property of " +
                            DestructiveBeanWithHook.class);
        }

        this.file = new File(filePath);
        this.file.createNewFile();

        System.out.println("File exists: " + file.exists());
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Destroying Bean");

        if(!file.delete()) {
            System.err.println("ERROR: failed to delete file.");
        }

        System.out.println("File exists: " + file.exists());
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public static void main(String... args) throws Exception {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/app-context-annotation.xml");
        ctx.registerShutdownHook();
        ctx.refresh();

        ctx.getBean(DestructiveBeanWithHook.class);

        ctx.close();
    }
}
