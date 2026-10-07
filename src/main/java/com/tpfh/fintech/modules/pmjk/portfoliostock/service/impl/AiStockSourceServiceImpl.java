package com.tpfh.fintech.modules.pmjk.portfoliostock.service.impl;

import com.tpfh.fintech.datasources.DataSourceNames;
import com.tpfh.fintech.datasources.DynamicDataSource;
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
    public List<PortfolioStockEntity> querySource(String stockDate) throws Exception{
    	try {
    		//手动临时切换数据源
			DynamicDataSource.setDataSource(DataSourceNames.VAS9);//指定当前线程使用的数据源
			return this.aiStockSourceDao.selectAiStock(stockDate);
    	}catch (Exception e) {
			// TODO: handle exception
    		throw e;
		}finally {
			DynamicDataSource.clearDataSource();//清除当前线程使用的数据源，回归默认数据源
		}
        
    }
}
