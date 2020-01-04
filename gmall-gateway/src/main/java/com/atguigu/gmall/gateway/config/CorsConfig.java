package com.atguigu.gmall.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

/**
 * @author shkstart
 * @data 2020/1/3-16:58
 */
@Configuration
public class CorsConfig {

    @Bean
    public CorsWebFilter corsWebFilter(){
        CorsConfiguration config=new CorsConfiguration();
        //允许跨域请求的路径
        config.addAllowedOrigin("http://localhost:1000");
        config.addAllowedOrigin("http://127.0.0.1:1000");
        //允许跨域携带请求头
        config.addAllowedHeader("*");
        //是否允许跨域携带Ccookie
        config.setAllowCredentials(true);
        //允许那些方法允许跨域请求
        config.addAllowedMethod("*");
        UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();
        configurationSource.registerCorsConfiguration("/**",config);
        return  new CorsWebFilter(configurationSource);
    }
}
