/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.mapper.MetaObjectHandler
 *  org.apache.ibatis.reflection.MetaObject
 */
package com.tpfh.fintech.config;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import com.tpfh.fintech.common.utils.ShiroUtils;
import java.util.Date;
import org.apache.ibatis.reflection.MetaObject;

public class MyMetaObjectHandler
extends MetaObjectHandler {
    public void insertFill(MetaObject metaObject) {
        Date newDate = new Date();
        Object createTime = this.getFieldValByName("createTime", metaObject);
        Object createUser = this.getFieldValByName("createUser", metaObject);
        if (createTime == null) {
            this.setFieldValByName("createTime", newDate, metaObject);
        }
        if (createUser == null) {
            this.setFieldValByName("createUser", ShiroUtils.getUserId(), metaObject);
        }
    }

    public void updateFill(MetaObject metaObject) {
        Date newDate = new Date();
        this.setFieldValByName("updateTime", newDate, metaObject);
        this.setFieldValByName("updateUser", ShiroUtils.getUserId(), metaObject);
    }
}

