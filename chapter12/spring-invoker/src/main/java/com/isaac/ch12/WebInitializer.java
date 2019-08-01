package com.isaac.ch12;

import com.isaac.ch12.config.DataServiceConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * 实现WebApplicationInitializer或者扩展一个开箱即用的Spring实现（AbstractAnnotationConfigDispatcherServletInitializer），该接口以编程方式配置了ServletContext。这样就无需提供web.xml文件来配置Web应用程序。该类导入数据访问和事务配置，并基于此创建根应用程序上下文
 *
 * Web应用程序上下文是使用WebConfig类以及为HTTP调用器服务定义配置的配置类一起创建的。在使用Spring时，最佳实践是将自定义服务和基础结构bean保留在不同的类中
 */
public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{DataServiceConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{WebConfig.class, HttpInvokerConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/invoker/*"};
    }
}
