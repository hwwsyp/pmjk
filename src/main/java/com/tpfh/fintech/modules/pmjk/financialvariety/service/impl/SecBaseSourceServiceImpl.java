package com.tpfh.fintech.modules.pmjk.financialvariety.service.impl;

import com.tpfh.fintech.datasources.DataSourceNames;
import com.tpfh.fintech.datasources.annotation.DataSource;
import com.tpfh.fintech.modules.pmjk.financialvariety.dao.SecBaseSourceDao;
import com.tpfh.fintech.modules.pmjk.financialvariety.entity.FinancialVarietyEntity;
import com.tpfh.fintech.modules.pmjk.financialvariety.service.SecBaseSourceService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "secBaseSourceService")
public class SecBaseSourceServiceImpl implements SecBaseSourceService {

    @Autowired
    private SecBaseSourceDao secBaseSourceDao;

    @Override
    @DataSource(name = DataSourceNames.VAS9)
    public List<FinancialVarietyEntity> querySourceList() {
        return this.secBaseSourceDao.selectSecBaseList();
    }
}
