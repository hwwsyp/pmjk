package com.tpfh.fintech.modules.pmjk.portfoliostock.service;

import com.tpfh.fintech.modules.pmjk.portfoliostock.entity.PortfolioStockEntity;
import java.util.List;

public interface AiStockSourceService {

    List<PortfolioStockEntity> querySource(String stockDate);
}
