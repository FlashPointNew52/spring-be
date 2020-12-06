package com.maklersoft.springbe.configuration;

import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.maklersoft.springbe.repositories")
@PropertySource(value = "classpath:application.properties")
public class ElasticsearchConfig {
    @Value("${elasticsearch.host}")
    private String EsHost;
    @Value("${elasticsearch.importHost}")
    private String importHost;
    @Value("${elasticsearch.port}")
    private int EsPort;
    @Value("${elasticsearch.importPort}")
    private int importPort;

    @Value("${elasticsearch.authorizationKey}")
    private String authorization;

    @Bean
    public RestHighLevelClient client() {
        RestClientBuilder builder = RestClient.builder(new HttpHost(EsHost, EsPort, "http"));
        return new RestHighLevelClient(builder);
    }

    @Bean
    public RestHighLevelClient importClient() {
        RestClientBuilder builder = RestClient.builder(new HttpHost(importHost, importPort, "http"));
        Header[] defaultHeaders = new Header[]{new BasicHeader("Authorization", "Basic " + authorization)};
        builder.setDefaultHeaders(defaultHeaders);
        return new RestHighLevelClient(builder);
    }
    @Primary
    @Bean
    public ElasticsearchOperations elasticsearchTemplate() {
        return new ElasticsearchRestTemplate(client());
    }

    @Bean
    public ElasticsearchOperations elasticsearchImportTemplate() {
        return new ElasticsearchRestTemplate(importClient());
    }
}
