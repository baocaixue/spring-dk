package com.isaac.ch12.config;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.castor.CastorMarshaller;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class RestClientConfig {
    @Autowired
    private ApplicationContext ctx;

    private Credentials credentials() {
        return new UsernamePasswordCredentials("isaac", "isaac");
    }

    private CredentialsProvider provider() {
        BasicCredentialsProvider provider = new BasicCredentialsProvider();
        provider.setCredentials(AuthScope.ANY, credentials());
        return provider;
    }

    /**
     * HttpComponentsClientHttpRequestFactory是Spring对Apache HttpComponents HttpClient库提供的支持，
     * 需要使用该库构造一个用来存储客户端证书的CloseableHttpClient实例
     * @return
     */
    @Bean
    public HttpComponentsClientHttpRequestFactory httpRequestFactory() {
        CloseableHttpClient client = HttpClients.custom().setDefaultCredentialsProvider(provider()).build();
        return new HttpComponentsClientHttpRequestFactory(client);
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(httpRequestFactory());
        List<HttpMessageConverter<?>> mcvs = new ArrayList<>();
        mcvs.add(singerMessageConverter());
        restTemplate.setMessageConverters(mcvs);
        return restTemplate;
    }

    @Bean
    public MarshallingHttpMessageConverter singerMessageConverter() {
        MarshallingHttpMessageConverter mc = new MarshallingHttpMessageConverter();
        mc.setMarshaller(castorMarshaller());
        mc.setUnmarshaller(castorMarshaller());
        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(new MediaType("application", "xml"));
        mc.setSupportedMediaTypes(mediaTypes);
        return mc;
    }

    @Bean
    CastorMarshaller castorMarshaller() {
        CastorMarshaller castorMarshaller = new CastorMarshaller();
        castorMarshaller.setMappingLocation(ctx.getResource("classpath:spring/oxm-mapping.xml"));
        return castorMarshaller;
    }
}
