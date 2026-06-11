package com.tpfh.fintech.modules.sys.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.tpfh.fintech.common.utils.PageUtils;
import com.tpfh.fintech.common.utils.Query;
import com.tpfh.fintech.modules.sys.dao.SysLogDao;
import com.tpfh.fintech.modules.sys.entity.SysLogEntity;
import com.tpfh.fintech.modules.sys.service.SysLogService;


@Service("sysLogService")
public class SysLogServiceImpl extends ServiceImpl<SysLogDao, SysLogEntity> implements SysLogService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String)params.get("key");
        
        List<String> orderList = new ArrayList<>();
        orderList.add("create_date");

        Page<SysLogEntity> page = this.selectPage(
            new Query<SysLogEntity>(params).getPage(),
            new EntityWrapper<SysLogEntity>().like(StringUtils.isNotBlank(key),"username", key).orderDesc(orderList)
        );

        return new PageUtils(page);
    }

}
