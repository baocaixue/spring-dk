package com.isaac.ch18.config;

import com.isaac.ch18.Singer;
import com.isaac.ch18.StepExecutionStatsListener;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ResourceLoader;

import javax.sql.DataSource;

/**
 * EnableBatchProcessing为构建批处理作业提供了基本配置：
 *  创建org.springframework.batch.core.scope.StepCore的一个实例。该作用域内的对象将使用Spring容器作为对象工厂，所以每个执行步骤中这种Bean的实例只有一个
 *  生成一组可用于自动装配的特定的批注里基础结构bean：jobRepository（JobRepository类型）、jobLauncher（JobLauncher类型）、jobBuilders（JobBuilderFactory类型）和stepBuilders（StepBuilderFactory类型）
 */
@Configuration
@Import(DataSourceConfig.class)
@ComponentScan("com.isaac.ch18")
@EnableBatchProcessing
@AllArgsConstructor
public class BatchConfig {
    private JobBuilderFactory jobs;
    private StepBuilderFactory steps;
    private DataSource dataSource;
    private ResourceLoader resourceLoader;
    //必须显示声明
    private StepExecutionStatsListener executionStatsListener;

    /**
     * 批处理作业
     */
    @Bean
    public Job job(@Qualifier("step1") Step step){
        return jobs.get("singerJob1").start(step).build();
    }

    @Bean
    public Step step1(ItemReader<Singer> reader, ItemProcessor<Singer,Singer> itemProcessor, ItemWriter<Singer> writer) {
        return steps.get("step1").listener(executionStatsListener).<Singer,Singer>chunk(10).reader(reader).processor(itemProcessor).writer(writer).build();
    }

    @Bean
    public ItemWriter<Singer> itemWriter() {
        JdbcBatchItemWriter<Singer> itemWriter = new JdbcBatchItemWriter<>();
        itemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        itemWriter.setSql("INSERT INTO singer (first_name, last_name, song) VALUES (:firstName, :lastName, :song)");
        itemWriter.setDataSource(dataSource);
        return itemWriter;
    }

    @Bean
    public ItemReader<Singer> itemReader() {
        FlatFileItemReader<Singer> itemReader = new FlatFileItemReader<>();
        itemReader.setResource(resourceLoader.getResource("classpath:support/test-data.csv"));
        DefaultLineMapper<Singer> lineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames("firstName","lastName","song");
        tokenizer.setDelimiter(",");
        lineMapper.setLineTokenizer(tokenizer);

        BeanWrapperFieldSetMapper<Singer> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Singer.class);
        lineMapper.setFieldSetMapper(fieldSetMapper);

        itemReader.setLineMapper(lineMapper);
        return itemReader;
    }
}
