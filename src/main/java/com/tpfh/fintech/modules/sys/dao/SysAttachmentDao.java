package com.tpfh.fintech.modules.sys.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.tpfh.fintech.modules.sys.entity.SysAttachmentEntity;

@Mapper
public interface SysAttachmentDao extends BaseMapper<SysAttachmentEntity> {

	List<SysAttachmentEntity> getAttachmentListForPage(Page<SysAttachmentEntity> page, HashMap<String, Object> params);
	
	List<SysAttachmentEntity> getAttachmentList(HashMap<String, Object> params);
	
	int addAttachment(Map<String, String> map);

}