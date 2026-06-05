/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.mapper.EntityWrapper
 *  com.baomidou.mybatisplus.plugins.Page
 *  com.baomidou.mybatisplus.service.impl.ServiceImpl
 *  org.apache.commons.lang.StringUtils
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.stereotype.Service
 *  org.springframework.transaction.annotation.Transactional
 */
package com.tpfh.fintech.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.tpfh.fintech.common.exception.TpfhException;
import com.tpfh.fintech.common.utils.Constant;
import com.tpfh.fintech.common.utils.PageUtils;
import com.tpfh.fintech.common.utils.Query;
import com.tpfh.fintech.modules.sys.dao.SysRoleDao;
import com.tpfh.fintech.modules.sys.entity.SysRoleEntity;
import com.tpfh.fintech.modules.sys.service.SysRoleMenuService;
import com.tpfh.fintech.modules.sys.service.SysRoleService;
import com.tpfh.fintech.modules.sys.service.SysUserRoleService;
import com.tpfh.fintech.modules.sys.service.SysUserService;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value="sysRoleService")
public class SysRoleServiceImpl
extends ServiceImpl<SysRoleDao, SysRoleEntity>
implements SysRoleService {
    @Autowired
    private SysRoleMenuService sysRoleMenuService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String roleName = (String)params.get("roleName");
        Long createUserId = (Long)params.get("createUserId");
        Page page = this.selectPage(new Query(params).getPage(), new EntityWrapper().like(StringUtils.isNotBlank((String)roleName), "role_name", roleName).eq(createUserId != null, "create_user_id", (Object)createUserId));
        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor={Exception.class})
    public void save(SysRoleEntity role) {
        role.setCreateTime(new Date());
        this.insert(role);
        this.checkPrems(role);
        this.sysRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
    }

    @Override
    @Transactional(rollbackFor={Exception.class})
    public void update(SysRoleEntity role) {
        this.updateById(role);
        this.checkPrems(role);
        this.sysRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
    }

    @Override
    @Transactional(rollbackFor={Exception.class})
    public void deleteBatch(Long[] roleIds) {
        this.deleteBatchIds(Arrays.asList(roleIds));
        this.sysRoleMenuService.deleteBatch(roleIds);
        this.sysUserRoleService.deleteBatch(roleIds);
    }

    @Override
    public List<Long> queryRoleIdList(Long createUserId) {
        return ((SysRoleDao)this.baseMapper).queryRoleIdList(createUserId);
    }

    private void checkPrems(SysRoleEntity role) {
        if (role.getCreateUserId() == Constant.SUPER_ADMIN) {
            return;
        }
        List<Long> menuIdList = this.sysUserService.queryAllMenuId(role.getCreateUserId());
        if (!menuIdList.containsAll(role.getMenuIdList())) {
            throw new TpfhException("\u65b0\u589e\u89d2\u8272\u7684\u6743\u9650\uff0c\u5df2\u8d85\u51fa\u4f60\u7684\u6743\u9650\u8303\u56f4");
        }
    }

    @Override
    public List<SysRoleEntity> getRolesList() {
        return ((SysRoleDao)this.baseMapper).getRolesList();
    }
}

