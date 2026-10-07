package com.tpfh.fintech.modules.pmjk.financialvariety.controller;

import com.tpfh.fintech.common.annotation.SysLog;
import com.tpfh.fintech.common.utils.PageUtils;
import com.tpfh.fintech.common.utils.R;
import com.tpfh.fintech.modules.pmjk.financialvariety.entity.FinancialVarietyEntity;
import com.tpfh.fintech.modules.pmjk.financialvariety.service.FinancialVarietyService;
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
@RequestMapping(value = {"/pmjk/financialvariety"})
public class FinancialVarietyController extends AbstractController {

    @Autowired
    private FinancialVarietyService financialVarietyService;

    @GetMapping(value = {"/list"})
    @RequiresPermissions(value = {"pmjk:financialvariety:list"})
    public R list(@RequestParam HashMap<String, Object> params) {
        PageUtils page = this.financialVarietyService.queryPage(params);
        return R.ok().put("page", page);
    }

    @GetMapping(value = {"/getFinancialVarietyList"})
    @RequiresPermissions(value = {"pmjk:financialvariety:list"})
    public R getFinancialVarietyList(@RequestParam HashMap<String, Object> params) {
        List<FinancialVarietyEntity> list = this.financialVarietyService.getInfoList(params);
        return R.ok().put("result", list);
    }

    @GetMapping(value = {"/info/{id}"})
    @RequiresPermissions(value = {"pmjk:financialvariety:info"})
    public R info(@PathVariable(value = "id") Long id) {
        FinancialVarietyEntity info = this.financialVarietyService.getInfoById(id);
        return R.ok().put("financialVarietyInfo", info);
    }

    @SysLog(value = "新增理财品种")
    @PostMapping(value = {"/save"})
    @RequiresPermissions(value = {"pmjk:financialvariety:save"})
    public R add(@RequestBody FinancialVarietyEntity entity) {
        Date now = new Date();
        entity.setCreatetimestamp(now);
        entity.setUpdatetimestamp(now);
        this.financialVarietyService.insert(entity);
        return R.ok().put("financialVarietyInfo", entity);
    }

    @SysLog(value = "更新理财品种")
    @PostMapping(value = {"/update"})
    @RequiresPermissions(value = {"pmjk:financialvariety:update"})
    public R update(@RequestBody FinancialVarietyEntity entity) {
        entity.setUpdatetimestamp(new Date());
        this.financialVarietyService.updateById(entity);
        return R.ok().put("financialVarietyInfo", entity);
    }

    @SysLog(value = "删除理财品种")
    @PostMapping(value = {"/delete"})
    @RequiresPermissions(value = {"pmjk:financialvariety:delete"})
    public R delete(@RequestBody List<Long> ids) {
        this.financialVarietyService.deleteBatchIds(ids);
        return R.ok();
    }

    @SysLog(value = "同步理财品种")
    @PostMapping(value = {"/sync"})
    @RequiresPermissions(value = {"pmjk:financialvariety:sync"})
    public R sync() {
        int count = this.financialVarietyService.syncFromSource();
        return R.ok().put("count", count);
    }
}
