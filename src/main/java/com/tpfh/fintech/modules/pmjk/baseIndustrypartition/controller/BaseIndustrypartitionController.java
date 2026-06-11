/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.alibaba.fastjson2.JSONObject
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
package com.tpfh.fintech.modules.pmjk.baseIndustrypartition.controller;

import com.alibaba.fastjson2.JSONObject;
import com.tpfh.fintech.common.annotation.SysLog;
import com.tpfh.fintech.common.utils.DateUtils;
import com.tpfh.fintech.common.utils.PageUtils;
import com.tpfh.fintech.common.utils.R;
import com.tpfh.fintech.modules.pmjk.baseIndustrypartition.entity.BaseIndustrypartitionEntity;
import com.tpfh.fintech.modules.pmjk.baseIndustrypartition.service.BaseIndustrypartitionService;
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
@RequestMapping(value={"/pmjk/baseIndustrypartition"})
public class BaseIndustrypartitionController
extends AbstractController {
    @Autowired
    private BaseIndustrypartitionService baseIndustrypartitionService;
    @Value(value="${tpfh.filePath}")
    String filePath;

    @GetMapping(value={"/list"})
    @RequiresPermissions(value={"pmjk:baseIndustrypartition:list"})
    public R list(@RequestParam HashMap<String, Object> params) {
        PageUtils page = this.baseIndustrypartitionService.queryPage(params);
        return R.ok().put("page", (Object)page);
    }

    @GetMapping(value={"/listForSelectDialogPage"})
    @RequiresPermissions(value={"pmjk:baseIndustrypartition:list"})
    public R listForSelectDialogPage(@RequestParam HashMap<String, Object> params) {
        PageUtils page = this.baseIndustrypartitionService.queryForSelectDialogPage(params);
        return R.ok().put("page", (Object)page);
    }

    @GetMapping(value={"/getBaseIndustrypartitionList"})
    @RequiresPermissions(value={"pmjk:baseIndustrypartition:list"})
    public R getBaseIndustrypartitionList(@RequestParam HashMap<String, Object> params) {
        List<BaseIndustrypartitionEntity> list = this.baseIndustrypartitionService.getInfoList(params);
        return R.ok().put("result", (Object)list);
    }

    @GetMapping(value={"/info/{id}"})
    @RequiresPermissions(value={"pmjk:baseIndustrypartition:info"})
    public R info(@PathVariable(value="id") Long id) {
        BaseIndustrypartitionEntity baseIndustrypartitionInfo = this.baseIndustrypartitionService.getInfoById(id);
        return R.ok().put("baseIndustrypartitionInfo", (Object)baseIndustrypartitionInfo);
    }

    @GetMapping(value={"/infoByCode/{industrycode}"})
    @RequiresPermissions(value={"pmjk:baseIndustrypartition:info"})
    public R infoByCode(@PathVariable(value="industrycode") String industrycode) {
        BaseIndustrypartitionEntity baseIndustrypartitionInfo = this.baseIndustrypartitionService.getInfoByIndustrycode(industrycode);
        return R.ok().put("baseIndustrypartitionInfo", (Object)baseIndustrypartitionInfo);
    }

    @SysLog(value="\u65b0\u589e")
    @PostMapping(value={"/save"})
    @RequiresPermissions(value={"pmjk:baseIndustrypartition:save"})
    public R add(@RequestBody BaseIndustrypartitionEntity baseIndustrypartitionEntity) {
        this.baseIndustrypartitionService.insert(baseIndustrypartitionEntity);
        return R.ok();
    }

    @SysLog(value="\u66f4\u65b0")
    @PostMapping(value={"/update"})
    @RequiresPermissions(value={"pmjk:baseIndustrypartition:update"})
    public R update(@RequestBody BaseIndustrypartitionEntity baseIndustrypartitionEntity) {
        this.baseIndustrypartitionService.updateById(baseIndustrypartitionEntity);
        return R.ok();
    }

    @SysLog(value="\u5220\u9664")
    @PostMapping(value={"/delete"})
    @RequiresPermissions(value={"pmjk:baseIndustrypartition:delete"})
    public R delete(@RequestBody List<Long> ids) {
        this.baseIndustrypartitionService.deleteBatchIds(ids);
        return R.ok();
    }

    @SysLog(value="\u4e0a\u4f20\u6587\u4ef6")
    @PostMapping(value={"/upload"})
    public R upload(@RequestParam(value="file") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                this.saveFile(file);
                List<BaseIndustrypartitionEntity> list = this.parseFile(file);
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
        pathLevelList.add("baseIndustrypartition");
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

    private List<BaseIndustrypartitionEntity> parseFile(MultipartFile file) throws Exception {
        String text = "{'sheetNum':1,'startLineStrategyType':'NUMBER','startLineStrategyValue':2,'endLineStrategyType':'NUMBER','endLineStrategyValue':-1,'list':[{'columnNum':1,'fieldName':'XXX','classType':'String','isRequired':true},{'columnNum':2,'fieldName':'XXX','classType':'String','isRequired':true},{'columnNum':3,'fieldName':'XXX','classType':'String','isRequired':true},{'columnNum':4,'fieldName':'XXX','classType':'BigDecimal','isRequired':true},{'columnNum':5,'fieldName':'XXX','classType':'String','isRequired':true}]}";
        FileToClassMapping fileToTableMapping = (FileToClassMapping)JSONObject.parseObject((String)text, FileToClassMapping.class);
        List<BaseIndustrypartitionEntity> list = null;
        ExcelReadDataFromFileTemplate<BaseIndustrypartitionEntity> readData = new ExcelReadDataFromFileTemplate<BaseIndustrypartitionEntity>();
        list = ((ReadDataFromFileTemplate)readData).parseFile(file, fileToTableMapping, BaseIndustrypartitionEntity.class);
        return list;
    }
}

