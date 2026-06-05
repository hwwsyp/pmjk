package com.tpfh.fintech.modules.${prefix}.${function}.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.tpfh.fintech.common.utils.PageUtils;
import com.tpfh.fintech.modules.${prefix}.${function}.dao.${entityName}Dao;
import com.tpfh.fintech.modules.${prefix}.${function}.entity.${entityName}Entity;
import com.tpfh.fintech.modules.${prefix}.${function}.service.${entityName}Service;

@Service("${function}Service")
public class ${entityName}ServiceImpl extends ServiceImpl<${entityName}Dao, ${entityName}Entity> implements ${entityName}Service {
	
	@Autowired
	private ${entityName}Dao ${function}Dao;

	@Override
	public PageUtils queryPage(HashMap<String, Object> params) {
		Page<${entityName}Entity> page = new Page<${entityName}Entity>();
		Integer pageno = Integer.parseInt(params.get("page").toString());
		Integer limit = Integer.parseInt(params.get("limit").toString());
		page.setCurrent(pageno.intValue());
		page.setSize(limit.intValue());
		page.setRecords(${function}Dao.get${entityName}ListForPage(page, params));

		return new PageUtils(page);
	}

	@Override
	public ${entityName}Entity getInfoById(Long id) {
		return baseMapper.selectById(id);
	}

	@Override
	public List<${entityName}Entity> getInfoList(HashMap<String, Object> params) {
		return baseMapper.get${entityName}List(params);
	}

}
