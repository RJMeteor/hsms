package com.primarySchoolPeriod.util;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.fill.Column;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AutomaticGenerator {
    public static void renjia(String[] args) throws IOException {

        ClassPathResource classPathResource = new ClassPathResource("config/db.properties");
        InputStream inputStream = classPathResource.getInputStream();
        Properties properties = new Properties();
        properties.load(inputStream);
        String driver = properties.getProperty("renjia.driver");
        String url = properties.getProperty("renjia.url");
        String username = properties.getProperty("renjia.username");
        String password = properties.getProperty("renjia.password");
        FastAutoGenerator.create(url,username,password)
                //全局配置
                .globalConfig(builder -> {
                    builder.commentDate("yyyy-MM-dd") //注释日期格式
                            .outputDir(System.getProperty("user.dir") + "/src/main/java") //指定输出目录
                            .fileOverride(); //覆盖文件
                })
                //包配置
                .packageConfig(builder -> {
                    builder.parent("com.primarySchoolPeriod") //包名的前缀
                            .entity("pojo") //实体类包名
                            .mapper("mapper") //mapper接口包名
                            .service("service") //service包名
                            .controller("controller") //controller包名
                            .xml("mapper"); //映射文件包名
                })
                //策略配置
                .strategyConfig(builder -> {
                    Column column = new Column("create_date", FieldFill.INSERT);
                    Column col = new Column("update_date",FieldFill.INSERT_UPDATE);
                    builder.entityBuilder() //开始实体类配置
                            .enableLombok() //开启lombok模型
                            .naming(NamingStrategy.underline_to_camel) //表名下划线转驼峰
                            .columnNaming(NamingStrategy.underline_to_camel) //列名下划线转驼峰
                            .addTableFills(col,column);
                }).execute();
    }
}
