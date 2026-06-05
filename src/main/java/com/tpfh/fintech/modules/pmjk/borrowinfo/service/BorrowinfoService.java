/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.service.IService
 */
package com.tpfh.fintech.modules.pmjk.borrowinfo.service;

import com.baomidou.mybatisplus.service.IService;
import com.tpfh.fintech.common.utils.PageUtils;
import com.tpfh.fintech.modules.pmjk.borrowinfo.entity.BorrowinfoEntity;
import java.util.HashMap;
import java.util.List;

public interface BorrowinfoService
extends IService<BorrowinfoEntity> {
    public PageUtils queryPage(HashMap<String, Object> var1);

    public BorrowinfoEntity getInfoById(Long var1);

    public List<BorrowinfoEntity> getInfoList(HashMap<String, Object> var1);

    public BorrowinfoEntity getInfoByCreditenhanceid(Long var1);
}

