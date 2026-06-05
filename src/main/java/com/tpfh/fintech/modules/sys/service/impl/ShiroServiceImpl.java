/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.commons.lang.StringUtils
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.stereotype.Service
 */
package com.tpfh.fintech.modules.sys.service.impl;

import com.tpfh.fintech.common.utils.Constant;
import com.tpfh.fintech.modules.sys.dao.SysMenuDao;
import com.tpfh.fintech.modules.sys.dao.SysUserDao;
import com.tpfh.fintech.modules.sys.dao.SysUserTokenDao;
import com.tpfh.fintech.modules.sys.entity.SysMenuEntity;
import com.tpfh.fintech.modules.sys.entity.SysUserEntity;
import com.tpfh.fintech.modules.sys.entity.SysUserTokenEntity;
import com.tpfh.fintech.modules.sys.service.ShiroService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShiroServiceImpl
implements ShiroService {
    @Autowired
    private SysMenuDao sysMenuDao;
    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private SysUserTokenDao sysUserTokenDao;

    @Override
    public Set<String> getUserPermissions(long userId) {
        List<String> permsList;
        if (userId == Constant.SUPER_ADMIN) {
            List menuList = this.sysMenuDao.selectList(null);
            permsList = new ArrayList<String>(menuList.size());
            for (SysMenuEntity menu : menuList) {
                permsList.add(menu.getPerms());
            }
        } else {
            permsList = this.sysUserDao.queryAllPerms(userId);
        }
        HashSet<String> permsSet = new HashSet<String>();
        for (String perms : permsList) {
            if (StringUtils.isBlank((String)perms)) continue;
            permsSet.addAll(Arrays.asList(perms.trim().split(",")));
        }
        return permsSet;
    }

    @Override
    public SysUserTokenEntity queryByToken(String token) {
        return this.sysUserTokenDao.queryByToken(token);
    }

    @Override
    public SysUserEntity queryUser(Long userId) {
        return (SysUserEntity)this.sysUserDao.selectById(userId);
    }
}

