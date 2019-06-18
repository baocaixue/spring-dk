package com.isaac.ch4;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.io.File;

public class DestructiveBean implements InitializingBean {
    private File file;
    private String filePath;

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Initializing Bean");
        if(filePath == null)
            throw new IllegalArgumentException("You must specify the filePath property of " + DestructiveBean.class);
        this.file = new File(filePath);
        boolean created = this.file.createNewFile();
        System.out.println("File : " + file.getName() + " is exist ? " + created);
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void destroy() {
        System.out.println("Destroy Bean");
        if (!file.delete())
            System.err.println("ERROR:failed to delete this file." + file.getName());
        System.out.println("File : " + file.getName() + " is exist ? " + file.exists());
    }

    public static void main(String[] args){
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/app-context-xml.xml");
        ctx.refresh();

        DestructiveBean bean = (DestructiveBean) ctx.getBean("destructiveBean");

        System.out.println("Calling destroy()");
//        ctx.close();
        ctx.destroy();
        System.out.println("Called destroy()");
    }
}
