/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.mapper.EntityWrapper
 *  com.baomidou.mybatisplus.plugins.Page
 *  com.baomidou.mybatisplus.service.impl.ServiceImpl
 *  org.apache.commons.lang.StringUtils
 *  org.springframework.stereotype.Service
 */
package com.tpfh.fintech.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.tpfh.fintech.common.utils.PageUtils;
import com.tpfh.fintech.common.utils.Query;
import com.tpfh.fintech.modules.sys.dao.SysLogDao;
import com.tpfh.fintech.modules.sys.entity.SysLogEntity;
import com.tpfh.fintech.modules.sys.service.SysLogService;
import java.util.ArrayList;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

@Service(value="sysLogService")
public class SysLogServiceImpl
extends ServiceImpl<SysLogDao, SysLogEntity>
implements SysLogService {
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String)params.get("key");
        ArrayList<String> orderList = new ArrayList<String>();
        orderList.add("create_date");
        Page page = this.selectPage(new Query(params).getPage(), new EntityWrapper().like(StringUtils.isNotBlank((String)key), "username", key).orderDesc(orderList));
        return new PageUtils(page);
    }
}

