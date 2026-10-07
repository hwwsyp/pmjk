package com.tpfh.fintech.modules.pmjk.portfoliostock.controller;

import com.tpfh.fintech.common.annotation.SysLog;
import com.tpfh.fintech.common.utils.PageUtils;
import com.tpfh.fintech.common.utils.R;
import com.tpfh.fintech.modules.pmjk.portfoliostock.entity.PortfolioStockEntity;
import com.tpfh.fintech.modules.pmjk.portfoliostock.service.PortfolioStockService;
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
@RequestMapping(value = {"/pmjk/portfoliostock"})
public class PortfolioStockController extends AbstractController {

    @Autowired
    private PortfolioStockService portfolioStockService;

    @GetMapping(value = {"/list"})
    @RequiresPermissions(value = {"pmjk:portfoliostock:list"})
    public R list(@RequestParam HashMap<String, Object> params) {
        PageUtils page = this.portfolioStockService.queryPage(params);
        return R.ok().put("page", page);
    }

    @GetMapping(value = {"/getPortfolioStockList"})
    @RequiresPermissions(value = {"pmjk:portfoliostock:list"})
    public R getPortfolioStockList(@RequestParam HashMap<String, Object> params) {
        List<PortfolioStockEntity> list = this.portfolioStockService.getInfoList(params);
        return R.ok().put("result", list);
    }

    @GetMapping(value = {"/info/{id}"})
    @RequiresPermissions(value = {"pmjk:portfoliostock:info"})
    public R info(@PathVariable(value = "id") Long id) {
        PortfolioStockEntity info = this.portfolioStockService.getInfoById(id);
        return R.ok().put("portfolioStockInfo", info);
    }

    @SysLog(value = "新增组合库存")
    @PostMapping(value = {"/save"})
    @RequiresPermissions(value = {"pmjk:portfoliostock:save"})
    public R add(@RequestBody PortfolioStockEntity entity) {
        Date now = new Date();
        entity.setCreatetimestamp(now);
        entity.setUpdatetimestamp(now);
        this.portfolioStockService.insert(entity);
        return R.ok().put("portfolioStockInfo", entity);
    }

    @SysLog(value = "更新组合库存")
    @PostMapping(value = {"/update"})
    @RequiresPermissions(value = {"pmjk:portfoliostock:update"})
    public R update(@RequestBody PortfolioStockEntity entity) {
        entity.setUpdatetimestamp(new Date());
        this.portfolioStockService.updateById(entity);
        return R.ok().put("portfolioStockInfo", entity);
    }

    @SysLog(value = "删除组合库存")
    @PostMapping(value = {"/delete"})
    @RequiresPermissions(value = {"pmjk:portfoliostock:delete"})
    public R delete(@RequestBody List<Long> ids) {
        this.portfolioStockService.deleteBatchIds(ids);
        return R.ok();
    }

    @SysLog(value = "同步组合库存")
    @PostMapping(value = {"/sync"})
    @RequiresPermissions(value = {"pmjk:portfoliostock:sync"})
    public R sync(@RequestBody HashMap<String, String> body) {
        String stockDate = body.get("stockDate");
        int count = this.portfolioStockService.syncFromSource(stockDate);
        return R.ok().put("count", count);
    }
}
