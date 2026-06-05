/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.ibatis.annotations.Mapper
 *  org.apache.ibatis.annotations.Param
 */
package com.tpfh.fintech.modules.sys.dao;

import com.tpfh.fintech.modules.sys.entity.SysConfigEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysConfigDao {
    public List<SysConfigEntity> queryListByKey(String var1);

    public SysConfigEntity queryByKey(String var1);

    public int updateValueByKey(@Param(value="key") String var1, @Param(value="value") String var2);

    public List<SysConfigEntity> queryParentList(@Param(value="value") String var1, @Param(value="key") String var2);

    public int validateDictionary(@Param(value="parentValue") String var1, @Param(value="value") String var2);

    public void update(SysConfigEntity var1);

    public void save(SysConfigEntity var1);

    public void deleteBatch(Long[] var1);

    public List<SysConfigEntity> queryList(Map<String, Object> var1);

    public int queryTotal(Map<String, Object> var1);

    public SysConfigEntity queryObject(Long var1);
}

