/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  org.apache.commons.lang.StringUtils
 *  org.apache.shiro.util.CollectionUtils
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.stereotype.Service
 *  org.springframework.transaction.annotation.Transactional
 */
package com.tpfh.fintech.modules.sys.service.impl;

import com.google.gson.Gson;
import com.tpfh.fintech.common.exception.TpfhException;
import com.tpfh.fintech.modules.sys.dao.SysConfigDao;
import com.tpfh.fintech.modules.sys.entity.SysConfigEntity;
import com.tpfh.fintech.modules.sys.redis.SysConfigRedis;
import com.tpfh.fintech.modules.sys.service.SysConfigService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value="sysConfigService")
public class SysConfigServiceImpl
implements SysConfigService {
    @Autowired
    private SysConfigDao sysConfigDao;
    @Autowired
    private SysConfigRedis sysConfigRedis;

    @Override
    @Transactional
    public void save(SysConfigEntity config) {
        this.sysConfigDao.save(config);
        this.sysConfigRedis.saveOrUpdate(config);
    }

    @Override
    @Transactional
    public void update(SysConfigEntity config) {
        this.sysConfigDao.update(config);
        this.sysConfigRedis.saveOrUpdate(config);
    }

    @Override
    @Transactional
    public void updateValueByKey(String key, String value) {
        this.sysConfigDao.updateValueByKey(key, value);
        this.sysConfigRedis.delete(key);
    }

    @Override
    @Transactional
    public void deleteBatch(Long[] ids) {
        Long[] longArray = ids;
        int n = ids.length;
        int n2 = 0;
        while (n2 < n) {
            Long id = longArray[n2];
            SysConfigEntity config = this.queryObject(id);
            this.sysConfigRedis.delete(config.getKey());
            ++n2;
        }
        this.sysConfigDao.deleteBatch(ids);
    }

    @Override
    public List<SysConfigEntity> queryList(Map<String, Object> map) {
        List<SysConfigEntity> list = this.sysConfigDao.queryList(map);
        ArrayList<Long> tids = new ArrayList<Long>();
        if (map.get("tid") != null) {
            tids.add(Long.valueOf(String.valueOf(map.get("tid"))));
        }
        this.removeListValue(list, tids, new ArrayList<Long>());
        return list;
    }

    @Override
    public List<SysConfigEntity> queryListByParent(Map<String, Object> map) {
        List<SysConfigEntity> list = this.sysConfigDao.queryList(map);
        Long parentId = 2L;
        ArrayList<SysConfigEntity> oneList = new ArrayList<SysConfigEntity>();
        for (SysConfigEntity sysConfigEntity : list) {
            if (!sysConfigEntity.getParentId().equals(parentId)) continue;
            oneList.add(sysConfigEntity);
        }
        ArrayList<SysConfigEntity> result = new ArrayList<SysConfigEntity>();
        for (SysConfigEntity sysConfigEntity : oneList) {
            this.findSonTree(sysConfigEntity.getId(), list, result);
        }
        result.addAll(oneList);
        return result;
    }

    private void findSonTree(Long id, List<SysConfigEntity> list, List<SysConfigEntity> result) {
        ArrayList<SysConfigEntity> childList = new ArrayList<SysConfigEntity>();
        for (SysConfigEntity sysConfigEntity : list) {
            if (!sysConfigEntity.getParentId().equals(id)) continue;
            childList.add(sysConfigEntity);
            result.add(sysConfigEntity);
        }
        for (SysConfigEntity sysConfigEntity : childList) {
            for (SysConfigEntity configEntity : list) {
                if (!sysConfigEntity.getId().equals(configEntity.getParentId())) continue;
                this.findSonTree(configEntity.getId(), list, result);
            }
        }
    }

    private void removeListValue(List<SysConfigEntity> list, List<Long> tids, List<Long> alls) {
        ArrayList<Long> longs = new ArrayList<Long>();
        for (SysConfigEntity sysConfigEntity : list) {
            for (Long l : tids) {
                if (!sysConfigEntity.getParentId().equals(l)) continue;
                longs.add(sysConfigEntity.getId());
                alls.add(sysConfigEntity.getId());
            }
        }
        if (!CollectionUtils.isEmpty(longs)) {
            this.removeListValue(list, longs, alls);
        }
        Iterator<SysConfigEntity> it = list.iterator();
        while (it.hasNext()) {
            SysConfigEntity x = it.next();
            for (Long l : alls) {
                if (!x.getId().equals(l)) continue;
                it.remove();
            }
        }
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return this.sysConfigDao.queryTotal(map);
    }

    @Override
    public SysConfigEntity queryObject(Long id) {
        return this.sysConfigDao.queryObject(id);
    }

    @Override
    public String getValue(String key) {
        SysConfigEntity config = this.sysConfigRedis.get(key);
        if (config == null) {
            config = this.sysConfigDao.queryByKey(key);
            this.sysConfigRedis.saveOrUpdate(config);
        }
        return config == null ? null : config.getValue();
    }

    @Override
    public <T> T getConfigObject(String key, Class<T> clazz) {
        String value = this.getValue(key);
        if (StringUtils.isNotBlank((String)value)) {
            return (T)new Gson().fromJson(value, clazz);
        }
        try {
            return clazz.newInstance();
        }
        catch (Exception e) {
            throw new TpfhException("\u83b7\u53d6\u53c2\u6570\u5931\u8d25");
        }
    }

    @Override
    public List<SysConfigEntity> queryByKey(String key) {
        return this.sysConfigDao.queryListByKey(key);
    }

    @Override
    public List<SysConfigEntity> queryParent(String id, String key) {
        return this.sysConfigDao.queryParentList(id, key);
    }

    @Override
    public boolean validateDictionary(String parentValue, String value) {
        int count = this.sysConfigDao.validateDictionary(parentValue, value);
        return count > 0;
    }

    @Override
    public SysConfigEntity queryValueByKey(String key) {
        return this.sysConfigDao.queryByKey(key);
    }
}

