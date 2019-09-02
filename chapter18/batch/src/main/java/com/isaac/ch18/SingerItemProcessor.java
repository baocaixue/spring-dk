package com.isaac.ch18;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component("itemProcessor")
public class SingerItemProcessor implements ItemProcessor<Singer, Singer> {
    /**
     * 写入数据之前对数据进行转换，面向块的处理场景中不需要ItemProcessor，只需要ItemReader和ItemWriter
     * UpperCase
     */
    @Override
    public Singer process(Singer item) throws Exception {
        return new Singer(item.getFirstName().toUpperCase(), item.getLastName().toUpperCase(), item.getSong().toUpperCase());
    }
}
