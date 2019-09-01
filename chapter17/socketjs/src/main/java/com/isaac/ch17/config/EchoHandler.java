package com.isaac.ch17.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Slf4j
public class EchoHandler extends TextWebSocketHandler {
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        log.info("handle text message: " + message.getPayload());
        session.sendMessage(new TextMessage(message.getPayload()));
    }
}
