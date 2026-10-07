package com.tpfh.fintech.modules.pmjk.portfoliostock.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.tpfh.fintech.modules.pmjk.portfoliostock.entity.PortfolioStockEntity;
import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PortfolioStockDao extends BaseMapper<PortfolioStockEntity> {

    List<PortfolioStockEntity> getPortfolioStockListForPage(Page<PortfolioStockEntity> page, HashMap<String, Object> params);

    List<PortfolioStockEntity> getPortfolioStockList(HashMap<String, Object> params);

    int deleteByStockDate(@Param("stockDate") String stockDate);
}
