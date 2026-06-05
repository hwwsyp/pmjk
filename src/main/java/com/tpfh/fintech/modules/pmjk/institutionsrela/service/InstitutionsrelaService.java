/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.service.IService
 */
package com.tpfh.fintech.modules.pmjk.institutionsrela.service;

import com.baomidou.mybatisplus.service.IService;
import com.tpfh.fintech.common.utils.PageUtils;
import com.tpfh.fintech.modules.pmjk.institutionsrela.entity.InstitutionsrelaEntity;
import java.util.HashMap;
import java.util.List;

public interface InstitutionsrelaService
extends IService<InstitutionsrelaEntity> {
    public PageUtils queryPage(HashMap<String, Object> var1);

    public InstitutionsrelaEntity getInfoById(Long var1);

    public List<InstitutionsrelaEntity> getInfoList(HashMap<String, Object> var1);

    public InstitutionsrelaEntity getInfoByCreditenhanceidAndType(Long var1, Long var2);
}

