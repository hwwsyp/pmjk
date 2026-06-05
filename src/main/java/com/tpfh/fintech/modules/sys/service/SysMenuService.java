/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.service.IService
 */
package com.tpfh.fintech.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.tpfh.fintech.modules.sys.entity.SysMenuEntity;
import java.util.List;

public interface SysMenuService
extends IService<SysMenuEntity> {
    public List<SysMenuEntity> queryListParentId(Long var1, List<Long> var2);

    public List<SysMenuEntity> queryListParentId(Long var1);

    public List<SysMenuEntity> queryNotButtonList();

    public List<SysMenuEntity> getUserMenuList(Long var1);

    public void delete(Long var1);
}

