package com.isaac.ch7.config;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "com.isaac.ch7")
@EnableTransactionManagement//等效于<tx:annotation-driven>启用注解声明事务划分
public class AppConfig {
    private static Logger logger = LoggerFactory.getLogger(AppConfig.class);

    @Bean
    public DataSource dataSource(){
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).addScript("classpath:sql/schema.sql").addScript("classpath:sql/test-data.sql").build();
    }

    private Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        //查询指定数据库方言
        hibernateProperties.setProperty("hibernate.dialect","org.hibernate.dialect.H2Dialect");
        //日志或控制台中的sql格式化
        hibernateProperties.put("hibernate.format_sql", true);
        //hibernate在SQL内部生成注释以便于调试
        hibernateProperties.put("hibernate.use_sql_comments", true);
        //sql输出到日志文件或控制台
        hibernateProperties.put("hibernate.show_sql", true);
        //当一个映射对象与其他映射对象相关联时，用来声明外部连接的“深度”。该设置可以防止Hibernate通过过多嵌套关联获取太多数据，常用的值是3
        hibernateProperties.put("hibernate.max_fetch_depth", 3);
        //指示Hibernate应该将更新操作的数量组合到一个批处理中。这对于在Hibernate中执行批处理作业很有用。
        hibernateProperties.put("hibernate.jdbc.batch_size", 10);
        //指定来自底层的JDBC ResultSet的记录数，Hibernate每次只能从数据库中提取指定数量的记录。例如，查询已提交给数据库，并且ResultSet包含500条记录。如果获取大小为50，Hibernate需要获取10次才能得到所有数据
        hibernateProperties.put("hibernate.jdbc.fetch_size", 50);
        return hibernateProperties;
    }

    @Bean
    public SessionFactory sessionFactory() throws IOException{
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("com.isaac.ch7.entities");
        sessionFactory.setHibernateProperties(hibernateProperties());
        sessionFactory.afterPropertiesSet();
        return sessionFactory.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager() throws IOException {
        return new HibernateTransactionManager(sessionFactory());
    }
}
