/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.service.IService
 */
package com.tpfh.fintech.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.tpfh.fintech.modules.sys.entity.SysCaptchaEntity;
import java.awt.image.BufferedImage;

public interface SysCaptchaService
extends IService<SysCaptchaEntity> {
    public BufferedImage getCaptcha(String var1);

    public boolean validate(String var1, String var2);
}

