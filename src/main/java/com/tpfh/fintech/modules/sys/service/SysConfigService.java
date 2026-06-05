/*
 * Decompiled with CFR 0.152.
 */
package com.tpfh.fintech.modules.sys.service;

import com.tpfh.fintech.modules.sys.entity.SysConfigEntity;
import java.util.List;
import java.util.Map;

public interface SysConfigService {
    public void save(SysConfigEntity var1);

    public void update(SysConfigEntity var1);

    public void updateValueByKey(String var1, String var2);

    public void deleteBatch(Long[] var1);

    public List<SysConfigEntity> queryList(Map<String, Object> var1);

    public int queryTotal(Map<String, Object> var1);

    public SysConfigEntity queryObject(Long var1);

    public String getValue(String var1);

    public <T> T getConfigObject(String var1, Class<T> var2);

    public List<SysConfigEntity> queryByKey(String var1);

    public List<SysConfigEntity> queryParent(String var1, String var2);

    public List<SysConfigEntity> queryListByParent(Map<String, Object> var1);

    public boolean validateDictionary(String var1, String var2);

    public SysConfigEntity queryValueByKey(String var1);
}

