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
package com.tpfh.fintech.modules.job.controller;

import com.tpfh.fintech.common.annotation.SysLog;
import com.tpfh.fintech.common.utils.PageUtils;
import com.tpfh.fintech.common.utils.R;
import com.tpfh.fintech.common.validator.ValidatorUtils;
import com.tpfh.fintech.modules.job.entity.ScheduleJobEntity;
import com.tpfh.fintech.modules.job.service.ScheduleJobService;
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
@RequestMapping(value={"/sys/schedule"})
public class ScheduleJobController {
    @Autowired
    private ScheduleJobService scheduleJobService;

    @GetMapping(value={"/list"})
    @RequiresPermissions(value={"sys:schedule:list"})
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = this.scheduleJobService.queryPage(params);
        return R.ok().put("page", (Object)page);
    }

    @GetMapping(value={"/info/{jobId}"})
    @RequiresPermissions(value={"sys:schedule:info"})
    public R info(@PathVariable(value="jobId") Long jobId) {
        ScheduleJobEntity schedule = (ScheduleJobEntity)this.scheduleJobService.selectById(jobId);
        return R.ok().put("schedule", (Object)schedule);
    }

    @SysLog(value="\u4fdd\u5b58\u5b9a\u65f6\u4efb\u52a1")
    @PostMapping(value={"/save"})
    @RequiresPermissions(value={"sys:schedule:save"})
    public R save(@RequestBody ScheduleJobEntity scheduleJob) {
        ValidatorUtils.validateEntity(scheduleJob, new Class[0]);
        this.scheduleJobService.save(scheduleJob);
        return R.ok();
    }

    @SysLog(value="\u4fee\u6539\u5b9a\u65f6\u4efb\u52a1")
    @PostMapping(value={"/update"})
    @RequiresPermissions(value={"sys:schedule:update"})
    public R update(@RequestBody ScheduleJobEntity scheduleJob) {
        ValidatorUtils.validateEntity(scheduleJob, new Class[0]);
        this.scheduleJobService.update(scheduleJob);
        return R.ok();
    }

    @SysLog(value="\u5220\u9664\u5b9a\u65f6\u4efb\u52a1")
    @PostMapping(value={"/delete"})
    @RequiresPermissions(value={"sys:schedule:delete"})
    public R delete(@RequestBody Long[] jobIds) {
        this.scheduleJobService.deleteBatch(jobIds);
        return R.ok();
    }

    @SysLog(value="\u7acb\u5373\u6267\u884c\u4efb\u52a1")
    @PostMapping(value={"/run"})
    @RequiresPermissions(value={"sys:schedule:run"})
    public R run(@RequestBody Long[] jobIds) {
        this.scheduleJobService.run(jobIds);
        return R.ok();
    }

    @SysLog(value="\u6682\u505c\u5b9a\u65f6\u4efb\u52a1")
    @PostMapping(value={"/pause"})
    @RequiresPermissions(value={"sys:schedule:pause"})
    public R pause(@RequestBody Long[] jobIds) {
        this.scheduleJobService.pause(jobIds);
        return R.ok();
    }

    @SysLog(value="\u6062\u590d\u5b9a\u65f6\u4efb\u52a1")
    @PostMapping(value={"/resume"})
    @RequiresPermissions(value={"sys:schedule:resume"})
    public R resume(@RequestBody Long[] jobIds) {
        this.scheduleJobService.resume(jobIds);
        return R.ok();
    }
}

