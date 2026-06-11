package com.tpfh.fintech.common.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.entity.TableFieldInfo;
import com.baomidou.mybatisplus.entity.TableInfo;
import com.baomidou.mybatisplus.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.SqlHelper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.MapUtils;
import com.baomidou.mybatisplus.toolkit.TableInfoHelper;

/**
 * add by owen in 20220206
 *  为了在save、update、delete时进行统一操作
 * @author Taiping
 *
 */
public class MyServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T>{
	/**
	 * add by owen in 20220209 将删除操作统一更改为对status字段的更新，如果没有status字段，则直接删除记录，说明是不重要的数据
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean deleteById(Serializable id) {
		MyStatusEntity<T> statusEntity = this.getStatusEntityById(id);
		if(statusEntity!=null && statusEntity.getIsHavingStatus()) {
			return this.updateById(statusEntity.getEntity());
		}
		return SqlHelper.delBool(baseMapper.deleteById(id));
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean deleteByMap(Map<String, Object> columnMap) {
		if (MapUtils.isEmpty(columnMap)) {
			throw new MybatisPlusException("deleteByMap columnMap is empty.");
		}
		//是否进行update操作
		boolean isUpdate = false;
		//存放处理后的
		List<T> entityList = new ArrayList<>();

		//按条件查询出所属列表
		List<T> tempList = this.selectByMap(columnMap);
		for(T entity : tempList) {
			MyStatusEntity<T> statusEntity = this.getStatusEntity(entity);
			isUpdate = statusEntity.getIsHavingStatus();
			entityList.add(statusEntity.getEntity());
		}

		if(isUpdate) {
			return this.updateBatchById(entityList);
		}

		return SqlHelper.delBool(baseMapper.deleteByMap(columnMap));
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean delete(Wrapper<T> wrapper) {

		//是否进行update操作
		boolean isUpdate = false;
		//存放处理后的
		List<T> entityList = new ArrayList<>();

		//按条件查询出所属列表
		List<T> tempList = this.selectList(wrapper);
		for(T entity : tempList) {
			MyStatusEntity<T> statusEntity = this.getStatusEntity(entity);
			isUpdate = statusEntity.getIsHavingStatus();
			entityList.add(statusEntity.getEntity());
		}

		if(isUpdate) {
			return this.updateBatchById(entityList);
		}

		return SqlHelper.delBool(baseMapper.delete(wrapper));
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean deleteBatchIds(Collection<? extends Serializable> idList) {
		boolean isUpdate = false;
		List<T> entityList = new ArrayList<>();
		for(Serializable id : idList) {
			MyStatusEntity<T> statusEntity = this.getStatusEntityById(id);
			if(statusEntity!=null && statusEntity.getIsHavingStatus()) {
				//如果表包含了status字段，则走update方式
				isUpdate = true;
				entityList.add(statusEntity.getEntity());
			}
		}

		//判断是否是
		if(isUpdate) {
			return this.updateBatchById(entityList);
		}else {
			return SqlHelper.delBool(baseMapper.deleteBatchIds(idList));
		}
	}

	/**
	 * 根据主键id获取对应的主体信息，并对其status字段统一更新为DELETE数据
	 * @param id
	 * @return
	 */
	private MyStatusEntity<T> getStatusEntityById(Serializable id) {
		T entity = this.selectById(id);
		return this.getStatusEntity(entity);
	}

	/**
	 *  将实体的其status字段统一更新为DELETE数据
	 * @param id
	 * @return
	 */
	private MyStatusEntity<T> getStatusEntity(T entity) {

		MyStatusEntity<T> statusEntity = new MyStatusEntity<T>();
		statusEntity.setIsHavingStatus(false);
		statusEntity.setEntity(entity);

		if (null != entity) {
			Class<?> cls = entity.getClass();
			TableInfo tableInfo = TableInfoHelper.getTableInfo(cls);
			List<TableFieldInfo> fieldList = tableInfo.getFieldList();
			for(TableFieldInfo filedInfo : fieldList) {
				//TODO 这里规定死了，是status字段，且DELETE为删除状态
				if("status".equals(filedInfo.getProperty())) {
					MyReflectionKit.setFiledValue(entity.getClass(), entity, filedInfo.getProperty(), filedInfo.getPropertyType(), "DELETE");
					statusEntity.setIsHavingStatus(true);
				}
			}
		}

		return statusEntity;
	}
}
