/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.service.IService
 */
package com.tpfh.fintech.modules.pmjk.releaseinfo.service;

import com.baomidou.mybatisplus.service.IService;
import com.tpfh.fintech.common.utils.PageUtils;
import com.tpfh.fintech.modules.pmjk.releaseinfo.entity.ReleaseinfoEntity;
import java.util.HashMap;
import java.util.List;

public interface ReleaseinfoService
extends IService<ReleaseinfoEntity> {
    public PageUtils queryPage(HashMap<String, Object> var1);

    public ReleaseinfoEntity getInfoById(Long var1);

    public List<ReleaseinfoEntity> getInfoList(HashMap<String, Object> var1);

    public ReleaseinfoEntity getInfoByProductidAndVersionnum(Long var1, Long var2);
}

