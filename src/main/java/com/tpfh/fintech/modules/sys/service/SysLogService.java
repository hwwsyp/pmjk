/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.service.IService
 */
package com.tpfh.fintech.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.tpfh.fintech.common.utils.PageUtils;
import com.tpfh.fintech.modules.sys.entity.SysLogEntity;
import java.util.Map;

public interface SysLogService
extends IService<SysLogEntity> {
    public PageUtils queryPage(Map<String, Object> var1);
}

