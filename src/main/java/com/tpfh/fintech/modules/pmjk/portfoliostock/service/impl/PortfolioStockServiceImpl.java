package com.tpfh.fintech.modules.pmjk.portfoliostock.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.tpfh.fintech.common.utils.PageUtils;
import com.tpfh.fintech.modules.pmjk.portfoliostock.dao.PortfolioStockDao;
import com.tpfh.fintech.modules.pmjk.portfoliostock.entity.PortfolioStockEntity;
import com.tpfh.fintech.modules.pmjk.portfoliostock.service.AiStockSourceService;
import com.tpfh.fintech.modules.pmjk.portfoliostock.service.PortfolioStockService;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "portfolioStockService")
public class PortfolioStockServiceImpl extends ServiceImpl<PortfolioStockDao, PortfolioStockEntity>
implements PortfolioStockService {

	@Autowired
	private PortfolioStockDao portfolioStockDao;

	@Autowired
	private AiStockSourceService aiStockSourceService;

	@Override
	public PageUtils queryPage(HashMap<String, Object> params) {
		Page<PortfolioStockEntity> page = new Page<>();
		Integer pageno = Integer.parseInt(params.get("page").toString());
		Integer limit = Integer.parseInt(params.get("limit").toString());
		page.setCurrent(pageno);
		page.setSize(limit);
		page.setRecords(this.portfolioStockDao.getPortfolioStockListForPage(page, params));
		return new PageUtils(page);
	}

	@Override
	public List<PortfolioStockEntity> getInfoList(HashMap<String, Object> params) {
		return this.portfolioStockDao.getPortfolioStockList(params);
	}

	@Override
	public PortfolioStockEntity getInfoById(Long id) {
		return this.portfolioStockDao.selectById(id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int syncFromSource(String stockDate) {
		if (StringUtils.isBlank(stockDate)) {
			throw new IllegalArgumentException("库存日期不能为空");
		}
		try {
			List<PortfolioStockEntity> sourceRows = this.aiStockSourceService.querySource(stockDate);
			this.portfolioStockDao.deleteByStockDate(stockDate);
			Date now = new Date();
			for (PortfolioStockEntity row : sourceRows) {
				row.setId(null);
				row.setCreatetimestamp(now);
				row.setUpdatetimestamp(now);
				this.insert(row);
			}
			return sourceRows.size();
		}catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
}
