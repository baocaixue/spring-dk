package com.isaac.ch4.annotated;

import com.isaac.ch2.decoupled.MessageProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("provider")
public class ConfigurableMessageProvider implements MessageProvider {
    private String msg;

    public ConfigurableMessageProvider(@Value("Love on the weekend") String msg) {
        this.msg = msg;
    }

    @Override
    public String getMessage() {
        return msg;
    }
}
