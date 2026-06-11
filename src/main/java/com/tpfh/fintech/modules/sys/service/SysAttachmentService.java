package com.tpfh.fintech.modules.sys.service;

import java.util.HashMap;
import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.tpfh.fintech.common.utils.PageUtils;
import com.tpfh.fintech.modules.sys.entity.SysAttachmentEntity;

public interface SysAttachmentService extends IService<SysAttachmentEntity>{
	
	PageUtils queryPage(HashMap<String, Object> params);
	
	SysAttachmentEntity getInfoById(Long id);

	List<SysAttachmentEntity> getInfoList(HashMap<String, Object> params);
}
