package com.isaac.ch18;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("itemProcessor")
public class SingerItemProcessor implements ItemProcessor<Singer, Singer> {
    @Override
    public Singer process(Singer item) throws Exception {
        return Optional.ofNullable(item).map(old ->
                new Singer(
                        Optional.ofNullable(old.getFirstName()).map(String::toUpperCase).orElse(""),
                        Optional.ofNullable(old.getLastName()).map(String::toUpperCase).orElse(""),
                        Optional.ofNullable(old.getSong()).map(String::toUpperCase).orElse("")))
                .orElseThrow(() -> new RuntimeException("singer must not be null!"));
    }
}
