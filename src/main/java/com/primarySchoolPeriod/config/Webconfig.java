package com.primarySchoolPeriod.config;

import org.springframework.boot.validation.MessageInterpolatorFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.nio.charset.StandardCharsets;
import java.util.List;


@Configuration
public class Webconfig  extends WebMvcConfigurationSupport {

        @Override
        protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
            // 解决controller返回字符串中文乱码问题
            for (HttpMessageConverter<?> converter : converters) {
                if (converter instanceof StringHttpMessageConverter) {
                    ((StringHttpMessageConverter) converter).setDefaultCharset(StandardCharsets.UTF_8);
                } else if (converter instanceof MappingJackson2HttpMessageConverter) {
                    ((MappingJackson2HttpMessageConverter) converter).setDefaultCharset(StandardCharsets.UTF_8);
                }
            }
        }
}
