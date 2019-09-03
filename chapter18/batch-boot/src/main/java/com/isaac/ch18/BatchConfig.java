package com.isaac.ch18;

import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
@AllArgsConstructor
public class BatchConfig {
    private JobBuilderFactory jobs;
    private StepBuilderFactory steps;
    private DataSource dataSource;
    private SingerItemProcessor itemProcessor;

    @Bean
    public Job job(JobExecutionStatsListener jobExecutionStatsListener) {
        return jobs.get("singerJob").listener(jobExecutionStatsListener).flow(step1()).end().build();
    }

    @Bean
    public Step step1(){
        return steps.get("step1").<Singer,Singer>chunk(10).reader(itemReader()).processor(itemProcessor).writer(itemWriter()).build();
    }

    @Bean
    public ItemReader itemReader() {
        FlatFileItemReader<Singer> itemReader = new FlatFileItemReader<>();
        itemReader.setResource(new ClassPathResource("support/test-data.csv"));
        itemReader.setLineMapper(new DefaultLineMapper<Singer>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[] { "firstName", "lastName", "song" });
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<Singer>() {{
                setTargetType(Singer.class);
            }});
        }});
        return itemReader;
    }

    @Bean
    public ItemWriter itemWriter() {
        JdbcBatchItemWriter<Singer> itemWriter = new JdbcBatchItemWriter<>();
        itemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        itemWriter.setSql("INSERT INTO SINGER (first_name, last_name, song) VALUES (:firstName, :lastName, :song)");
        itemWriter.setDataSource(dataSource);
        return itemWriter;
    }
}
