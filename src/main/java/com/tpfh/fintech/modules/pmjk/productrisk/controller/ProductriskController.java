/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.alibaba.fastjson.JSONObject
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
package com.tpfh.fintech.modules.pmjk.productrisk.controller;

import com.alibaba.fastjson.JSONObject;
import com.tpfh.fintech.common.annotation.SysLog;
import com.tpfh.fintech.common.utils.DateUtils;
import com.tpfh.fintech.common.utils.PageUtils;
import com.tpfh.fintech.common.utils.R;
import com.tpfh.fintech.modules.pmjk.productrisk.entity.ProductriskEntity;
import com.tpfh.fintech.modules.pmjk.productrisk.service.ProductriskService;
import com.tpfh.fintech.modules.share.template.ExcelReadDataFromFileTemplate;
import com.tpfh.fintech.modules.share.template.FileToClassMapping;
import com.tpfh.fintech.modules.share.template.ReadDataFromFileTemplate;
import com.tpfh.fintech.modules.sys.controller.AbstractController;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
@RequestMapping(value={"/pmjk/productrisk"})
public class ProductriskController
extends AbstractController {
    @Autowired
    private ProductriskService productriskService;
    @Value(value="${tpfh.filePath}")
    String filePath;

    @GetMapping(value={"/list"})
    @RequiresPermissions(value={"pmjk:productrisk:list"})
    public R list(@RequestParam HashMap<String, Object> params) {
        PageUtils page = this.productriskService.queryPage(params);
        return R.ok().put("page", (Object)page);
    }

    @GetMapping(value={"/getProductriskList"})
    @RequiresPermissions(value={"pmjk:productrisk:list"})
    public R getProductriskList(@RequestParam HashMap<String, Object> params) {
        List<ProductriskEntity> list = this.productriskService.getInfoList(params);
        return R.ok().put("result", (Object)list);
    }

    @GetMapping(value={"/info/{id}"})
    @RequiresPermissions(value={"pmjk:productrisk:info"})
    public R info(@PathVariable(value="id") Long id) {
        ProductriskEntity productriskInfo = this.productriskService.getInfoById(id);
        return R.ok().put("productriskInfo", (Object)productriskInfo);
    }

    @SysLog(value="\u65b0\u589e")
    @PostMapping(value={"/save"})
    @RequiresPermissions(value={"pmjk:productrisk:save"})
    public R add(@RequestBody ProductriskEntity productriskEntity) {
        this.productriskService.insert(productriskEntity);
        return R.ok();
    }

    @SysLog(value="\u66f4\u65b0")
    @PostMapping(value={"/update"})
    @RequiresPermissions(value={"pmjk:productrisk:update"})
    public R update(@RequestBody ProductriskEntity productriskEntity) {
        this.productriskService.updateById(productriskEntity);
        return R.ok();
    }

    @SysLog(value="\u5220\u9664")
    @PostMapping(value={"/delete"})
    @RequiresPermissions(value={"pmjk:productrisk:delete"})
    public R delete(@RequestBody List<Long> ids) {
        this.productriskService.deleteBatchIds(ids);
        return R.ok();
    }

    @SysLog(value="\u4e0a\u4f20\u6587\u4ef6")
    @PostMapping(value={"/upload"})
    public R upload(@RequestParam(value="file") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                this.saveFile(file);
                List<ProductriskEntity> list = this.parseFile(file);
                return R.ok("\u4e0a\u4f20\u6210\u529f");
            }
            catch (Exception e) {
                this.logger.error(e.getMessage(), (Throwable)e);
                return R.error(e.getMessage());
            }
        }
        return R.error("\u4e0a\u4f20\u5931\u8d25,\u6587\u4ef6\u4e3a\u7a7a");
    }

    private void saveFile(MultipartFile file) throws Exception {
        String today = DateUtils.format(new Date());
        ArrayList<String> pathLevelList = new ArrayList<String>();
        pathLevelList.add(today);
        pathLevelList.add("productrisk");
        String bankTodayUploadPath = this.filePath;
        for (String path : pathLevelList) {
            if (Files.exists(Paths.get(bankTodayUploadPath = String.valueOf(bankTodayUploadPath) + "/" + path, new String[0]), new LinkOption[0])) continue;
            Files.createDirectory(Paths.get(bankTodayUploadPath, new String[0]), new FileAttribute[0]);
        }
        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        fileName = String.valueOf(fileName.substring(0, fileName.lastIndexOf("."))) + "-" + System.currentTimeMillis() + suffixName;
        this.logger.info(Paths.get(bankTodayUploadPath, fileName).toAbsolutePath().toString());
        Files.copy(file.getInputStream(), Paths.get(bankTodayUploadPath, fileName), new CopyOption[0]);
    }

    private List<ProductriskEntity> parseFile(MultipartFile file) throws Exception {
        String text = "{'sheetNum':1,'startLineStrategyType':'NUMBER','startLineStrategyValue':2,'endLineStrategyType':'NUMBER','endLineStrategyValue':-1,'list':[{'columnNum':1,'fieldName':'XXX','classType':'String','isRequired':true},{'columnNum':2,'fieldName':'XXX','classType':'String','isRequired':true},{'columnNum':3,'fieldName':'XXX','classType':'String','isRequired':true},{'columnNum':4,'fieldName':'XXX','classType':'BigDecimal','isRequired':true},{'columnNum':5,'fieldName':'XXX','classType':'String','isRequired':true}]}";
        FileToClassMapping fileToTableMapping = (FileToClassMapping)JSONObject.parseObject((String)text, FileToClassMapping.class);
        List<ProductriskEntity> list = null;
        ExcelReadDataFromFileTemplate<ProductriskEntity> readData = new ExcelReadDataFromFileTemplate<ProductriskEntity>();
        list = ((ReadDataFromFileTemplate)readData).parseFile(file, fileToTableMapping, ProductriskEntity.class);
        return list;
    }
}

