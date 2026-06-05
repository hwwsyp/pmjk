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
package com.tpfh.fintech.modules.pmjk.stakeholder.controller;

import com.tpfh.fintech.common.annotation.SysLog;
import com.tpfh.fintech.common.utils.PageUtils;
import com.tpfh.fintech.common.utils.R;
import com.tpfh.fintech.modules.pmjk.stakeholder.entity.StakeholderEntity;
import com.tpfh.fintech.modules.pmjk.stakeholder.service.StakeholderService;
import com.tpfh.fintech.modules.sys.controller.AbstractController;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
@RequestMapping(value={"/pmjk/stakeholder"})
public class StakeholderController
extends AbstractController {
    @Autowired
    private StakeholderService stakeholderService;

    @GetMapping(value={"/list"})
    @RequiresPermissions(value={"pmjk:stakeholder:list"})
    public R list(@RequestParam HashMap<String, Object> params) {
        PageUtils page = this.stakeholderService.queryPage(params);
        return R.ok().put("page", (Object)page);
    }

    @GetMapping(value={"/getStakeholderList"})
    @RequiresPermissions(value={"pmjk:stakeholder:list"})
    public R getStakeholderList(@RequestParam HashMap<String, Object> params) {
        List<StakeholderEntity> list = this.stakeholderService.getInfoList(params);
        return R.ok().put("result", (Object)list);
    }

    @GetMapping(value={"/info/{productid}/{versionnum}"})
    @RequiresPermissions(value={"pmjk:stakeholder:info"})
    public R info(@PathVariable(value="productid") Long productid, @PathVariable(value="versionnum") Long versionnum) {
        StakeholderEntity stakeholderInfo = this.stakeholderService.getInfoByProductidAndVersionnum(productid, versionnum);
        return R.ok().put("stakeholderInfo", (Object)stakeholderInfo);
    }

    @SysLog(value="\u65b0\u589e")
    @PostMapping(value={"/save"})
    @RequiresPermissions(value={"pmjk:stakeholder:save"})
    public R add(@RequestBody StakeholderEntity stakeholderEntity) {
        Date now = new Date();
        stakeholderEntity.setCreatetimestamp(now);
        stakeholderEntity.setUpdatetimestamp(now);
        this.stakeholderService.insert(stakeholderEntity);
        return R.ok();
    }

    @SysLog(value="\u66f4\u65b0")
    @PostMapping(value={"/update"})
    @RequiresPermissions(value={"pmjk:stakeholder:update"})
    public R update(@RequestBody StakeholderEntity stakeholderEntity) {
        Date now = new Date();
        stakeholderEntity.setUpdatetimestamp(now);
        this.stakeholderService.updateById(stakeholderEntity);
        return R.ok();
    }

    @SysLog(value="\u5220\u9664")
    @PostMapping(value={"/delete"})
    @RequiresPermissions(value={"pmjk:stakeholder:delete"})
    public R delete(@RequestBody List<Long> ids) {
        this.stakeholderService.deleteBatchIds(ids);
        return R.ok();
    }
}

