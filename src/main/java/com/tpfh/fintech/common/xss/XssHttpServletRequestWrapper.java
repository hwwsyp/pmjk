/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.servlet.ReadListener
 *  javax.servlet.ServletInputStream
 *  javax.servlet.http.HttpServletRequest
 *  javax.servlet.http.HttpServletRequestWrapper
 *  org.apache.commons.io.IOUtils
 *  org.apache.commons.lang.StringUtils
 */
package com.tpfh.fintech.common.xss;

import com.tpfh.fintech.common.xss.HTMLFilter;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

public class XssHttpServletRequestWrapper
extends HttpServletRequestWrapper {
    HttpServletRequest orgRequest;
    private static final HTMLFilter htmlFilter = new HTMLFilter();

    public XssHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
        this.orgRequest = request;
    }

    public ServletInputStream getInputStream() throws IOException {
        if (!"application/json".equalsIgnoreCase(super.getHeader("Content-Type"))) {
            return super.getInputStream();
        }
        String json = IOUtils.toString((InputStream)super.getInputStream(), (String)"utf-8");
        if (StringUtils.isBlank((String)json)) {
            return super.getInputStream();
        }
        json = this.xssEncode(json);
        final ByteArrayInputStream bis = new ByteArrayInputStream(json.getBytes("utf-8"));
        return new ServletInputStream(){

            public boolean isFinished() {
                return true;
            }

            public boolean isReady() {
                return true;
            }

            public void setReadListener(ReadListener readListener) {
            }

            public int read() throws IOException {
                return bis.read();
            }
        };
    }

    public String getParameter(String name) {
        String value = super.getParameter(this.xssEncode(name));
        if (StringUtils.isNotBlank((String)value)) {
            value = this.xssEncode(value);
        }
        return value;
    }

    public String[] getParameterValues(String name) {
        String[] parameters = super.getParameterValues(name);
        if (parameters == null || parameters.length == 0) {
            return null;
        }
        int i = 0;
        while (i < parameters.length) {
            parameters[i] = this.xssEncode(parameters[i]);
            ++i;
        }
        return parameters;
    }

    public Map<String, String[]> getParameterMap() {
        LinkedHashMap<String, String[]> map = new LinkedHashMap<String, String[]>();
        Map parameters = super.getParameterMap();
        for (String key : parameters.keySet()) {
            String[] values = (String[])parameters.get(key);
            int i = 0;
            while (i < values.length) {
                values[i] = this.xssEncode(values[i]);
                ++i;
            }
            map.put(key, values);
        }
        return map;
    }

    public String getHeader(String name) {
        String value = super.getHeader(this.xssEncode(name));
        if (StringUtils.isNotBlank((String)value)) {
            value = this.xssEncode(value);
        }
        return value;
    }

    private String xssEncode(String input) {
        return htmlFilter.filter(input);
    }

    public HttpServletRequest getOrgRequest() {
        return this.orgRequest;
    }

    public static HttpServletRequest getOrgRequest(HttpServletRequest request) {
        if (request instanceof XssHttpServletRequestWrapper) {
            return ((XssHttpServletRequestWrapper)request).getOrgRequest();
        }
        return request;
    }
}

