package com.tpfh.fintech.modules.${prefix}.${function}.service;

import java.util.HashMap;
import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.tpfh.fintech.common.utils.PageUtils;
import com.tpfh.fintech.modules.${prefix}.${function}.entity.${entityName}Entity;

public interface ${entityName}Service extends IService<${entityName}Entity>{
	
	PageUtils queryPage(HashMap<String, Object> params);
	
	${entityName}Entity getInfoById(Long id);

	List<${entityName}Entity> getInfoList(HashMap<String, Object> params);
}
