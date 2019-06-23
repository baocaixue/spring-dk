package com.isaac.ch4;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ResourceDemo {
    public static void main(String[] args) throws Exception {
        ApplicationContext ctx = new ClassPathXmlApplicationContext();

        File file = File.createTempFile("test", "txt");
        file.deleteOnExit();

        Resource res1 = ctx.getResource("file://" + file.getPath());
        readFileContent(res1.getFile());
        //displayInfo(res1);

        Resource res2 = ctx.getResource("classpath:test.txt");
        readFileContent(res2.getFile());
        displayInfo(res2);

        Resource res3 = ctx.getResource("http://www.baidu.com");
        displayInfo(res3);
    }

    private static void displayInfo(Resource resource) throws Exception{
        System.out.println(resource.getClass());
        System.out.println(resource.getURL().getContent());
        System.out.println();
    }

    private static void readFileContent(File file) throws Exception {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        FileChannel channel = new FileInputStream(file).getChannel();
        buffer.clear();
        int length = channel.read(buffer);
        if (length > 0)
            System.out.println(new String(buffer.array(), 0, length));
        channel.close();
    }
}
