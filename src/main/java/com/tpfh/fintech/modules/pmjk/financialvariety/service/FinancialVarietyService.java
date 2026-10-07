package com.tpfh.fintech.modules.pmjk.financialvariety.service;

import com.baomidou.mybatisplus.service.IService;
import com.tpfh.fintech.common.utils.PageUtils;
import com.tpfh.fintech.modules.pmjk.financialvariety.entity.FinancialVarietyEntity;
import java.util.HashMap;
import java.util.List;

public interface FinancialVarietyService extends IService<FinancialVarietyEntity> {

    PageUtils queryPage(HashMap<String, Object> params);

    List<FinancialVarietyEntity> getInfoList(HashMap<String, Object> params);

    FinancialVarietyEntity getInfoById(Long id);

    int syncFromSource();

    int persistSyncRows(List<FinancialVarietyEntity> sourceRows);
}
