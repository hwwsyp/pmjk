/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder
 *  org.springframework.boot.context.properties.ConfigurationProperties
 *  org.springframework.context.annotation.Bean
 *  org.springframework.context.annotation.Configuration
 *  org.springframework.context.annotation.Primary
 */
package com.tpfh.fintech.datasources;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.tpfh.fintech.datasources.DynamicDataSource;
import java.util.HashMap;
import javax.sql.DataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DynamicDataSourceConfig {
    @Bean
    @ConfigurationProperties(value="spring.datasource.druid.bbg")
    public DataSource bbgDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(value="spring.datasource.druid.third")
    public DataSource thirdDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public DynamicDataSource dataSource(DataSource bbgDataSource, DataSource thirdDataSource) {
        HashMap<Object, Object> targetDataSources = new HashMap<Object, Object>();
        targetDataSources.put("bbg", bbgDataSource);
        targetDataSources.put("third", thirdDataSource);
        return new DynamicDataSource(bbgDataSource, targetDataSources);
    }
}

