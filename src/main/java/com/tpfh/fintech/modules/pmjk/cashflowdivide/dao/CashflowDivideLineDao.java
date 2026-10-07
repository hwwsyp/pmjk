package com.tpfh.fintech.modules.pmjk.cashflowdivide.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.tpfh.fintech.modules.pmjk.cashflowdivide.entity.CashflowDivideLineEntity;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CashflowDivideLineDao extends BaseMapper<CashflowDivideLineEntity> {
    List<CashflowDivideLineEntity> listByBatchId(@Param("batchId") Long batchId);
    int deleteByBatchId(@Param("batchId") Long batchId);
}
