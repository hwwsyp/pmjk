/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.annotations.TableId
 *  com.baomidou.mybatisplus.annotations.TableName
 *  com.baomidou.mybatisplus.enums.IdType
 */
package com.tpfh.fintech.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;

@TableName(value="sys_captcha")
public class SysCaptchaEntity {
    @TableId(type=IdType.INPUT)
    private String uuid;
    private String code;
    private Date expireTime;

    public String getUuid() {
        return this.uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getExpireTime() {
        return this.expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }
}

