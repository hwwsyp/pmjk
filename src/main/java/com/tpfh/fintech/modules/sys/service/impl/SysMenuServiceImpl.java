/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.service.impl.ServiceImpl
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.stereotype.Service
 */
package com.tpfh.fintech.modules.sys.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.tpfh.fintech.common.utils.Constant;
import com.tpfh.fintech.common.utils.MapUtils;
import com.tpfh.fintech.modules.sys.dao.SysMenuDao;
import com.tpfh.fintech.modules.sys.entity.SysMenuEntity;
import com.tpfh.fintech.modules.sys.service.SysMenuService;
import com.tpfh.fintech.modules.sys.service.SysRoleMenuService;
import com.tpfh.fintech.modules.sys.service.SysUserService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="sysMenuService")
public class SysMenuServiceImpl
extends ServiceImpl<SysMenuDao, SysMenuEntity>
implements SysMenuService {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    @Override
    public List<SysMenuEntity> queryListParentId(Long parentId, List<Long> menuIdList) {
        List<SysMenuEntity> menuList = this.queryListParentId(parentId);
        if (menuIdList == null) {
            return menuList;
        }
        ArrayList<SysMenuEntity> userMenuList = new ArrayList<SysMenuEntity>();
        for (SysMenuEntity menu : menuList) {
            if (!menuIdList.contains(menu.getMenuId())) continue;
            userMenuList.add(menu);
        }
        return userMenuList;
    }

    @Override
    public List<SysMenuEntity> queryListParentId(Long parentId) {
        return ((SysMenuDao)this.baseMapper).queryListParentId(parentId);
    }

    @Override
    public List<SysMenuEntity> queryNotButtonList() {
        return ((SysMenuDao)this.baseMapper).queryNotButtonList();
    }

    @Override
    public List<SysMenuEntity> getUserMenuList(Long userId) {
        if (userId == Constant.SUPER_ADMIN) {
            return this.getAllMenuList(null);
        }
        List<Long> menuIdList = this.sysUserService.queryAllMenuId(userId);
        return this.getAllMenuList(menuIdList);
    }

    @Override
    public void delete(Long menuId) {
        this.deleteById(menuId);
        this.sysRoleMenuService.deleteByMap(new MapUtils().put("menu_id", (Object)menuId));
    }

    private List<SysMenuEntity> getAllMenuList(List<Long> menuIdList) {
        List<SysMenuEntity> menuList = this.queryListParentId(0L, menuIdList);
        this.getMenuTreeList(menuList, menuIdList);
        return menuList;
    }

    private List<SysMenuEntity> getMenuTreeList(List<SysMenuEntity> menuList, List<Long> menuIdList) {
        ArrayList<SysMenuEntity> subMenuList = new ArrayList<SysMenuEntity>();
        for (SysMenuEntity entity : menuList) {
            if (entity.getType().intValue() == Constant.MenuType.CATALOG.getValue()) {
                entity.setList(this.getMenuTreeList(this.queryListParentId(entity.getMenuId(), menuIdList), menuIdList));
            }
            subMenuList.add(entity);
        }
        return subMenuList;
    }
}

