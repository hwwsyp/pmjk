/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.mapper.EntityWrapper
 *  com.baomidou.mybatisplus.mapper.Wrapper
 *  com.baomidou.mybatisplus.plugins.Page
 *  com.baomidou.mybatisplus.service.impl.ServiceImpl
 *  org.apache.commons.lang.StringUtils
 *  org.springframework.stereotype.Service
 *  org.springframework.transaction.annotation.Transactional
 */
package com.tpfh.fintech.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.tpfh.fintech.common.utils.PageUtils;
import com.tpfh.fintech.common.utils.Query;
import com.tpfh.fintech.modules.sys.dao.SysDeptDao;
import com.tpfh.fintech.modules.sys.entity.SysDeptEntity;
import com.tpfh.fintech.modules.sys.service.SysDeptService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value="sysDeptService")
public class SysDeptServiceImpl
extends ServiceImpl<SysDeptDao, SysDeptEntity>
implements SysDeptService {
    @Transactional(rollbackFor={Exception.class})
    public boolean insert(SysDeptEntity entity) {
        ((SysDeptDao)this.baseMapper).insert(entity);
        if (entity.getParentId() != null) {
            SysDeptEntity parentEntity = (SysDeptEntity)((SysDeptDao)this.baseMapper).selectById(entity.getParentId());
            entity.setPath(String.valueOf(parentEntity.getPath()) + ",$" + entity.getDeptId() + "$");
        } else {
            entity.setPath("$" + entity.getDeptId() + "$");
        }
        SysDeptEntity tempEntity = new SysDeptEntity();
        tempEntity.setDeptId(entity.getDeptId());
        tempEntity.setPath(entity.getPath());
        ((SysDeptDao)this.baseMapper).updateById(tempEntity);
        return true;
    }

    @Transactional(rollbackFor={Exception.class})
    public boolean updateById(SysDeptEntity entity) {
        ((SysDeptDao)this.baseMapper).updateById(entity);
        if (entity.getParentId() != null) {
            SysDeptEntity parentEntity = (SysDeptEntity)((SysDeptDao)this.baseMapper).selectById(entity.getParentId());
            entity.setPath(String.valueOf(parentEntity.getPath()) + ",$" + entity.getDeptId() + "$");
        } else {
            entity.setPath("$" + entity.getDeptId() + "$");
        }
        SysDeptEntity tempEntity = new SysDeptEntity();
        tempEntity.setDeptId(entity.getDeptId());
        tempEntity.setPath(entity.getPath());
        ((SysDeptDao)this.baseMapper).updateById(entity);
        return true;
    }

    @Override
    public List<String> queryDetpIdList(Integer parentId) {
        return ((SysDeptDao)this.baseMapper).queryDetpIdList(parentId);
    }

    @Override
    public List<SysDeptEntity> getSubDeptList(Integer deptId) {
        EntityWrapper wrapper = new EntityWrapper();
        wrapper.eq("parent_id", (Object)deptId);
        wrapper.eq("del_flag", (Object)0);
        wrapper.orderBy("order_num");
        return ((SysDeptDao)this.baseMapper).selectList((Wrapper)wrapper);
    }

    @Override
    public List<SysDeptEntity> queryList(SysDeptEntity sysDeptEntity) {
        return ((SysDeptDao)this.baseMapper).queryList(sysDeptEntity);
    }

    @Override
    public SysDeptEntity queryObject(String deptId) {
        return ((SysDeptDao)this.baseMapper).queryObject(deptId);
    }

    @Override
    public PageUtils queryPage(HashMap<String, Object> params) {
        String key = (String)params.get("key");
        Page page = this.selectPage(new Query((Map<String, Object>)params).getPage(), new EntityWrapper().like(StringUtils.isNotBlank((String)key), "parentId", key).eq("delFlag", (Object)0));
        return new PageUtils(page);
    }

    @Override
    public List<SysDeptEntity> getDeptsList() {
        List<SysDeptEntity> deptsList = ((SysDeptDao)this.baseMapper).getDeptsList();
        return deptsList;
    }

    @Override
    public List<SysDeptEntity> queryList(Long userId) {
        return ((SysDeptDao)this.baseMapper).queryList(userId);
    }
}

