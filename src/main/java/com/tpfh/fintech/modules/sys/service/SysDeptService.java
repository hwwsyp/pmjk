/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.service.IService
 */
package com.tpfh.fintech.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.tpfh.fintech.common.utils.PageUtils;
import com.tpfh.fintech.modules.sys.entity.SysDeptEntity;
import java.util.HashMap;
import java.util.List;

public interface SysDeptService
extends IService<SysDeptEntity> {
    public List<String> queryDetpIdList(Integer var1);

    public List<SysDeptEntity> getSubDeptList(Integer var1);

    public List<SysDeptEntity> queryList(SysDeptEntity var1);

    public SysDeptEntity queryObject(String var1);

    public PageUtils queryPage(HashMap<String, Object> var1);

    public List<SysDeptEntity> getDeptsList();

    public List<SysDeptEntity> queryList(Long var1);
}

