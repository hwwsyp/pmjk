/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.servlet.http.HttpServletRequest
 *  javax.servlet.http.HttpServletResponse
 *  org.apache.shiro.authz.annotation.RequiresPermissions
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.beans.factory.annotation.Value
 *  org.springframework.web.bind.annotation.GetMapping
 *  org.springframework.web.bind.annotation.PathVariable
 *  org.springframework.web.bind.annotation.PostMapping
 *  org.springframework.web.bind.annotation.RequestBody
 *  org.springframework.web.bind.annotation.RequestMapping
 *  org.springframework.web.bind.annotation.RequestParam
 *  org.springframework.web.bind.annotation.RestController
 *  org.springframework.web.multipart.MultipartFile
 */
package com.tpfh.fintech.modules.pmjk.productAttachment.controller;

import com.tpfh.fintech.common.annotation.SysLog;
import com.tpfh.fintech.common.utils.DateUtils;
import com.tpfh.fintech.common.utils.PageUtils;
import com.tpfh.fintech.common.utils.R;
import com.tpfh.fintech.common.utils.ShiroUtils;
import com.tpfh.fintech.modules.pmjk.productAttachment.entity.ProductAttachmentEntity;
import com.tpfh.fintech.modules.pmjk.productAttachment.service.ProductAttachmentService;
import com.tpfh.fintech.modules.sys.controller.AbstractController;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value={"/pmjk/productAttachment"})
public class ProductAttachmentController
extends AbstractController {
    @Autowired
    private ProductAttachmentService productAttachmentService;
    @Value(value="${tpfh.filePath}")
    String filePath;

    @GetMapping(value={"/list"})
    @RequiresPermissions(value={"pmjk:productAttachment:list"})
    public R list(@RequestParam HashMap<String, Object> params) {
        PageUtils page = this.productAttachmentService.queryPage(params);
        return R.ok().put("page", (Object)page);
    }

    @GetMapping(value={"/getProductAttachmentList"})
    @RequiresPermissions(value={"pmjk:productAttachment:list"})
    public R getProductAttachmentList(@RequestParam HashMap<String, Object> params) {
        List<ProductAttachmentEntity> list = this.productAttachmentService.getInfoList(params);
        return R.ok().put("result", (Object)list);
    }

    @GetMapping(value={"/info/{id}"})
    @RequiresPermissions(value={"pmjk:productAttachment:info"})
    public R info(@PathVariable(value="id") Long id) {
        ProductAttachmentEntity productAttachmentInfo = this.productAttachmentService.getInfoById(id);
        return R.ok().put("productAttachmentInfo", (Object)productAttachmentInfo);
    }

    @SysLog(value="\u65b0\u589e")
    @PostMapping(value={"/save"})
    @RequiresPermissions(value={"pmjk:productAttachment:save"})
    public R add(@RequestBody ProductAttachmentEntity productAttachmentEntity) {
        this.productAttachmentService.insert(productAttachmentEntity);
        return R.ok();
    }

    @SysLog(value="\u66f4\u65b0")
    @PostMapping(value={"/update"})
    @RequiresPermissions(value={"pmjk:productAttachment:update"})
    public R update(@RequestBody ProductAttachmentEntity productAttachmentEntity) {
        this.productAttachmentService.updateById(productAttachmentEntity);
        return R.ok();
    }

    @SysLog(value="\u5220\u9664")
    @PostMapping(value={"/delete"})
    @RequiresPermissions(value={"pmjk:productAttachment:delete"})
    public R delete(@RequestBody List<Long> ids) {
        this.productAttachmentService.deleteBatchIds(ids);
        return R.ok();
    }

    @SysLog(value="\u4e0b\u8f7d\u9644\u4ef6\u6587\u4ef6")
    @GetMapping(value={"/download"})
    @RequiresPermissions(value={"pmjk:productAttachment:list"})
    public void download(@RequestParam(value="id") Long id, HttpServletRequest request, HttpServletResponse response) {
        try {
            int len;
            ProductAttachmentEntity entity = this.productAttachmentService.getInfoById(id);
            String filepath = entity.getFilepath();
            FileInputStream fileInputStream = new FileInputStream(new File(filepath));
            String srcName = entity.getSrcname();
            srcName = URLEncoder.encode(srcName, "UTF-8");
            response.setContentType("application/octet-stream;charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + srcName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
            byte[] b = new byte[100];
            while ((len = fileInputStream.read(b)) > 0) {
                response.getOutputStream().write(b, 0, len);
            }
            fileInputStream.close();
        }
        catch (Exception e) {
            this.logger.error(e.getMessage(), (Throwable)e);
        }
    }

    @SysLog(value="\u4e0a\u4f20\u6587\u4ef6")
    @PostMapping(value={"/upload"})
    public R upload(@RequestParam(value="file") MultipartFile file, String productid, String type) {
        if (!file.isEmpty()) {
            try {
                this.saveFile(file, productid, type);
                return R.ok("\u4e0a\u4f20\u6210\u529f");
            }
            catch (Exception e) {
                this.logger.error(e.getMessage(), (Throwable)e);
                return R.error(e.getMessage());
            }
        }
        return R.error("\u4e0a\u4f20\u5931\u8d25,\u6587\u4ef6\u4e3a\u7a7a");
    }

    private void saveFile(MultipartFile file, String productid, String type) throws Exception {
        String fileName;
        String today = DateUtils.format(new Date());
        ArrayList<String> pathLevelList = new ArrayList<String>();
        pathLevelList.add(today);
        pathLevelList.add("productAttachment");
        String bankTodayUploadPath = this.filePath;
        for (String path : pathLevelList) {
            if (Files.exists(Paths.get(bankTodayUploadPath = String.valueOf(bankTodayUploadPath) + "/" + path, new String[0]), new LinkOption[0])) continue;
            Files.createDirectory(Paths.get(bankTodayUploadPath, new String[0]), new FileAttribute[0]);
        }
        String srcName = fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        fileName = String.valueOf(fileName.substring(0, fileName.lastIndexOf("."))) + "-" + System.currentTimeMillis() + suffixName;
        this.logger.info(Paths.get(bankTodayUploadPath, fileName).toAbsolutePath().toString());
        Files.copy(file.getInputStream(), Paths.get(bankTodayUploadPath, fileName), new CopyOption[0]);
        ProductAttachmentEntity entity = new ProductAttachmentEntity();
        entity.setProductid(productid);
        entity.setType(type);
        entity.setSrcname(srcName);
        entity.setDestname(fileName);
        entity.setFilepath(Paths.get(bankTodayUploadPath, fileName).toString());
        entity.setUploaduser(ShiroUtils.getUserNameEvenError());
        entity.setUploadtime(new Date());
        this.productAttachmentService.insert(entity);
    }
}

