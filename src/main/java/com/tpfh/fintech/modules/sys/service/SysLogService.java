package com.tpfh.fintech.modules.sys.service;


import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.tpfh.fintech.common.utils.PageUtils;
import com.tpfh.fintech.modules.sys.entity.SysLogEntity;


/**
 * 系统日志
 * 
 * @author tpfh
 * @email tpfh@tpfh.com
 * @date 2017-03-08 10:40:56
 */
public interface SysLogService extends IService<SysLogEntity> {

    PageUtils queryPage(Map<String, Object> params);


}
