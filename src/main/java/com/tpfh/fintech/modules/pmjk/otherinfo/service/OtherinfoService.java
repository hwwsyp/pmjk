/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.service.IService
 */
package com.tpfh.fintech.modules.pmjk.otherinfo.service;

import com.baomidou.mybatisplus.service.IService;
import com.tpfh.fintech.common.utils.PageUtils;
import com.tpfh.fintech.modules.pmjk.otherinfo.entity.OtherinfoEntity;
import java.util.HashMap;
import java.util.List;

public interface OtherinfoService
extends IService<OtherinfoEntity> {
    public PageUtils queryPage(HashMap<String, Object> var1);

    public OtherinfoEntity getInfoById(Long var1);

    public List<OtherinfoEntity> getInfoList(HashMap<String, Object> var1);

    public OtherinfoEntity getInfoByProductidAndVersionnum(Long var1, Long var2);
}

