package com.isaac.ch9.msg;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Messages {
    private static Logger logger = LoggerFactory.getLogger(Messages.class);

    @JmsListener(destination = "singers")
    public void onMessage(String content) {
        logger.info("----->    Received content : " + content);
    }
}
