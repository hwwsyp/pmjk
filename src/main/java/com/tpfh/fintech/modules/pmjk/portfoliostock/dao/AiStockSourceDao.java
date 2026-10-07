package com.tpfh.fintech.modules.pmjk.portfoliostock.dao;

import com.tpfh.fintech.modules.pmjk.portfoliostock.entity.PortfolioStockEntity;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AiStockSourceDao {

    List<PortfolioStockEntity> selectAiStock(@Param("stockDate") String stockDate);
}
