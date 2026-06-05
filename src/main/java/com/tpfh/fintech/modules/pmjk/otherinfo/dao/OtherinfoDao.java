/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.mapper.BaseMapper
 *  com.baomidou.mybatisplus.plugins.Page
 *  org.apache.ibatis.annotations.Mapper
 */
package com.tpfh.fintech.modules.pmjk.otherinfo.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.tpfh.fintech.modules.pmjk.otherinfo.entity.OtherinfoEntity;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OtherinfoDao
extends BaseMapper<OtherinfoEntity> {
    public List<OtherinfoEntity> getOtherinfoListForPage(Page<OtherinfoEntity> var1, HashMap<String, Object> var2);

    public List<OtherinfoEntity> getOtherinfoList(HashMap<String, Object> var1);

    public int addOtherinfo(Map<String, String> var1);
}

