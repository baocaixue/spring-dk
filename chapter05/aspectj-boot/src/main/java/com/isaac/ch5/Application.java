package com.isaac.ch5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Created by iuliana.cosmina on 4/9/17.
 */
@SpringBootApplication
public class Application {

	public static void main(String[] args) throws Exception {
		ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);
		assert (ctx != null);

		NewDocumentarist documentarist = ctx.getBean("documentarist", NewDocumentarist.class);
		documentarist.execute();

		System.in.read();
		ctx.close();
	}
}
