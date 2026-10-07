package com.tpfh.fintech.modules.pmjk.portfoliostock.service;

import com.baomidou.mybatisplus.service.IService;
import com.tpfh.fintech.common.utils.PageUtils;
import com.tpfh.fintech.modules.pmjk.portfoliostock.entity.PortfolioStockEntity;
import java.util.HashMap;
import java.util.List;

public interface PortfolioStockService extends IService<PortfolioStockEntity> {

    PageUtils queryPage(HashMap<String, Object> params);

    List<PortfolioStockEntity> getInfoList(HashMap<String, Object> params);

    PortfolioStockEntity getInfoById(Long id);

    int syncFromSource(String stockDate);

    int persistSyncRows(String stockDate, List<PortfolioStockEntity> sourceRows);
}
