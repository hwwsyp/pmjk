package com.tpfh.fintech.modules.pmjk.financialvariety.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.tpfh.fintech.common.utils.PageUtils;
import com.tpfh.fintech.modules.pmjk.financialvariety.dao.FinancialVarietyDao;
import com.tpfh.fintech.modules.pmjk.financialvariety.entity.FinancialVarietyEntity;
import com.tpfh.fintech.modules.pmjk.financialvariety.service.FinancialVarietyService;
import com.tpfh.fintech.modules.pmjk.financialvariety.service.SecBaseSourceService;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "financialVarietyService")
public class FinancialVarietyServiceImpl extends ServiceImpl<FinancialVarietyDao, FinancialVarietyEntity>
        implements FinancialVarietyService {

    @Autowired
    private FinancialVarietyDao financialVarietyDao;

    @Autowired
    private SecBaseSourceService secBaseSourceService;

    @Autowired
    @Lazy
    private FinancialVarietyService financialVarietyService;

    @Override
    public PageUtils queryPage(HashMap<String, Object> params) {
        Page<FinancialVarietyEntity> page = new Page<>();
        Integer pageno = Integer.parseInt(params.get("page").toString());
        Integer limit = Integer.parseInt(params.get("limit").toString());
        page.setCurrent(pageno);
        page.setSize(limit);
        page.setRecords(this.financialVarietyDao.getFinancialVarietyListForPage(page, params));
        return new PageUtils(page);
    }

    @Override
    public List<FinancialVarietyEntity> getInfoList(HashMap<String, Object> params) {
        return this.financialVarietyDao.getFinancialVarietyList(params);
    }

    @Override
    public FinancialVarietyEntity getInfoById(Long id) {
        return this.financialVarietyDao.selectById(id);
    }

    @Override
    public int syncFromSource() {
        List<FinancialVarietyEntity> sourceRows = this.secBaseSourceService.querySourceList();
        return this.financialVarietyService.persistSyncRows(sourceRows);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int persistSyncRows(List<FinancialVarietyEntity> sourceRows) {
        this.financialVarietyDao.deleteAllSynced();
        Date now = new Date();
        for (FinancialVarietyEntity row : sourceRows) {
            row.setId(null);
            row.setCreatetimestamp(now);
            row.setUpdatetimestamp(now);
            this.insert(row);
        }
        return sourceRows.size();
    }
}
