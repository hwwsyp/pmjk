/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.shiro.authc.AuthenticationToken
 */
package com.tpfh.fintech.modules.sys.oauth2;

import org.apache.shiro.authc.AuthenticationToken;

public class OAuth2Token
implements AuthenticationToken {
    private String token;

    public OAuth2Token(String token) {
        this.token = token;
    }

    public String getPrincipal() {
        return this.token;
    }

    public Object getCredentials() {
        return this.token;
    }
}

