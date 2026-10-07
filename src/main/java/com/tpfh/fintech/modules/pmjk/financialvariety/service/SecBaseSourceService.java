package com.tpfh.fintech.modules.pmjk.financialvariety.service;

import com.tpfh.fintech.modules.pmjk.financialvariety.entity.FinancialVarietyEntity;
import java.util.List;

public interface SecBaseSourceService {

    List<FinancialVarietyEntity> querySourceList();
}
