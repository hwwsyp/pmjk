/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.mapper.BaseMapper
 *  com.baomidou.mybatisplus.plugins.Page
 *  org.apache.ibatis.annotations.Mapper
 */
package com.tpfh.fintech.modules.pmjk.rating.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.tpfh.fintech.modules.pmjk.rating.entity.RatingEntity;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RatingDao
extends BaseMapper<RatingEntity> {
    public List<RatingEntity> getRatingListForPage(Page<RatingEntity> var1, HashMap<String, Object> var2);

    public List<RatingEntity> getRatingList(HashMap<String, Object> var1);

    public int addRating(Map<String, String> var1);
}

