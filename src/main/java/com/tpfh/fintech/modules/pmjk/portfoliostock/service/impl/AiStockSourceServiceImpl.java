package com.tpfh.fintech.modules.pmjk.portfoliostock.service.impl;

import com.tpfh.fintech.datasources.DataSourceNames;
import com.tpfh.fintech.datasources.annotation.DataSource;
import com.tpfh.fintech.modules.pmjk.portfoliostock.dao.AiStockSourceDao;
import com.tpfh.fintech.modules.pmjk.portfoliostock.entity.PortfolioStockEntity;
import com.tpfh.fintech.modules.pmjk.portfoliostock.service.AiStockSourceService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "aiStockSourceService")
public class AiStockSourceServiceImpl implements AiStockSourceService {

    @Autowired
    private AiStockSourceDao aiStockSourceDao;

    @Override
    @DataSource(name = DataSourceNames.VAS9)
    public List<PortfolioStockEntity> querySource(String stockDate) {
        return this.aiStockSourceDao.selectAiStock(stockDate);
    }
}
