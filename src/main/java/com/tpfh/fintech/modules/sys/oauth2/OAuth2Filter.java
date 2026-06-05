/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.servlet.ServletRequest
 *  javax.servlet.ServletResponse
 *  javax.servlet.http.HttpServletRequest
 *  javax.servlet.http.HttpServletResponse
 *  org.apache.commons.lang.StringUtils
 *  org.apache.shiro.authc.AuthenticationException
 *  org.apache.shiro.authc.AuthenticationToken
 *  org.apache.shiro.web.filter.authc.AuthenticatingFilter
 *  org.springframework.http.HttpStatus
 *  org.springframework.web.bind.annotation.RequestMethod
 */
package com.tpfh.fintech.modules.sys.oauth2;

import com.tpfh.fintech.common.utils.HttpContextUtils;
import com.tpfh.fintech.common.utils.JsonUtil;
import com.tpfh.fintech.common.utils.R;
import com.tpfh.fintech.modules.sys.oauth2.OAuth2Token;
import java.io.IOException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

public class OAuth2Filter
extends AuthenticatingFilter {
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception {
        String token = this.getRequestToken((HttpServletRequest)request);
        if (StringUtils.isBlank((String)token)) {
            return null;
        }
        return new OAuth2Token(token);
    }

    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        return ((HttpServletRequest)request).getMethod().equals(RequestMethod.OPTIONS.name());
    }

    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        String token = this.getRequestToken((HttpServletRequest)request);
        if (StringUtils.isBlank((String)token)) {
            HttpServletResponse httpResponse = (HttpServletResponse)response;
            httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
            httpResponse.setHeader("Access-Control-Allow-Origin", HttpContextUtils.getOrigin());
            String json = JsonUtil.getJsonByObj(R.error(HttpStatus.UNAUTHORIZED.value(), "invalid token"));
            httpResponse.getWriter().print(json);
            return false;
        }
        return this.executeLogin(request, response);
    }

    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        HttpServletResponse httpResponse = (HttpServletResponse)response;
        httpResponse.setContentType("application/json;charset=utf-8");
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpResponse.setHeader("Access-Control-Allow-Origin", HttpContextUtils.getOrigin());
        try {
            Object throwable = e.getCause() == null ? e : e.getCause();
            R r = R.error(HttpStatus.UNAUTHORIZED.value(), ((Throwable)throwable).getMessage());
            String json = JsonUtil.getJsonByObj(r);
            httpResponse.getWriter().print(json);
        }
        catch (IOException iOException) {
            // empty catch block
        }
        return false;
    }

    private String getRequestToken(HttpServletRequest httpRequest) {
        String token = httpRequest.getHeader("token");
        if (StringUtils.isBlank((String)token)) {
            token = httpRequest.getParameter("token");
        }
        return token;
    }
}

