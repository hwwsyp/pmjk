package com.tpfh.fintech.modules.pmjk.financialvariety.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.tpfh.fintech.modules.pmjk.financialvariety.entity.FinancialVarietyEntity;
import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FinancialVarietyDao extends BaseMapper<FinancialVarietyEntity> {

    List<FinancialVarietyEntity> getFinancialVarietyListForPage(Page<FinancialVarietyEntity> page, HashMap<String, Object> params);

    List<FinancialVarietyEntity> getFinancialVarietyList(HashMap<String, Object> params);

    int deleteAllSynced();
}
