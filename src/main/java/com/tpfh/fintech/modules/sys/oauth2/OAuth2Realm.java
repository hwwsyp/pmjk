/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.shiro.authc.AuthenticationException
 *  org.apache.shiro.authc.AuthenticationInfo
 *  org.apache.shiro.authc.AuthenticationToken
 *  org.apache.shiro.authc.IncorrectCredentialsException
 *  org.apache.shiro.authc.LockedAccountException
 *  org.apache.shiro.authc.SimpleAuthenticationInfo
 *  org.apache.shiro.authz.AuthorizationInfo
 *  org.apache.shiro.authz.SimpleAuthorizationInfo
 *  org.apache.shiro.realm.AuthorizingRealm
 *  org.apache.shiro.subject.PrincipalCollection
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.stereotype.Component
 */
package com.tpfh.fintech.modules.sys.oauth2;

import com.tpfh.fintech.modules.sys.entity.SysUserEntity;
import com.tpfh.fintech.modules.sys.entity.SysUserTokenEntity;
import com.tpfh.fintech.modules.sys.oauth2.OAuth2Token;
import com.tpfh.fintech.modules.sys.service.ShiroService;
import com.tpfh.fintech.modules.sys.service.SysUserRoleService;
import java.util.List;
import java.util.Set;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OAuth2Realm
extends AuthorizingRealm {
    @Autowired
    private ShiroService shiroService;
    @Autowired
    private SysUserRoleService sysUserRoleService;

    public boolean supports(AuthenticationToken token) {
        return token instanceof OAuth2Token;
    }

    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SysUserEntity user = (SysUserEntity)principals.getPrimaryPrincipal();
        Long userId = user.getUserId();
        Set<String> permsSet = this.shiroService.getUserPermissions(userId);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permsSet);
        return info;
    }

    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String accessToken = (String)token.getPrincipal();
        SysUserTokenEntity tokenEntity = this.shiroService.queryByToken(accessToken);
        if (tokenEntity == null || tokenEntity.getExpireTime().getTime() < System.currentTimeMillis()) {
            throw new IncorrectCredentialsException("token\u5931\u6548\uff0c\u8bf7\u91cd\u65b0\u767b\u5f55");
        }
        SysUserEntity user = this.shiroService.queryUser(tokenEntity.getUserId());
        if (user.getStatus() == 0) {
            throw new LockedAccountException("\u8d26\u53f7\u5df2\u88ab\u9501\u5b9a,\u8bf7\u8054\u7cfb\u7ba1\u7406\u5458");
        }
        List<Long> roleIdList = this.sysUserRoleService.queryRoleIdList(user.getUserId());
        user.setRoleIdList(roleIdList);
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo((Object)user, (Object)accessToken, this.getName());
        return info;
    }
}

