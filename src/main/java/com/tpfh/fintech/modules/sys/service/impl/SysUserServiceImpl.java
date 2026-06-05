/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.mapper.EntityWrapper
 *  com.baomidou.mybatisplus.plugins.Page
 *  com.baomidou.mybatisplus.service.impl.ServiceImpl
 *  org.apache.commons.lang.RandomStringUtils
 *  org.apache.commons.lang.StringUtils
 *  org.apache.shiro.crypto.hash.Sha256Hash
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.stereotype.Service
 *  org.springframework.transaction.annotation.Transactional
 */
package com.tpfh.fintech.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.tpfh.fintech.common.utils.PageUtils;
import com.tpfh.fintech.modules.sys.dao.SysUserDao;
import com.tpfh.fintech.modules.sys.entity.SysDeptEntity;
import com.tpfh.fintech.modules.sys.entity.SysUserEntity;
import com.tpfh.fintech.modules.sys.service.SysDeptService;
import com.tpfh.fintech.modules.sys.service.SysUserRoleService;
import com.tpfh.fintech.modules.sys.service.SysUserService;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value="sysUserService")
public class SysUserServiceImpl
extends ServiceImpl<SysUserDao, SysUserEntity>
implements SysUserService {
    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private SysDeptService sysDeptService;
    @Autowired
    private SysUserDao sysUserDao;

    @Override
    public PageUtils queryPage(HashMap<String, Object> params) {
        Page page = new Page();
        Integer pageno = Integer.parseInt(params.get("page").toString());
        Integer limit = Integer.parseInt(params.get("limit").toString());
        page.setCurrent(pageno.intValue());
        page.setSize(limit.intValue());
        page.setRecords(this.sysUserDao.getUserList((Page<SysUserEntity>)page, params));
        return new PageUtils(page);
    }

    @Override
    public List<String> queryAllPerms(Long userId) {
        return ((SysUserDao)this.baseMapper).queryAllPerms(userId);
    }

    @Override
    public List<Long> queryAllMenuId(Long userId) {
        return ((SysUserDao)this.baseMapper).queryAllMenuId(userId);
    }

    @Override
    public SysUserEntity queryByUserName(String username) {
        return ((SysUserDao)this.baseMapper).queryByUserName(username);
    }

    @Override
    @Transactional
    public void save(SysUserEntity user) {
        user.setCreateTime(new Date());
        String salt = RandomStringUtils.randomAlphanumeric((int)20);
        user.setPassword(new Sha256Hash((Object)user.getPassword(), (Object)salt).toHex());
        user.setSalt(salt);
        this.insert(user);
        this.sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
    }

    @Override
    @Transactional
    public void update(SysUserEntity user) {
        if (StringUtils.isBlank((String)user.getPassword())) {
            user.setPassword(null);
        } else {
            user.setPassword(new Sha256Hash((Object)user.getPassword(), (Object)user.getSalt()).toHex());
        }
        this.updateById(user);
        this.sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
    }

    @Override
    public void deleteBatch(Long[] userId) {
        this.deleteBatchIds(Arrays.asList(userId));
    }

    @Override
    public boolean updatePassword(Long userId, String password, String newPassword) {
        SysUserEntity userEntity = new SysUserEntity();
        userEntity.setPassword(newPassword);
        return this.update(userEntity, new EntityWrapper().eq("user_id", (Object)userId).eq("password", (Object)password));
    }

    @Override
    public List<SysUserEntity> getUsersList(String word) {
        return ((SysUserDao)this.baseMapper).getUsersList(word);
    }

    @Override
    public List<SysUserEntity> getAllUsersList() {
        return ((SysUserDao)this.baseMapper).getAllUsersList();
    }

    @Override
    public List<SysUserEntity> getUsersListByDeptId(Integer deptId) {
        return ((SysUserDao)this.baseMapper).getUsersListByDeptId(deptId);
    }

    @Override
    public List<SysUserEntity> getUsersListByRoleId(String roleId) {
        return ((SysUserDao)this.baseMapper).getUsersListByRoleId(roleId);
    }

    @Override
    public SysUserEntity getUserInfo(Long userId) {
        SysUserEntity user = (SysUserEntity)((SysUserDao)this.baseMapper).selectById(userId);
        List<Long> roleIdList = this.sysUserRoleService.queryRoleIdList(userId);
        user.setRoleIdList(roleIdList);
        SysDeptEntity sysDept = (SysDeptEntity)this.sysDeptService.selectById((Serializable)((Object)user.getDeptId()));
        user.setDeptName(sysDept.getName());
        return user;
    }
}

