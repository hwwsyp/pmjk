/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.service.impl.ServiceImpl
 *  org.springframework.stereotype.Service
 */
package com.tpfh.fintech.modules.sys.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.tpfh.fintech.common.utils.MapUtils;
import com.tpfh.fintech.modules.sys.dao.SysUserRoleDao;
import com.tpfh.fintech.modules.sys.entity.SysUserRoleEntity;
import com.tpfh.fintech.modules.sys.service.SysUserRoleService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service(value="sysUserRoleService")
public class SysUserRoleServiceImpl
extends ServiceImpl<SysUserRoleDao, SysUserRoleEntity>
implements SysUserRoleService {
    @Override
    public void saveOrUpdate(Long userId, List<Long> roleIdList) {
        this.deleteByMap(new MapUtils().put("user_id", (Object)userId));
        if (roleIdList == null || roleIdList.size() == 0) {
            return;
        }
        for (Long roleId : roleIdList) {
            SysUserRoleEntity sysUserRoleEntity = new SysUserRoleEntity();
            sysUserRoleEntity.setUserId(userId);
            sysUserRoleEntity.setRoleId(roleId);
            this.insert(sysUserRoleEntity);
        }
    }

    @Override
    public List<Long> queryRoleIdList(Long userId) {
        return ((SysUserRoleDao)this.baseMapper).queryRoleIdList(userId);
    }

    @Override
    public int deleteBatch(Long[] roleIds) {
        return ((SysUserRoleDao)this.baseMapper).deleteBatch(roleIds);
    }
}

