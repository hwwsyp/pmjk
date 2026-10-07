package com.tpfh.fintech.modules.pmjk.cashflowdivide.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.tpfh.fintech.modules.pmjk.cashflowdivide.entity.CashflowDivideBatchEntity;
import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CashflowDivideBatchDao extends BaseMapper<CashflowDivideBatchEntity> {
    List<CashflowDivideBatchEntity> getBatchListForPage(Page<CashflowDivideBatchEntity> page, HashMap<String, Object> params);
}
