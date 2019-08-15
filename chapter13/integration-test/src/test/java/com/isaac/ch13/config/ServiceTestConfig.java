package com.isaac.ch13.config;

import com.isaac.ch13.init.DBInitializer;
import lombok.extern.slf4j.Slf4j;
import org.dbunit.DataSourceDatabaseTester;
import org.dbunit.util.fileloader.XlsDataFileLoader;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

/**
 * 测试环境配置类
 */
@Profile("test")
@Configuration
@ComponentScan(basePackages = {"com.isaac.ch13"},
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = DBInitializer.class)}
)
@Slf4j
public class ServiceTestConfig {
    @Bean
    public DataSource dataSource() {
        try {
            EmbeddedDatabaseBuilder dbBuilder = new EmbeddedDatabaseBuilder();
            return dbBuilder.setType(EmbeddedDatabaseType.H2).build();
        } catch (Exception e) {
            log.error("Embedded DataSource bean cannot be created!", e);
            return null;
        }
    }

    @Bean
    public DataSourceDatabaseTester databaseTester() {
        return new DataSourceDatabaseTester(dataSource());
    }

    @Bean
    public XlsDataFileLoader xlsDataFileLoader() {
        return new XlsDataFileLoader();
    }
}
