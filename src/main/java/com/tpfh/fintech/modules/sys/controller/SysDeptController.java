/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.shiro.authz.annotation.RequiresPermissions
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.web.bind.annotation.PathVariable
 *  org.springframework.web.bind.annotation.RequestBody
 *  org.springframework.web.bind.annotation.RequestMapping
 *  org.springframework.web.bind.annotation.RestController
 */
package com.tpfh.fintech.modules.sys.controller;

import com.tpfh.fintech.common.utils.Constant;
import com.tpfh.fintech.common.utils.R;
import com.tpfh.fintech.modules.sys.controller.AbstractController;
import com.tpfh.fintech.modules.sys.entity.SysDeptEntity;
import com.tpfh.fintech.modules.sys.entity.SysUserEntity;
import com.tpfh.fintech.modules.sys.service.SysDeptService;
import com.tpfh.fintech.modules.sys.service.SysUserService;
import java.io.Serializable;
import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value={"/sys/dept"})
public class SysDeptController
extends AbstractController {
    @Autowired
    private SysDeptService sysDeptService;
    @Autowired
    private SysUserService sysUserService;

    @RequestMapping(value={"/list"})
    @RequiresPermissions(value={"sys:dept:list"})
    public List<SysDeptEntity> list() {
        List<SysDeptEntity> deptList = this.sysDeptService.queryList(this.getUserId());
        return deptList;
    }

    @RequestMapping(value={"/select"})
    @RequiresPermissions(value={"sys:dept:select"})
    public R select() {
        List<SysDeptEntity> deptList = this.sysDeptService.queryList(this.getUserId());
        return R.ok().put("deptList", (Object)deptList);
    }

    @RequestMapping(value={"/info"})
    @RequiresPermissions(value={"sys:dept:list"})
    public R info() {
        Integer deptId = null;
        if (this.getUserId() != Constant.SUPER_ADMIN) {
            SysDeptEntity dept = (SysDeptEntity)this.sysDeptService.selectById((Serializable)((Object)super.getDeptId()));
            deptId = dept.getParentId();
        }
        return R.ok().put("deptId", (Object)deptId);
    }

    @RequestMapping(value={"/info/{deptId}"})
    @RequiresPermissions(value={"sys:dept:info"})
    public R info(@PathVariable(value="deptId") String deptId) {
        SysDeptEntity dept = this.sysDeptService.queryObject(deptId);
        return R.ok().put("dept", (Object)dept);
    }

    @RequestMapping(value={"/save"})
    @RequiresPermissions(value={"sys:dept:save"})
    public R save(@RequestBody SysDeptEntity dept) {
        this.sysDeptService.insert(dept);
        return R.ok();
    }

    @RequestMapping(value={"/update"})
    @RequiresPermissions(value={"sys:dept:update"})
    public R update(@RequestBody SysDeptEntity dept) {
        this.sysDeptService.updateById(dept);
        return R.ok();
    }

    @RequestMapping(value={"/delete"})
    @RequiresPermissions(value={"sys:dept:delete"})
    public R delete(Integer deptId) {
        List<String> deptList = this.sysDeptService.queryDetpIdList(deptId);
        if (!deptList.isEmpty()) {
            return R.error("\u8bf7\u5148\u5220\u9664\u5b50\u90e8\u95e8");
        }
        List<SysUserEntity> userList = this.sysUserService.getUsersListByDeptId(deptId);
        if (!userList.isEmpty()) {
            return R.error("\u8be5\u90e8\u95e8\u4e0b\u5b58\u5728\u7528\u6237\uff0c\u65e0\u6cd5\u5220\u9664");
        }
        SysDeptEntity dept = new SysDeptEntity();
        dept.setDeptId(deptId);
        dept.setDelFlag(1);
        this.sysDeptService.updateById(dept);
        return R.ok();
    }

    @RequestMapping(value={"/getDeptsList"})
    public R getDeptsList() {
        List<SysDeptEntity> deptsList = this.sysDeptService.getDeptsList();
        return R.ok().put("deptsList", (Object)deptsList);
    }

    @RequestMapping(value={"/getSubDeptList/{deptId}"})
    public R getSubDeptIdList(@PathVariable(value="deptId") Integer deptId) {
        List<SysDeptEntity> deptsList = this.sysDeptService.getSubDeptList(deptId);
        return R.ok().put("deptsList", (Object)deptsList);
    }
}

