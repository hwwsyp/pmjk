/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.shiro.authz.annotation.RequiresPermissions
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.web.bind.annotation.GetMapping
 *  org.springframework.web.bind.annotation.PathVariable
 *  org.springframework.web.bind.annotation.RequestMapping
 *  org.springframework.web.bind.annotation.RequestParam
 *  org.springframework.web.bind.annotation.RestController
 */
package com.tpfh.fintech.modules.job.controller;

import com.tpfh.fintech.common.utils.PageUtils;
import com.tpfh.fintech.common.utils.R;
import com.tpfh.fintech.modules.job.entity.ScheduleJobLogEntity;
import com.tpfh.fintech.modules.job.service.ScheduleJobLogService;
import java.util.Map;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value={"/sys/scheduleLog"})
public class ScheduleJobLogController {
    @Autowired
    private ScheduleJobLogService scheduleJobLogService;

    @GetMapping(value={"/list"})
    @RequiresPermissions(value={"sys:schedule:log"})
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = this.scheduleJobLogService.queryPage(params);
        return R.ok().put("page", (Object)page);
    }

    @GetMapping(value={"/info/{logId}"})
    public R info(@PathVariable(value="logId") Long logId) {
        ScheduleJobLogEntity log = (ScheduleJobLogEntity)this.scheduleJobLogService.selectById(logId);
        return R.ok().put("log", (Object)log);
    }
}

