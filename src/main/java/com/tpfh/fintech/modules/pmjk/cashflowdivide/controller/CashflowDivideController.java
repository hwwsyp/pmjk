package com.tpfh.fintech.modules.pmjk.cashflowdivide.controller;

import com.tpfh.fintech.common.annotation.SysLog;
import com.tpfh.fintech.common.utils.PageUtils;
import com.tpfh.fintech.common.utils.R;
import com.tpfh.fintech.modules.pmjk.cashflowdivide.dto.CashflowDivideCalculateRequest;
import com.tpfh.fintech.modules.pmjk.cashflowdivide.entity.CashflowDivideLineEntity;
import com.tpfh.fintech.modules.pmjk.cashflowdivide.service.CashflowDivideService;
import com.tpfh.fintech.modules.sys.controller.AbstractController;
import java.util.HashMap;
import java.util.List;
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
@RequestMapping("/pmjk/cashflowdivide")
public class CashflowDivideController extends AbstractController {

    @Autowired
    private CashflowDivideService cashflowDivideService;

    @GetMapping("/list")
    @RequiresPermissions("pmjk:cashflowdivide:list")
    public R list(@RequestParam HashMap<String, Object> params) {
        PageUtils page = this.cashflowDivideService.queryBatchPage(params);
        return R.ok().put("page", page);
    }

    @GetMapping("/lines/{batchId}")
    @RequiresPermissions("pmjk:cashflowdivide:list")
    public R lines(@PathVariable Long batchId) {
        List<CashflowDivideLineEntity> lines = this.cashflowDivideService.getLinesByBatchId(batchId);
        return R.ok().put("lines", lines);
    }

    @PostMapping("/calculate")
    @RequiresPermissions("pmjk:cashflowdivide:calculate")
    public R calculate(@RequestBody CashflowDivideCalculateRequest request) {
        Map<String, Object> result = this.cashflowDivideService.calculate(request);
        return R.ok(result);
    }

    @SysLog("保存现金流分配")
    @PostMapping("/save")
    @RequiresPermissions("pmjk:cashflowdivide:save")
    public R save(@RequestBody CashflowDivideCalculateRequest request) {
        Long batchId = this.cashflowDivideService.saveBatch(request, null);
        return R.ok().put("batchId", batchId);
    }
}
