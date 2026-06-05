package com.tpfh.fintech.modules.${prefix}.${function}.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.tpfh.fintech.modules.${prefix}.${function}.entity.${entityName}Entity;

@Mapper
public interface ${entityName}Dao extends BaseMapper<${entityName}Entity> {

	List<${entityName}Entity> get${entityName}ListForPage(Page<${entityName}Entity> page, HashMap<String, Object> params);
	
	List<${entityName}Entity> get${entityName}List(HashMap<String, Object> params);
	
	int add${entityName}(Map<String, String> map);

}