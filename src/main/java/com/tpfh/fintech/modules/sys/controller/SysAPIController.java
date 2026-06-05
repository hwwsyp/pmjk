/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.web.bind.annotation.GetMapping
 *  org.springframework.web.bind.annotation.RequestMapping
 *  org.springframework.web.bind.annotation.RequestParam
 *  org.springframework.web.bind.annotation.RestController
 */
package com.tpfh.fintech.modules.sys.controller;

import com.tpfh.fintech.common.utils.R;
import com.tpfh.fintech.modules.sys.controller.AbstractController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value={"/sys/api"})
public class SysAPIController
extends AbstractController {
    @GetMapping(value={"/saveAccStatis"})
    public R saveAccessStatistics(@RequestParam String token, @RequestParam String subSysAlias, @RequestParam String remark) {
        System.out.println(token == null ? "\u65e0\u540d\u6c0f" : token);
        System.out.println(subSysAlias == null ? "\u65e0\u5f53\u524d\u9879\u76ee\u540d" : subSysAlias);
        System.out.println(remark == null ? "\u65e0\u8bbf\u95ee\u8def\u5f84" : remark);
        return R.ok();
    }
}

