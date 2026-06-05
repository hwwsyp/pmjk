/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.web.bind.annotation.PathVariable
 *  org.springframework.web.bind.annotation.RequestBody
 *  org.springframework.web.bind.annotation.RequestMapping
 *  org.springframework.web.bind.annotation.RequestParam
 *  org.springframework.web.bind.annotation.RestController
 */
package com.tpfh.fintech.modules.sys.controller;

import com.tpfh.fintech.common.annotation.SysLog;
import com.tpfh.fintech.common.utils.R;
import com.tpfh.fintech.common.validator.ValidatorUtils;
import com.tpfh.fintech.modules.sys.controller.AbstractController;
import com.tpfh.fintech.modules.sys.entity.SysConfigEntity;
import com.tpfh.fintech.modules.sys.service.SysConfigService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value={"/sys/config"})
public class SysConfigController
extends AbstractController {
    @Autowired
    private SysConfigService sysConfigService;

    @RequestMapping(value={"/list"})
    public List<SysConfigEntity> list(@RequestParam Map<String, Object> params) {
        List<SysConfigEntity> configList = this.sysConfigService.queryList(params);
        return configList;
    }

    @RequestMapping(value={"/listByParent"})
    public List<SysConfigEntity> listByParent(@RequestParam Map<String, Object> params) {
        return this.sysConfigService.queryListByParent(params);
    }

    @RequestMapping(value={"/queryValueByKey"})
    public R queryValueByKey(@RequestParam String key) {
        SysConfigEntity sysConfigEntity = this.sysConfigService.queryValueByKey(key);
        return R.ok().put("value", (Object)sysConfigEntity.getValue());
    }

    @RequestMapping(value={"/query"})
    public R queryByKey(@RequestParam String key) {
        List<SysConfigEntity> configList = this.sysConfigService.queryByKey(key);
        return R.ok().put("configList", (Object)configList);
    }

    @RequestMapping(value={"/queryParent"})
    public R queryParent(String id, String key) {
        List<SysConfigEntity> configList = this.sysConfigService.queryParent(id, key);
        return R.ok().put("configList", (Object)configList);
    }

    @RequestMapping(value={"/select"})
    public R select(@RequestParam Map<String, Object> params) {
        List<SysConfigEntity> configList = this.sysConfigService.queryList(params);
        SysConfigEntity root = new SysConfigEntity();
        root.setId(0L);
        root.setKey("0");
        root.setValue("\u4e00\u7ea7\u53c2\u6570");
        root.setParentId(-1L);
        root.setOpen(true);
        configList.add(root);
        return R.ok().put("configList", (Object)configList);
    }

    @RequestMapping(value={"/info/{id}"})
    public R info(@PathVariable(value="id") Long id) {
        SysConfigEntity config = this.sysConfigService.queryObject(id);
        return R.ok().put("config", (Object)config);
    }

    @SysLog(value="\u4fdd\u5b58\u53c2\u6570")
    @RequestMapping(value={"/save"})
    public R save(@RequestBody SysConfigEntity config) {
        ValidatorUtils.validateEntity(config, new Class[0]);
        this.sysConfigService.save(config);
        return R.ok();
    }

    @SysLog(value="\u4fee\u6539\u53c2\u6570")
    @RequestMapping(value={"/update"})
    public R update(@RequestBody SysConfigEntity config) {
        ValidatorUtils.validateEntity(config, new Class[0]);
        this.sysConfigService.update(config);
        return R.ok();
    }

    @SysLog(value="\u5220\u9664\u53c2\u6570")
    @RequestMapping(value={"/delete"})
    public R delete(@RequestBody Long[] ids) {
        this.sysConfigService.deleteBatch(ids);
        return R.ok();
    }
}

