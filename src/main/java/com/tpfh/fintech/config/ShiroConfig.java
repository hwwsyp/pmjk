/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.shiro.mgt.SecurityManager
 *  org.apache.shiro.realm.Realm
 *  org.apache.shiro.session.mgt.SessionManager
 *  org.apache.shiro.spring.LifecycleBeanPostProcessor
 *  org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor
 *  org.apache.shiro.spring.web.ShiroFilterFactoryBean
 *  org.apache.shiro.web.mgt.DefaultWebSecurityManager
 *  org.apache.shiro.web.session.mgt.DefaultWebSessionManager
 *  org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator
 *  org.springframework.context.annotation.Bean
 *  org.springframework.context.annotation.Configuration
 */
package com.tpfh.fintech.config;

import com.tpfh.fintech.modules.sys.oauth2.OAuth2Filter;
import com.tpfh.fintech.modules.sys.oauth2.OAuth2Realm;
import java.util.HashMap;
import java.util.LinkedHashMap;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfig {
    @Bean(value={"sessionManager"})
    public SessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionValidationSchedulerEnabled(true);
        sessionManager.setSessionIdCookieEnabled(true);
        sessionManager.setGlobalSessionTimeout(86400000L);
        return sessionManager;
    }

    @Bean(value={"securityManager"})
    public SecurityManager securityManager(OAuth2Realm oAuth2Realm, SessionManager sessionManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm((Realm)oAuth2Realm);
        securityManager.setSessionManager(sessionManager);
        return securityManager;
    }

    @Bean(value={"shiroFilter"})
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);
        HashMap<String, OAuth2Filter> filters = new HashMap<String, OAuth2Filter>();
        filters.put("oauth2", new OAuth2Filter());
        shiroFilter.setFilters(filters);
        LinkedHashMap<String, String> filterMap = new LinkedHashMap<String, String>();
        filterMap.put("/**/*.css", "anon");
        filterMap.put("/**/*.js", "anon");
        filterMap.put("/*.html", "anon");
        filterMap.put("/**/*.html", "anon");
        filterMap.put("/webjars/**", "anon");
        filterMap.put("/druid/**", "anon");
        filterMap.put("/app/**", "anon");
        filterMap.put("/api/**", "anon");
        filterMap.put("/sys/login", "anon");
        filterMap.put("/swagger/**", "anon");
        filterMap.put("/v2/api-docs", "anon");
        filterMap.put("/swagger-ui.html", "anon");
        filterMap.put("/swagger-resources/**", "anon");
        filterMap.put("/captcha.jpg", "anon");
        filterMap.put("/plugins/**", "anon");
        filterMap.put("/sys/**", "oauth2");
        filterMap.put("/fund/**", "oauth2");
        filterMap.put("/project/**", "oauth2");
        filterMap.put("/finance/**", "oauth2");
        filterMap.put("/followUp/**", "oauth2");
        filterMap.put("/pmjk/**", "oauth2");
        shiroFilter.setFilterChainDefinitionMap(filterMap);
        return shiroFilter;
    }

    @Bean(value={"lifecycleBeanPostProcessor"})
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
        proxyCreator.setProxyTargetClass(true);
        return proxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }
}

