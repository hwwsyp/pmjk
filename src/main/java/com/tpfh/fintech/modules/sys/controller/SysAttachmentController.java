/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.mapper.EntityWrapper
 *  com.baomidou.mybatisplus.mapper.Wrapper
 *  javax.servlet.ServletOutputStream
 *  javax.servlet.http.HttpServletResponse
 *  org.apache.commons.io.IOUtils
 *  org.apache.shiro.authz.annotation.RequiresPermissions
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.web.bind.annotation.GetMapping
 *  org.springframework.web.bind.annotation.PathVariable
 *  org.springframework.web.bind.annotation.PostMapping
 *  org.springframework.web.bind.annotation.RequestBody
 *  org.springframework.web.bind.annotation.RequestMapping
 *  org.springframework.web.bind.annotation.RequestParam
 *  org.springframework.web.bind.annotation.RestController
 */
package com.tpfh.fintech.modules.sys.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.tpfh.fintech.common.annotation.SysLog;
import com.tpfh.fintech.common.utils.PageUtils;
import com.tpfh.fintech.common.utils.R;
import com.tpfh.fintech.modules.sys.controller.AbstractController;
import com.tpfh.fintech.modules.sys.entity.SysAttachmentEntity;
import com.tpfh.fintech.modules.sys.service.SysAttachmentService;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value={"/sys/attachment"})
public class SysAttachmentController
extends AbstractController {
    @Autowired
    private SysAttachmentService attachmentService;

    @GetMapping(value={"/download"})
    public void download(@RequestParam(value="code") String code, HttpServletResponse response) throws IOException {
        EntityWrapper wrapper = new EntityWrapper();
        wrapper.eq("code", (Object)code);
        SysAttachmentEntity entity = (SysAttachmentEntity)this.attachmentService.selectOne((Wrapper)wrapper);
        FileInputStream is = new FileInputStream(new File(entity.getFilePath()));
        response.setHeader("content-disposition", "attachment;fileName=" + URLEncoder.encode(entity.getSrcFileName(), "UTF-8"));
        ServletOutputStream os = response.getOutputStream();
        IOUtils.copy((InputStream)is, (OutputStream)os);
        IOUtils.closeQuietly((InputStream)is);
        IOUtils.closeQuietly((OutputStream)os);
    }

    @GetMapping(value={"/list"})
    @RequiresPermissions(value={"sys:attachment:list"})
    public R list(@RequestParam HashMap<String, Object> params) {
        PageUtils page = this.attachmentService.queryPage(params);
        return R.ok().put("page", (Object)page);
    }

    @GetMapping(value={"/getAttachmentList"})
    @RequiresPermissions(value={"sys:attachment:list"})
    public R getAttachmentList(@RequestParam HashMap<String, Object> params) {
        List<SysAttachmentEntity> list = this.attachmentService.getInfoList(params);
        return R.ok().put("result", (Object)list);
    }

    @GetMapping(value={"/getAttachmentInfoByCode/{code}"})
    @RequiresPermissions(value={"sys:attachment:info"})
    public R getAttachmentInfoByCode(@PathVariable(value="code") String code) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("code", code);
        List<SysAttachmentEntity> infoList = this.attachmentService.getInfoList(params);
        if (infoList != null && infoList.size() > 0) {
            return R.ok().put("attachmentInfo", (Object)infoList.get(0));
        }
        return R.error("\u6ca1\u6709\u5bf9\u5e94\u7684\u9644\u4ef6\u4fe1\u606f");
    }

    @GetMapping(value={"/info/{id}"})
    @RequiresPermissions(value={"sys:attachment:info"})
    public R info(@PathVariable(value="id") Long id) {
        SysAttachmentEntity attachmentInfo = this.attachmentService.getInfoById(id);
        return R.ok().put("attachmentInfo", (Object)attachmentInfo);
    }

    @SysLog(value="\u65b0\u589e")
    @PostMapping(value={"/save"})
    @RequiresPermissions(value={"sys:attachment:save"})
    public R add(@RequestBody SysAttachmentEntity SysAttachmentEntity2) {
        this.attachmentService.insert(SysAttachmentEntity2);
        return R.ok();
    }

    @SysLog(value="\u66f4\u65b0")
    @PostMapping(value={"/update"})
    @RequiresPermissions(value={"sys:attachment:update"})
    public R update(@RequestBody SysAttachmentEntity SysAttachmentEntity2) {
        this.attachmentService.updateById(SysAttachmentEntity2);
        return R.ok();
    }

    @SysLog(value="\u5220\u9664")
    @PostMapping(value={"/delete"})
    @RequiresPermissions(value={"sys:attachment:delete"})
    public R delete(@RequestBody List<Long> ids) {
        this.attachmentService.deleteBatchIds(ids);
        return R.ok();
    }
}

