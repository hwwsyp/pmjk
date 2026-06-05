/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 *  org.springframework.boot.SpringApplication
 *  org.springframework.boot.autoconfigure.EnableAutoConfiguration
 *  org.springframework.boot.autoconfigure.SpringBootApplication
 *  org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
 *  org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
 *  org.springframework.boot.builder.SpringApplicationBuilder
 *  org.springframework.boot.web.servlet.support.SpringBootServletInitializer
 *  org.springframework.context.ApplicationContext
 *  org.springframework.context.ConfigurableApplicationContext
 *  org.springframework.context.annotation.ComponentScan
 *  org.springframework.context.annotation.Import
 *  org.springframework.core.env.Environment
 *  org.springframework.scheduling.annotation.EnableAsync
 */
package com.tpfh.fintech;

import com.tpfh.fintech.common.utils.DefaultProfileUtil;
import com.tpfh.fintech.datasources.DynamicDataSourceConfig;
import com.tpfh.fintech.modules.rule.util.SpringContextUtil;
import java.net.InetAddress;
import java.net.UnknownHostException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@Import(value={DynamicDataSourceConfig.class})
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class, SecurityAutoConfiguration.class})
@ComponentScan(basePackages={"com.tpfh.fintech"})
@EnableAsync
public class TpfhPmjkApplication
extends SpringBootServletInitializer {
    private static final Logger log = LoggerFactory.getLogger(TpfhPmjkApplication.class);

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(new Class[]{TpfhPmjkApplication.class});
        DefaultProfileUtil.addDefaultProfile(app);
        ConfigurableApplicationContext appc = app.run(args);
        Environment env = appc.getEnvironment();
        String ip = "localhost";
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
        }
        catch (UnknownHostException e) {
            e.printStackTrace();
        }
        log.info("\n----------------------------------------------------------\n\tApplication '{}' is running! Access URLs:\n\tLocal: \t\thttp://{}:{}\n\t----------------------------------------------------------", new Object[]{env.getProperty("spring.profiles.active"), ip, env.getProperty("server.port")});
        log.info("\u7a0b\u5e8f\u542f\u52a8\u5b8c\u6210!");
        SpringContextUtil.setApplicationContext((ApplicationContext)appc);
    }

    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(new Class[]{TpfhPmjkApplication.class});
    }
}

