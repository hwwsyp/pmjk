/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.alibaba.fastjson.JSON
 *  com.alibaba.fastjson.JSONObject
 *  org.apache.commons.lang.ArrayUtils
 *  org.apache.shiro.authz.annotation.RequiresPermissions
 *  org.apache.shiro.crypto.hash.Sha256Hash
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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tpfh.fintech.common.annotation.SysLog;
import com.tpfh.fintech.common.utils.AESDecryptorUtil;
import com.tpfh.fintech.common.utils.Constant;
import com.tpfh.fintech.common.utils.PageUtils;
import com.tpfh.fintech.common.utils.R;
import com.tpfh.fintech.common.validator.Assert;
import com.tpfh.fintech.common.validator.ValidatorUtils;
import com.tpfh.fintech.common.validator.group.AddGroup;
import com.tpfh.fintech.common.validator.group.UpdateGroup;
import com.tpfh.fintech.modules.sys.controller.AbstractController;
import com.tpfh.fintech.modules.sys.entity.SysRoleEntity;
import com.tpfh.fintech.modules.sys.entity.SysUserEntity;
import com.tpfh.fintech.modules.sys.form.PasswordForm;
import com.tpfh.fintech.modules.sys.service.SysRoleService;
import com.tpfh.fintech.modules.sys.service.SysUserRoleService;
import com.tpfh.fintech.modules.sys.service.SysUserService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.lang.ArrayUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value={"/sys/user"})
public class SysUserController
extends AbstractController {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private SysRoleService sysRoleService;

    @GetMapping(value={"/list"})
    @RequiresPermissions(value={"sys:user:list"})
    public R list(@RequestParam HashMap<String, Object> params) {
        params.put("userId", this.getUserId());
        PageUtils page = this.sysUserService.queryPage(params);
        return R.ok().put("page", (Object)page);
    }

    @GetMapping(value={"/info"})
    public R info() {
        SysUserEntity user = this.getUser();
        user.setSalt("");
        user.setPassword("");
        if (user.getUserId() == Constant.SUPER_ADMIN) {
            List list = this.sysRoleService.selectByMap(null);
            ArrayList<Long> roleIdList = new ArrayList<Long>();
            for (SysRoleEntity role : list) {
                roleIdList.add(role.getRoleId());
            }
            user.setRoleIdList(roleIdList);
            return R.ok().put("user", (Object)user);
        }
        List<Long> roleIdList = this.sysUserRoleService.queryRoleIdList(user.getUserId());
        user.setRoleIdList(roleIdList);
        return R.ok().put("user", (Object)user);
    }

    @SysLog(value="\u4fee\u6539\u5bc6\u7801")
    @PostMapping(value={"/password"})
    public R password(@RequestBody PasswordForm form) {
        Assert.isBlank(form.getNewPassword(), "\u65b0\u5bc6\u7801\u4e0d\u4e3a\u80fd\u7a7a");
        String oldEncryptedData = form.getPassword();
        String newEncryptedData = form.getNewPassword();
        String key = "jk%fdsa2QERX_2+2";
        String iv = "1iopi7&FDS123456";
        try {
            String oldPassword = AESDecryptorUtil.decrypt(oldEncryptedData, key, iv);
            String newPassword = AESDecryptorUtil.decrypt(newEncryptedData, key, iv);
            System.out.println("\u89e3\u5bc6\u7ed3\u679c\uff1a" + oldPassword + "," + newPassword);
            String password = new Sha256Hash((Object)oldPassword, (Object)this.getUser().getSalt()).toHex();
            newPassword = new Sha256Hash((Object)newPassword, (Object)this.getUser().getSalt()).toHex();
            boolean flag = this.sysUserService.updatePassword(this.getUserId(), password, newPassword);
            if (!flag) {
                return R.error("\u539f\u5bc6\u7801\u4e0d\u6b63\u786e");
            }
            return R.ok();
        }
        catch (Exception e) {
            return R.error("\u66f4\u65b0\u5bc6\u7801\u5931\u8d25");
        }
    }

    @GetMapping(value={"/info/{userId}"})
    public R info(@PathVariable(value="userId") Long userId) {
        return R.ok().put("user", (Object)this.sysUserService.getUserInfo(userId));
    }

    @SysLog(value="\u4fdd\u5b58\u7528\u6237")
    @PostMapping(value={"/save"})
    @RequiresPermissions(value={"sys:user:save"})
    public R save(@RequestBody JSONObject userJSON) {
        SysUserEntity user = (SysUserEntity)JSON.toJavaObject((JSON)userJSON, SysUserEntity.class);
        ValidatorUtils.validateEntity(user, AddGroup.class);
        user.setCreateUserId(this.getUserId());
        this.sysUserService.save(user);
        return R.ok();
    }

    @SysLog(value="\u4fee\u6539\u7528\u6237")
    @PostMapping(value={"/update"})
    @RequiresPermissions(value={"sys:user:update"})
    public R update(@RequestBody SysUserEntity user) {
        ValidatorUtils.validateEntity(user, UpdateGroup.class);
        user.setCreateUserId(this.getUserId());
        this.sysUserService.update(user);
        return R.ok();
    }

    @SysLog(value="\u5220\u9664\u7528\u6237")
    @PostMapping(value={"/delete"})
    @RequiresPermissions(value={"sys:user:delete"})
    public R delete(@RequestBody Long[] userIds) {
        if (ArrayUtils.contains((Object[])userIds, (Object)1L)) {
            return R.error("\u7cfb\u7edf\u7ba1\u7406\u5458\u4e0d\u80fd\u5220\u9664");
        }
        if (ArrayUtils.contains((Object[])userIds, (Object)this.getUserId())) {
            return R.error("\u5f53\u524d\u7528\u6237\u4e0d\u80fd\u5220\u9664");
        }
        this.sysUserService.deleteBatch(userIds);
        return R.ok();
    }

    @RequestMapping(value={"/getUsersList"})
    public R getUsersList(String word) {
        List<SysUserEntity> usersList = this.sysUserService.getUsersList(word);
        return R.ok().put("usersList", (Object)usersList);
    }

    @RequestMapping(value={"/getAllUsersList"})
    public R getAllUsersList() {
        List<SysUserEntity> usersList = this.sysUserService.getAllUsersList();
        return R.ok().put("usersList", (Object)usersList);
    }

    @RequestMapping(value={"/getUsersListByDeptId"})
    public R getUsersListByDeptId(Integer deptId) {
        List<SysUserEntity> usersList = this.sysUserService.getUsersListByDeptId(deptId);
        return R.ok().put("usersList", (Object)usersList);
    }

    @RequestMapping(value={"/getUsersListByRoleId"})
    public R getUsersListByRoleId(String roleId) {
        List<SysUserEntity> usersList = this.sysUserService.getUsersListByRoleId(roleId);
        return R.ok().put("usersList", (Object)usersList);
    }
}

