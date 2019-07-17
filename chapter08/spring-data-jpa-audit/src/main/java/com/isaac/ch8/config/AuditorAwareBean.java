package com.isaac.ch8.config;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareBean implements AuditorAware {
    @Override
    public Optional getCurrentAuditor() {
        return Optional.of("Isaac");
    }
}
