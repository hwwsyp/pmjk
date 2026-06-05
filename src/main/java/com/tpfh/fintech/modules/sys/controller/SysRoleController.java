/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.shiro.authz.annotation.RequiresPermissions
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.web.bind.annotation.GetMapping
 *  org.springframework.web.bind.annotation.PathVariable
 *  org.springframework.web.bind.annotation.PostMapping
 *  org.springframework.web.bind.annotation.RequestBody
 *  org.springframework.web.bind.annotation.RequestMapping
 *  org.springframework.web.bind.annotation.RequestParam
 *  org.springframework.web.bind.annotation.RestController
 */
package com.tpfh.fintech.modules.sys.controller;

import com.tpfh.fintech.common.annotation.SysLog;
import com.tpfh.fintech.common.utils.PageUtils;
import com.tpfh.fintech.common.utils.R;
import com.tpfh.fintech.common.validator.ValidatorUtils;
import com.tpfh.fintech.modules.sys.controller.AbstractController;
import com.tpfh.fintech.modules.sys.entity.SysRoleEntity;
import com.tpfh.fintech.modules.sys.service.SysRoleMenuService;
import com.tpfh.fintech.modules.sys.service.SysRoleService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value={"/sys/role"})
public class SysRoleController
extends AbstractController {
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    @GetMapping(value={"/list"})
    @RequiresPermissions(value={"sys:role:list"})
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = this.sysRoleService.queryPage(params);
        return R.ok().put("page", (Object)page);
    }

    @GetMapping(value={"/select"})
    @RequiresPermissions(value={"sys:role:select"})
    public R select() {
        HashMap map = new HashMap();
        List list = this.sysRoleService.selectByMap(map);
        return R.ok().put("list", (Object)list);
    }

    @GetMapping(value={"/info/{roleId}"})
    @RequiresPermissions(value={"sys:role:info"})
    public R info(@PathVariable(value="roleId") Long roleId) {
        SysRoleEntity role = (SysRoleEntity)this.sysRoleService.selectById(roleId);
        List<Long> menuIdList = this.sysRoleMenuService.queryMenuIdList(roleId);
        role.setMenuIdList(menuIdList);
        return R.ok().put("role", (Object)role);
    }

    @SysLog(value="\u4fdd\u5b58\u89d2\u8272")
    @PostMapping(value={"/save"})
    @RequiresPermissions(value={"sys:role:save"})
    public R save(@RequestBody SysRoleEntity role) {
        ValidatorUtils.validateEntity(role, new Class[0]);
        role.setCreateUserId(this.getUserId());
        this.sysRoleService.save(role);
        return R.ok();
    }

    @SysLog(value="\u4fee\u6539\u89d2\u8272")
    @PostMapping(value={"/update"})
    @RequiresPermissions(value={"sys:role:update"})
    public R update(@RequestBody SysRoleEntity role) {
        ValidatorUtils.validateEntity(role, new Class[0]);
        role.setCreateUserId(this.getUserId());
        this.sysRoleService.update(role);
        return R.ok();
    }

    @SysLog(value="\u5220\u9664\u89d2\u8272")
    @PostMapping(value={"/delete"})
    @RequiresPermissions(value={"sys:role:delete"})
    public R delete(@RequestBody Long[] roleIds) {
        this.sysRoleService.deleteBatch(roleIds);
        return R.ok();
    }

    @RequestMapping(value={"/getRolesList"})
    public R getRolesList() {
        List<SysRoleEntity> rolesList = this.sysRoleService.getRolesList();
        return R.ok().put("rolesList", (Object)rolesList);
    }
}

