package com.isaac.ch9.config;

import com.atomikos.icatch.config.UserTransactionService;
import com.atomikos.icatch.config.UserTransactionServiceImp;
import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.isaac.ch9.service"})
public class ServiceConfig {
    private final Logger logger = LoggerFactory.getLogger(ServiceConfig.class);

    /**
     * 用于配置Atomikos事务服务以管理挂起的事务
     */
    @Bean(initMethod = "init", destroyMethod = "shutdownForce")
    public UserTransactionService userTransactionService() {
        /*这是位于https://www.atomikos.com/Documentation/SpringIntegration#The_Advanced_Case_40As_of_3.3_41的Atomikos文档中作为示例给出的XML配置的注解配置调整版本*/
        Properties properties = new Properties();
        properties.put("com.atomikos.icatch.service", "com.atomikos.icatch.standalone.UserTransactionServiceFactory");
        return new UserTransactionServiceImp(properties);
    }

    /**
     * 定义的atomikosTransactionManager和userTransaction bean，实现类由Atomikos提供，分别实现了标准的Spring org.springframework.transaction.PlatformTransactionManager和javax.
     * transaction.UserTransaction接口。这些bean提供JTA所需的事务协调和同步服务，并通过支持2PC的XA协议与资源管理器进行通讯
     */
    @Bean (initMethod = "init", destroyMethod = "close")
    @DependsOn("userTransactionService")
    public UserTransactionManager atomikosTransactionManager(){
        UserTransactionManager utm = new UserTransactionManager();
        utm.setStartupTransactionService(false);
        utm.setForceShutdown(true);
        return utm;
    }

    @Bean
    @DependsOn("userTransactionService")
    public UserTransaction userTransaction(){
        UserTransactionImp ut = new UserTransactionImp();
        try {
            ut.setTransactionTimeout(300);
        } catch (SystemException se) {
            logger.error("Configuration  exception.", se);
            return null;
        }
        return ut;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JtaTransactionManager jtm = new JtaTransactionManager();
        jtm.setTransactionManager(atomikosTransactionManager());
        jtm.setUserTransaction(userTransaction());
        return jtm;
    }

}
