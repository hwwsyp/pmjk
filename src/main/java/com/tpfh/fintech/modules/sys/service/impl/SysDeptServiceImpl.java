package com.tpfh.fintech.modules.sys.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.tpfh.fintech.common.utils.PageUtils;
import com.tpfh.fintech.common.utils.Query;
import com.tpfh.fintech.modules.sys.dao.SysDeptDao;
import com.tpfh.fintech.modules.sys.entity.SysDeptEntity;
import com.tpfh.fintech.modules.sys.service.SysDeptService;



@Service("sysDeptService")
public class SysDeptServiceImpl extends ServiceImpl<SysDeptDao, SysDeptEntity> implements SysDeptService {
	
	@Transactional(rollbackFor = Exception.class)
    @Override
    public boolean insert(SysDeptEntity entity) {
        baseMapper.insert(entity);
        
        //如果存在父机构
        if(entity.getParentId()!=null) {
        	SysDeptEntity parentEntity = baseMapper.selectById(entity.getParentId());
        	entity.setPath(parentEntity.getPath() + ",$" + entity.getDeptId() + "$");//设置机构之间的层级关系
        }else {
        	entity.setPath("$"+ entity.getDeptId() +"$");
        }

        //基于id，更新path值
        SysDeptEntity tempEntity = new SysDeptEntity();
        tempEntity.setDeptId(entity.getDeptId());
        tempEntity.setPath(entity.getPath());
        baseMapper.updateById(tempEntity);
        
        return true;
    }
	
	@Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateById(SysDeptEntity entity) {
        baseMapper.updateById(entity);
        
        //如果存在父机构
        if(entity.getParentId()!=null) {
        	SysDeptEntity parentEntity = baseMapper.selectById(entity.getParentId());
        	entity.setPath(parentEntity.getPath() + ",$" + entity.getDeptId() + "$");//设置机构之间的层级关系
        }else {
        	entity.setPath("$"+ entity.getDeptId() +"$");
        }
        
        SysDeptEntity tempEntity = new SysDeptEntity();
        tempEntity.setDeptId(entity.getDeptId());
        tempEntity.setPath(entity.getPath());
        baseMapper.updateById(entity);
        
        return true;
    }

	@Override
	public List<String> queryDetpIdList(Integer parentId) {
		return baseMapper.queryDetpIdList(parentId);
	}

	@Override
	public List<SysDeptEntity> getSubDeptList(Integer deptId) {
		EntityWrapper<SysDeptEntity> wrapper = new EntityWrapper<SysDeptEntity>();
		wrapper.eq("parent_id", deptId);
		wrapper.eq("del_flag", 0);
		wrapper.orderBy("order_num");
		return baseMapper.selectList(wrapper);
	}

	@Override
	public List<SysDeptEntity> queryList(SysDeptEntity sysDeptEntity) {
		return baseMapper.queryList(sysDeptEntity);
	}

	@Override
	public SysDeptEntity queryObject(String deptId) {
		return baseMapper.queryObject(deptId);
	}

	@Override
	public PageUtils queryPage(HashMap<String, Object> params) {
		String key = (String)params.get("key");

		Page<SysDeptEntity> page = this.selectPage(
				new Query<SysDeptEntity>(params).getPage(),
				new EntityWrapper<SysDeptEntity>()
					.like(StringUtils.isNotBlank(key),"parentId", key)
					.eq("delFlag", 0)
		);

		return new PageUtils(page);
	}

	@Override
	public List<SysDeptEntity> getDeptsList() {
		List<SysDeptEntity> deptsList= baseMapper.getDeptsList();
		return deptsList;
	}

	@Override
	public List<SysDeptEntity> queryList(Long userId) {
		return baseMapper.queryList(userId);
	}

}
