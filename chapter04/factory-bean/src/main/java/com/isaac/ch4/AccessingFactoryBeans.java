package com.isaac.ch4;

import org.springframework.context.support.GenericXmlApplicationContext;

import java.security.MessageDigest;

public class AccessingFactoryBeans {

	public static void main(String... args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:spring/app-context-xml.xml");
		ctx.refresh();
		ctx.getBean("shaDigest", MessageDigest.class);

		Object facBean = ctx.getBean("shaDigest");
		assert facBean instanceof MessageDigest;
		Object accessBean = ctx.getBean("&shaDigest");
		assert accessBean instanceof MessageDigestFactoryBean;
		ctx.close();
	}
}
