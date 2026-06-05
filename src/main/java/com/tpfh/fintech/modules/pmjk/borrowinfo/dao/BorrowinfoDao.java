/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.mapper.BaseMapper
 *  com.baomidou.mybatisplus.plugins.Page
 *  org.apache.ibatis.annotations.Mapper
 */
package com.tpfh.fintech.modules.pmjk.borrowinfo.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.tpfh.fintech.modules.pmjk.borrowinfo.entity.BorrowinfoEntity;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BorrowinfoDao
extends BaseMapper<BorrowinfoEntity> {
    public List<BorrowinfoEntity> getBorrowinfoListForPage(Page<BorrowinfoEntity> var1, HashMap<String, Object> var2);

    public List<BorrowinfoEntity> getBorrowinfoList(HashMap<String, Object> var1);

    public int addBorrowinfo(Map<String, String> var1);
}

