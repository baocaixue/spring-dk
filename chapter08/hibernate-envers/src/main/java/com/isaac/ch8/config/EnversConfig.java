package com.isaac.ch8.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "com.isaac.ch8")
@EnableJpaRepositories(basePackages = "com.isaac.ch8.repos")
@EnableJpaAuditing(auditorAwareRef = "auditor")
public class EnversConfig {
    @Bean
    public AuditorAwareBean auditor(){
        return new AuditorAwareBean();
    }

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).addScript("classpath:db/schema.sql").build();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager(entityManagerFactory());
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        return new HibernateJpaVendorAdapter();
    }

    /**
     *
     * Hibernate Envers以EJB监听器的形式实现，下面配置了这些监听器
     */
    @Bean
    public Properties hibernateProperties() {
        Properties hibernateProp = new Properties();
        hibernateProp.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        hibernateProp.put("hibernate.format_sql", true);
        hibernateProp.put("hibernate.show_sql", true);
        hibernateProp.put("hibernate.max_fetch_depth", 3);
        hibernateProp.put("hibernate.jdbc.batch_size", 10);
        hibernateProp.put("hibernate.jdbc.fetch_size", 50);
        //Properties for Hibernate Envers
        //版本化实体的表名后缀。实体记录要保存到那张表，实体表名+后缀的表中
        hibernateProp.put("org.hibernate.envers.audit_table_suffix", "_H");
        //历史记录表的列，用于存储每条历史纪录的版本号
        hibernateProp.put("org.hibernate.envers.revision_field_name", "AUDIT_REVISION");
        //历史记录表的列，用于存储更新操作的类型
        hibernateProp.put("org.hibernate.envers.revision_type_field_name", "ACTION_TYPE");
        //实体版本控制的审计策略
        hibernateProp.put("org.hibernate.envers.audit_strategy", "org.hibernate.envers.strategy.ValidityAuditStrategy");
        //历史记录表的列，用于存储每条历史记录的最终版本号。仅在使用有效性审计策略时才需要该属性
        hibernateProp.put("org.hibernate.envers.audit_strategy_validity_end_rev_field_name", "AUDIT_REVISION_END");
        //是否在更新每条历史纪录的最终版本号时存储时间戳。仅在使用有效性审计策略时才需要该属性
        hibernateProp.put("org.hibernate.envers.audit_strategy_validity_store_revend_timestamp", "True");
        //历史记录表的列，用于存储更新每条历史纪录的最终版本号时的时间戳。仅在使用有效性审计策略且audit_strategy_validity_end_rev_field_name为True时才需要该属性
        hibernateProp.put("org.hibernate.envers.audit_strategy_validity_revend_timestamp_field_name",
                "AUDIT_REVISION_END_TS");
        return hibernateProp;
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setPackagesToScan("com.isaac.ch8.entities");
        factoryBean.setDataSource(dataSource());
        factoryBean.setJpaProperties(hibernateProperties());
        factoryBean.setJpaVendorAdapter(jpaVendorAdapter());
        factoryBean.afterPropertiesSet();
        return factoryBean.getObject();
    }
}
