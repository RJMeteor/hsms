package com.primarySchoolPeriod;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ServletComponentScan
@EnableTransactionManagement
@EnableCaching
@EnableSwagger2
@EnableKnife4j
@MapperScan("com.primarySchoolPeriod.mapper")
@PropertySource({"classpath:config/jwt.properties","classpath:config/filterpath.properties","classpath:config/db.properties"})
public class Start {
    public static void main(String[] args) {
        SpringApplication.run(Start.class,args);
    }
}
