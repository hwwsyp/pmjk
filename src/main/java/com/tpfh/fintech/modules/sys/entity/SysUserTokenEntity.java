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
import java.io.Serializable;
import java.util.Date;

@TableName(value="sys_user_token")
public class SysUserTokenEntity
implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(type=IdType.INPUT)
    private Long userId;
    private String token;
    private Date expireTime;
    private Date updateTime;

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public Date getExpireTime() {
        return this.expireTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getUpdateTime() {
        return this.updateTime;
    }
}

