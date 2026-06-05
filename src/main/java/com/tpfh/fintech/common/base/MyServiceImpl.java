/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.entity.TableFieldInfo
 *  com.baomidou.mybatisplus.entity.TableInfo
 *  com.baomidou.mybatisplus.exceptions.MybatisPlusException
 *  com.baomidou.mybatisplus.mapper.BaseMapper
 *  com.baomidou.mybatisplus.mapper.SqlHelper
 *  com.baomidou.mybatisplus.mapper.Wrapper
 *  com.baomidou.mybatisplus.service.impl.ServiceImpl
 *  com.baomidou.mybatisplus.toolkit.MapUtils
 *  com.baomidou.mybatisplus.toolkit.TableInfoHelper
 *  org.springframework.transaction.annotation.Transactional
 */
package com.tpfh.fintech.common.base;

import com.baomidou.mybatisplus.entity.TableFieldInfo;
import com.baomidou.mybatisplus.entity.TableInfo;
import com.baomidou.mybatisplus.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.SqlHelper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.MapUtils;
import com.baomidou.mybatisplus.toolkit.TableInfoHelper;
import com.tpfh.fintech.common.base.MyReflectionKit;
import com.tpfh.fintech.common.base.MyStatusEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;

public class MyServiceImpl<M extends BaseMapper<T>, T>
extends ServiceImpl<M, T> {
    @Transactional(rollbackFor={Exception.class})
    public boolean deleteById(Serializable id) {
        MyStatusEntity<T> statusEntity = this.getStatusEntityById(id);
        if (statusEntity != null && statusEntity.getIsHavingStatus().booleanValue()) {
            return this.updateById(statusEntity.getEntity());
        }
        return SqlHelper.delBool((Integer)this.baseMapper.deleteById(id));
    }

    @Transactional(rollbackFor={Exception.class})
    public boolean deleteByMap(Map<String, Object> columnMap) {
        if (MapUtils.isEmpty(columnMap)) {
            throw new MybatisPlusException("deleteByMap columnMap is empty.");
        }
        boolean isUpdate = false;
        ArrayList entityList = new ArrayList();
        List tempList = this.selectByMap(columnMap);
        for (Object entity : tempList) {
            MyStatusEntity statusEntity = this.getStatusEntity(entity);
            isUpdate = statusEntity.getIsHavingStatus();
            entityList.add(statusEntity.getEntity());
        }
        if (isUpdate) {
            return this.updateBatchById(entityList);
        }
        return SqlHelper.delBool((Integer)this.baseMapper.deleteByMap(columnMap));
    }

    @Transactional(rollbackFor={Exception.class})
    public boolean delete(Wrapper<T> wrapper) {
        boolean isUpdate = false;
        ArrayList entityList = new ArrayList();
        List tempList = this.selectList(wrapper);
        for (Object entity : tempList) {
            MyStatusEntity statusEntity = this.getStatusEntity(entity);
            isUpdate = statusEntity.getIsHavingStatus();
            entityList.add(statusEntity.getEntity());
        }
        if (isUpdate) {
            return this.updateBatchById(entityList);
        }
        return SqlHelper.delBool((Integer)this.baseMapper.delete(wrapper));
    }

    @Transactional(rollbackFor={Exception.class})
    public boolean deleteBatchIds(Collection<? extends Serializable> idList) {
        boolean isUpdate = false;
        ArrayList<T> entityList = new ArrayList<T>();
        for (Serializable serializable : idList) {
            MyStatusEntity<T> statusEntity = this.getStatusEntityById(serializable);
            if (statusEntity == null || !statusEntity.getIsHavingStatus().booleanValue()) continue;
            isUpdate = true;
            entityList.add(statusEntity.getEntity());
        }
        if (isUpdate) {
            return this.updateBatchById(entityList);
        }
        return SqlHelper.delBool((Integer)this.baseMapper.deleteBatchIds(idList));
    }

    private MyStatusEntity<T> getStatusEntityById(Serializable id) {
        Object entity = this.selectById(id);
        return this.getStatusEntity(entity);
    }

    private MyStatusEntity<T> getStatusEntity(T entity) {
        MyStatusEntity<T> statusEntity = new MyStatusEntity<T>();
        statusEntity.setIsHavingStatus(false);
        statusEntity.setEntity(entity);
        if (entity != null) {
            Class<?> cls = entity.getClass();
            TableInfo tableInfo = TableInfoHelper.getTableInfo(cls);
            List fieldList = tableInfo.getFieldList();
            for (TableFieldInfo filedInfo : fieldList) {
                if (!"status".equals(filedInfo.getProperty())) continue;
                MyReflectionKit.setFiledValue(entity.getClass(), entity, filedInfo.getProperty(), filedInfo.getPropertyType(), "DELETE");
                statusEntity.setIsHavingStatus(true);
            }
        }
        return statusEntity;
    }
}

