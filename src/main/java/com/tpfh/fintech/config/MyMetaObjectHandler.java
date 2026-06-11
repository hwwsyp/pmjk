package com.tpfh.fintech.config;

import java.util.Date;

import org.apache.ibatis.reflection.MetaObject;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import com.tpfh.fintech.common.utils.ShiroUtils;

/**
 * 自动填充 修改创建时间与人
 * @author tpfh
 *
 */
public class MyMetaObjectHandler extends MetaObjectHandler {

	
	@Override
    public void insertFill(MetaObject metaObject) {
        Date newDate = new Date();
        // 测试下划线
        Object createTime = getFieldValByName("createTime", metaObject);//mybatis-plus版本2.0.9+
        Object createUser = getFieldValByName("createUser", metaObject);
        if (createTime == null) {
            setFieldValByName("createTime", newDate, metaObject);//mybatis-plus版本2.0.9+
        }
        if (createUser == null) {
            setFieldValByName("createUser", ShiroUtils.getUserId(), metaObject);//mybatis-plus版本2.0.9+
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {

        //mybatis-plus版本2.0.9+
        Date newDate = new Date();
        setFieldValByName("updateTime", newDate, metaObject);
        setFieldValByName("updateUser", ShiroUtils.getUserId(), metaObject);
    }
}
