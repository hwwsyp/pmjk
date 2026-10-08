package com.tpfh.fintech.modules.pmjk.cashflowdivide.service;

import com.tpfh.fintech.common.utils.PageUtils;
import com.tpfh.fintech.modules.pmjk.cashflowdivide.dto.CashflowDivideCalculateRequest;
import com.tpfh.fintech.modules.pmjk.cashflowdivide.entity.CashflowDivideBatchEntity;
import com.tpfh.fintech.modules.pmjk.cashflowdivide.entity.CashflowDivideLineEntity;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface CashflowDivideService {

    PageUtils queryBatchPage(HashMap<String, Object> params);

    Map<String, Object> calculate(CashflowDivideCalculateRequest request);

    Long saveBatch(CashflowDivideCalculateRequest request, List<CashflowDivideLineEntity> lines);

    CashflowDivideBatchEntity getBatchById(Long id);

    List<CashflowDivideLineEntity> getLinesByBatchId(Long batchId);

    /** 按理财品种 SM 代码解析产品概要中的项目简称 */
    String resolveProjectShortNameBySmCode(String smCode);
}
