package com.primarySchoolPeriod.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {
    @Bean
    public CorsFilter corsFilter(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        //允许的域
        corsConfiguration.addAllowedOrigin("*");
        //允许跨域的方法
        corsConfiguration.addAllowedMethod("*");
        //允许跨域的请求头
        corsConfiguration.addAllowedHeader("*");
        //允许跨域的路径
        source.registerCorsConfiguration("/**",corsConfiguration);
        return new  CorsFilter(source);
    }
}
