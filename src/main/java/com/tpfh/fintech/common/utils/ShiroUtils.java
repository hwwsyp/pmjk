/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.shiro.SecurityUtils
 *  org.apache.shiro.session.Session
 *  org.apache.shiro.subject.Subject
 */
package com.tpfh.fintech.common.utils;

import com.tpfh.fintech.common.exception.TpfhException;
import com.tpfh.fintech.modules.sys.entity.SysUserEntity;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

public class ShiroUtils {
    public static Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }

    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    public static SysUserEntity getUserEntity() {
        return (SysUserEntity)SecurityUtils.getSubject().getPrincipal();
    }

    public static Long getUserId() {
        return ShiroUtils.getUserEntity().getUserId();
    }

    public static void setSessionAttribute(Object key, Object value) {
        ShiroUtils.getSession().setAttribute(key, value);
    }

    public static Object getSessionAttribute(Object key) {
        return ShiroUtils.getSession().getAttribute(key);
    }

    public static boolean isLogin() {
        return SecurityUtils.getSubject().getPrincipal() != null;
    }

    public static String getKaptcha(String key) {
        Object kaptcha = ShiroUtils.getSessionAttribute(key);
        if (kaptcha == null) {
            throw new TpfhException("\u9a8c\u8bc1\u7801\u5df2\u5931\u6548");
        }
        ShiroUtils.getSession().removeAttribute((Object)key);
        return kaptcha.toString();
    }

    public static String getUserNameEvenError() {
        try {
            SysUserEntity userEntity = (SysUserEntity)SecurityUtils.getSubject().getPrincipal();
            return userEntity.getUsername();
        }
        catch (Exception e) {
            return "System";
        }
    }
}

