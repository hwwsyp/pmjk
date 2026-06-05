/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.shiro.authz.annotation.RequiresPermissions
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.stereotype.Controller
 *  org.springframework.web.bind.annotation.GetMapping
 *  org.springframework.web.bind.annotation.RequestMapping
 *  org.springframework.web.bind.annotation.RequestParam
 *  org.springframework.web.bind.annotation.ResponseBody
 */
package com.tpfh.fintech.modules.sys.controller;

import com.tpfh.fintech.common.utils.PageUtils;
import com.tpfh.fintech.common.utils.R;
import com.tpfh.fintech.modules.sys.service.SysLogService;
import java.util.Map;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value={"/sys/log"})
public class SysLogController {
    @Autowired
    private SysLogService sysLogService;

    @ResponseBody
    @GetMapping(value={"/list"})
    @RequiresPermissions(value={"sys:log:list"})
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = this.sysLogService.queryPage(params);
        return R.ok().put("page", (Object)page);
    }
}

