package com.isaac.ch12.webconfig;

import com.isaac.ch12.config.DataServiceConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Spring Web应用程序遵循Front Controller设计模式，其中所有请求都由单个控制器接收，然后分派给相应的处理程序（控制器类）。
 *
 * 中央调度程序是org.springframework.web.servlet.DispatcherServlet的一个实例由AbstractAnnotationConfigDispatcherServletInitializer
 * 类进行注册，需要对该类进行扩展以替换web.xml配置
 */
public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    /**
     * 在Spring MVC中，每个DispatchServlet都将拥有自己的WebApplicationContext，但是，DataServiceConfig中定义的所有服务层的bean（称为根WebApplicationContext）
     * 也可用作每个servlet的WebApplicationContext
     * @return
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{DataServiceConfig.class};
    }

    /**
     * servlet的配置：带有HTTP消息转换器的Spring MVC配置类
     * @return
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{WebConfig.class};
    }

    /**
     * 指示Web容器将模式/下的所有URL都交由RESTful servlet进行处理
     * @return
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
