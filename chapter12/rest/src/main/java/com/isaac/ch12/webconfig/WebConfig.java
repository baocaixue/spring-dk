package com.isaac.ch12.webconfig;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.castor.CastorMarshaller;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 带有HTTP消息转换器的Spring MVC配置类
 *
 * 使用XML和JSON的HTTP消息转换器bean来丰富配置
 */
@Configuration
@EnableWebMvc//启用了对Spring MVC的注解（即@Controller）的支持，注册了Spring的类型转换和格式化系统，另外，还启用了JSR-303验证支持
@ComponentScan(basePackages = {"com.isaac.ch12"})
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private ApplicationContext ctx;

    /**
     * 声明用作支持格式的媒体转换的HttpMessageConverter实例。声明支持JSON和XML的两个转换器
     * @param converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(mappingJackson2HttpMessageConverter());
        converters.add(singerMessageConverter());
    }

    /**
     * 此时会注册一个默认的Handler：DefaultServletHttpRequestHandler，这个Handler也是用来处理静态文件的，它会尝试映射/。
     * 当DispatcherServelt映射/时（/ 和/ 是有区别的），并且没有找到合适的Handler来处理请求时，就会交给DefaultServletHttpRequestHandler
     * 来处理。注意：这里的静态资源是放置在web根目录下，而非WEB-INF 下
     * @param configurer
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean
    public MarshallingHttpMessageConverter singerMessageConverter() {
        MarshallingHttpMessageConverter mc = new MarshallingHttpMessageConverter();
        mc.setMarshaller(castorMarshaller());
        mc.setUnmarshaller(castorMarshaller());
        List<MediaType> mediaTypes = new ArrayList<>();
        MediaType mt = new MediaType("application", "xml");
        mediaTypes.add(mt);
        mc.setSupportedMediaTypes(mediaTypes);
        return mc;
    }

    @Bean
    public CastorMarshaller castorMarshaller() {
        CastorMarshaller castorMarshaller = new CastorMarshaller();
        castorMarshaller.setMappingLocation(ctx.getResource("classpath:spring/oxm-mapping.xml"));
        return castorMarshaller;
    }

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setObjectMapper(objectMapper());
        return mappingJackson2HttpMessageConverter;
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objMapper = new ObjectMapper();
        objMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        objMapper.setDateFormat(df);
        return objMapper;
    }
}
