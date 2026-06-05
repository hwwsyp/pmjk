/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.shiro.SecurityUtils
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package com.tpfh.fintech.common.base;

import com.tpfh.fintech.modules.sys.entity.SysUserEntity;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractController {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    protected SysUserEntity getUser() {
        return (SysUserEntity)SecurityUtils.getSubject().getPrincipal();
    }

    protected Long getUserId() {
        return this.getUser().getUserId();
    }

    protected String getDeptId() {
        return this.getUser().getDeptId();
    }
}

