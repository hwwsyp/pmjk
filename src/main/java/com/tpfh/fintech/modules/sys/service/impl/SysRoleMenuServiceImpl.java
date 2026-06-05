/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.service.impl.ServiceImpl
 *  org.springframework.stereotype.Service
 *  org.springframework.transaction.annotation.Transactional
 */
package com.tpfh.fintech.modules.sys.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.tpfh.fintech.modules.sys.dao.SysRoleMenuDao;
import com.tpfh.fintech.modules.sys.entity.SysRoleMenuEntity;
import com.tpfh.fintech.modules.sys.service.SysRoleMenuService;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value="sysRoleMenuService")
public class SysRoleMenuServiceImpl
extends ServiceImpl<SysRoleMenuDao, SysRoleMenuEntity>
implements SysRoleMenuService {
    @Override
    @Transactional(rollbackFor={Exception.class})
    public void saveOrUpdate(Long roleId, List<Long> menuIdList) {
        this.deleteBatch(new Long[]{roleId});
        if (menuIdList.size() == 0) {
            return;
        }
        for (Long menuId : menuIdList) {
            SysRoleMenuEntity sysRoleMenuEntity = new SysRoleMenuEntity();
            sysRoleMenuEntity.setMenuId(menuId);
            sysRoleMenuEntity.setRoleId(roleId);
            this.insert(sysRoleMenuEntity);
        }
    }

    @Override
    public List<Long> queryMenuIdList(Long roleId) {
        return ((SysRoleMenuDao)this.baseMapper).queryMenuIdList(roleId);
    }

    @Override
    public int deleteBatch(Long[] roleIds) {
        return ((SysRoleMenuDao)this.baseMapper).deleteBatch(roleIds);
    }
}

