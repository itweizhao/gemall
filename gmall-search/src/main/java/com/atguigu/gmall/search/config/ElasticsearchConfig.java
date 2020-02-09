package com.atguigu.gmall.search.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;

/**
 * @author shkstart
 * @create 2020-01-29 17:59
 */
public class ElasticsearchConfig {


    @Bean
    public RestHighLevelClient restHighLevelClient(){
        return new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("192.168.5.132",9200,"http")));
    }
}
