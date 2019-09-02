package com.isaac.ch18.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    /**
     * schema-h2.sql是spring-batch-core库的一部分，并且包含创建Spring Batch实用程序表所需的DML语句
     */
    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).addScripts("classpath:/org/springframework/batch/core/schema-h2.sql","classpath:support/singer.sql").build();
    }
}
