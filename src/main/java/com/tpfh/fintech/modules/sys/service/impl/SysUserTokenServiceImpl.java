/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.service.impl.ServiceImpl
 *  org.springframework.stereotype.Service
 */
package com.tpfh.fintech.modules.sys.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.tpfh.fintech.common.utils.R;
import com.tpfh.fintech.modules.sys.dao.SysUserTokenDao;
import com.tpfh.fintech.modules.sys.entity.SysUserTokenEntity;
import com.tpfh.fintech.modules.sys.oauth2.TokenGenerator;
import com.tpfh.fintech.modules.sys.service.SysUserTokenService;
import java.util.Date;
import org.springframework.stereotype.Service;

@Service(value="sysUserTokenService")
public class SysUserTokenServiceImpl
extends ServiceImpl<SysUserTokenDao, SysUserTokenEntity>
implements SysUserTokenService {
    private static final int EXPIRE = 43200;

    @Override
    public R createToken(long userId) {
        String token = TokenGenerator.generateValue();
        Date now = new Date();
        Date expireTime = new Date(now.getTime() + 43200000L);
        SysUserTokenEntity tokenEntity = (SysUserTokenEntity)this.selectById(Long.valueOf(userId));
        if (tokenEntity == null) {
            tokenEntity = new SysUserTokenEntity();
            tokenEntity.setUserId(userId);
            tokenEntity.setToken(token);
            tokenEntity.setUpdateTime(now);
            tokenEntity.setExpireTime(expireTime);
            this.insert(tokenEntity);
        } else {
            tokenEntity.setToken(token);
            tokenEntity.setUpdateTime(now);
            tokenEntity.setExpireTime(expireTime);
            this.updateById(tokenEntity);
        }
        R r = R.ok().put("token", (Object)token).put("expire", (Object)43200);
        return r;
    }

    @Override
    public void logout(long userId) {
        String token = TokenGenerator.generateValue();
        SysUserTokenEntity tokenEntity = new SysUserTokenEntity();
        tokenEntity.setUserId(userId);
        tokenEntity.setToken(token);
        this.updateById(tokenEntity);
    }
}

