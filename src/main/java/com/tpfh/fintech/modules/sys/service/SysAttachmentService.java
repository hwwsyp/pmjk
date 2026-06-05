/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.service.IService
 */
package com.tpfh.fintech.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.tpfh.fintech.common.utils.PageUtils;
import com.tpfh.fintech.modules.sys.entity.SysAttachmentEntity;
import java.util.HashMap;
import java.util.List;

public interface SysAttachmentService
extends IService<SysAttachmentEntity> {
    public PageUtils queryPage(HashMap<String, Object> var1);

    public SysAttachmentEntity getInfoById(Long var1);

    public List<SysAttachmentEntity> getInfoList(HashMap<String, Object> var1);
}

