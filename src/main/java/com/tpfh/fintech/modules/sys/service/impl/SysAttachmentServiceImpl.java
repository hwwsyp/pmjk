package com.tpfh.fintech.modules.sys.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.tpfh.fintech.common.utils.PageUtils;
import com.tpfh.fintech.modules.sys.dao.SysAttachmentDao;
import com.tpfh.fintech.modules.sys.entity.SysAttachmentEntity;
import com.tpfh.fintech.modules.sys.service.SysAttachmentService;

@Service("attachmentService")
public class SysAttachmentServiceImpl extends ServiceImpl<SysAttachmentDao, SysAttachmentEntity> implements SysAttachmentService {
	
	@Autowired
	private SysAttachmentDao sysAttachmentDao;

	@Override
	public PageUtils queryPage(HashMap<String, Object> params) {
		Page<SysAttachmentEntity> page = new Page<SysAttachmentEntity>();
		Integer pageno = Integer.parseInt(params.get("page").toString());
		Integer limit = Integer.parseInt(params.get("limit").toString());
		page.setCurrent(pageno.intValue());
		page.setSize(limit.intValue());
		page.setRecords(sysAttachmentDao.getAttachmentListForPage(page, params));

		return new PageUtils(page);
	}

	@Override
	public SysAttachmentEntity getInfoById(Long id) {
		return baseMapper.selectById(id);
	}

	@Override
	public List<SysAttachmentEntity> getInfoList(HashMap<String, Object> params) {
		return baseMapper.getAttachmentList(params);
	}

}
