package com.isaac.ch9.config;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

import static org.hibernate.cfg.AvailableSettings.*;
import static org.hibernate.cfg.AvailableSettings.STATEMENT_FETCH_SIZE;

/**
 * JTA实现全局事务，Spring配置
 */
@Configuration
@EnableJpaRepositories
public class XAJpaConfig {
    private static Logger logger = LoggerFactory.getLogger(XAJpaConfig.class);

    @Bean(initMethod = "init", destroyMethod = "close")
    public DataSource dataSourceA() {
        try {
            AtomikosDataSourceBean dataSource = new AtomikosDataSourceBean();
            dataSource.setUniqueResourceName("XADBMSA");
            //MySQL的资源管理器
            dataSource.setXaDataSourceClassName("com.mysql.cj.jdbc.MysqlXADataSource");
            dataSource.setXaProperties(xaProperties("MUSICDB_A", "isaac_A", "isaac_A"));
            dataSource.setPoolSize(1);
            return dataSource;
        } catch (Exception e) {
            logger.error("populator datasource bean can't be crated! ", e);
            return null;
        }
    }

    private Properties xaProperties(String schemaName, String username, String password) {
        Properties xaProp = new Properties();
        xaProp.put("databaseName", schemaName);
        xaProp.put("user", username);
        xaProp.put("password", password);
        return xaProp;
    }

    @Bean(initMethod = "init", destroyMethod = "close")
    public DataSource dataSourceB() {
        try {
            AtomikosDataSourceBean dataSource = new AtomikosDataSourceBean();
            dataSource.setUniqueResourceName("XADBMSB");
            dataSource.setXaDataSourceClassName("com.mysql.cj.jdbc.MysqlXADataSource");
            dataSource.setXaProperties(xaProperties("MUSICDB_B", "isaac_B", "isaac_B"));
            dataSource.setPoolSize(1);
            return dataSource;
        } catch (Exception e) {
            logger.error("Populator DataSource bean cannot be created!", e);
            return null;
        }
    }

    @Bean
    public EntityManagerFactory emfA() {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setPackagesToScan("com.isaac.ch9.entities");
        factoryBean.setDataSource(dataSourceA());
        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factoryBean.setJpaProperties(hibernateProperties());
        factoryBean.setPersistenceUnitName("emfA");
        factoryBean.afterPropertiesSet();
        return factoryBean.getObject();
    }


    @Bean
    public EntityManagerFactory emfB() {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setPackagesToScan("com.isaac.ch9.entities");
        factoryBean.setDataSource(dataSourceB());
        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factoryBean.setJpaProperties(hibernateProperties());
        factoryBean.setPersistenceUnitName("emfB");
        factoryBean.afterPropertiesSet();
        return factoryBean.getObject();
    }

    private Properties hibernateProperties() {
        Properties hibernateProp = new Properties();
        /*这两个属性非常重要，Hibernate使用它们来查找底层的UserTransaction和TransactionManager bean，从而参与正在管理全局事务的持久化上下文*/
        hibernateProp.put("hibernate.transaction.factory_class", "org.hibernate.transaction.JTATransactionFactory");
        hibernateProp.put(JTA_PLATFORM, "com.atomikos.icatch.jta.hibernate4.AtomikosPlatform");
        // required by Hibernate 5
        hibernateProp.put(TRANSACTION_COORDINATOR_STRATEGY, "jta");
        hibernateProp.put(CURRENT_SESSION_CONTEXT_CLASS, "jta");

        hibernateProp.put(AUTOCOMMIT, false);
        hibernateProp.put(FLUSH_BEFORE_COMPLETION, false);
        hibernateProp.put(DIALECT, "org.hibernate.dialect.MySQL5Dialect");
        // this will work only if users/schemas are created first, use ddl.sql script for this
        hibernateProp.put(HBM2DDL_AUTO, "create-drop");
        hibernateProp.put(SHOW_SQL, true);
        hibernateProp.put(MAX_FETCH_DEPTH, 3);
        hibernateProp.put(STATEMENT_BATCH_SIZE, 10);
        hibernateProp.put(STATEMENT_FETCH_SIZE, 50);
        return hibernateProp;
    }
}
