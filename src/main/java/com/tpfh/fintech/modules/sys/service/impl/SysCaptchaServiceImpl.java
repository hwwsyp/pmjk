/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.mapper.EntityWrapper
 *  com.baomidou.mybatisplus.service.impl.ServiceImpl
 *  com.google.code.kaptcha.Producer
 *  org.apache.commons.lang.StringUtils
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.stereotype.Service
 */
package com.tpfh.fintech.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.code.kaptcha.Producer;
import com.tpfh.fintech.common.exception.TpfhException;
import com.tpfh.fintech.common.utils.DateUtils;
import com.tpfh.fintech.modules.sys.dao.SysCaptchaDao;
import com.tpfh.fintech.modules.sys.entity.SysCaptchaEntity;
import com.tpfh.fintech.modules.sys.service.SysCaptchaService;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="sysCaptchaService")
public class SysCaptchaServiceImpl
extends ServiceImpl<SysCaptchaDao, SysCaptchaEntity>
implements SysCaptchaService {
    @Autowired
    private Producer producer;

    @Override
    public BufferedImage getCaptcha(String uuid) {
        if (StringUtils.isBlank((String)uuid)) {
            throw new TpfhException("uuid\u4e0d\u80fd\u4e3a\u7a7a");
        }
        String code = this.producer.createText();
        SysCaptchaEntity captchaEntity = new SysCaptchaEntity();
        captchaEntity.setUuid(uuid);
        captchaEntity.setCode(code);
        captchaEntity.setExpireTime(DateUtils.addDateMinutes(new Date(), 5));
        this.insert(captchaEntity);
        return this.producer.createImage(code);
    }

    @Override
    public boolean validate(String uuid, String code) {
        SysCaptchaEntity captchaEntity = (SysCaptchaEntity)this.selectOne(new EntityWrapper().eq("uuid", (Object)uuid));
        if (captchaEntity == null) {
            return false;
        }
        this.deleteById((Serializable)((Object)uuid));
        return captchaEntity.getCode().equalsIgnoreCase(code) && captchaEntity.getExpireTime().getTime() >= System.currentTimeMillis();
    }
}

