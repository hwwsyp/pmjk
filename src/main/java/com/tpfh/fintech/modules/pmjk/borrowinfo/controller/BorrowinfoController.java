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
package com.tpfh.fintech.modules.pmjk.borrowinfo.controller;

import com.alibaba.fastjson.JSONObject;
import com.tpfh.fintech.common.annotation.SysLog;
import com.tpfh.fintech.common.utils.DateUtils;
import com.tpfh.fintech.common.utils.PageUtils;
import com.tpfh.fintech.common.utils.R;
import com.tpfh.fintech.modules.pmjk.borrowinfo.entity.BorrowinfoEntity;
import com.tpfh.fintech.modules.pmjk.borrowinfo.service.BorrowinfoService;
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
@RequestMapping(value={"/pmjk/borrowinfo"})
public class BorrowinfoController
extends AbstractController {
    @Autowired
    private BorrowinfoService borrowinfoService;
    @Value(value="${tpfh.filePath}")
    String filePath;

    @GetMapping(value={"/list"})
    @RequiresPermissions(value={"pmjk:borrowinfo:list"})
    public R list(@RequestParam HashMap<String, Object> params) {
        PageUtils page = this.borrowinfoService.queryPage(params);
        return R.ok().put("page", (Object)page);
    }

    @GetMapping(value={"/getBorrowinfoList"})
    @RequiresPermissions(value={"pmjk:borrowinfo:list"})
    public R getBorrowinfoList(@RequestParam HashMap<String, Object> params) {
        List<BorrowinfoEntity> list = this.borrowinfoService.getInfoList(params);
        return R.ok().put("result", (Object)list);
    }

    @GetMapping(value={"/info/{creditenhanceid}"})
    @RequiresPermissions(value={"pmjk:borrowinfo:info"})
    public R info(@PathVariable(value="creditenhanceid") Long creditenhanceid) {
        BorrowinfoEntity borrowinfoInfo = this.borrowinfoService.getInfoByCreditenhanceid(creditenhanceid);
        return R.ok().put("borrowinfoInfo", (Object)borrowinfoInfo);
    }

    @SysLog(value="\u65b0\u589e")
    @PostMapping(value={"/save"})
    @RequiresPermissions(value={"pmjk:borrowinfo:save"})
    public R add(@RequestBody BorrowinfoEntity borrowinfoEntity) {
        this.borrowinfoService.insert(borrowinfoEntity);
        return R.ok();
    }

    @SysLog(value="\u66f4\u65b0")
    @PostMapping(value={"/update"})
    @RequiresPermissions(value={"pmjk:borrowinfo:update"})
    public R update(@RequestBody BorrowinfoEntity borrowinfoEntity) {
        this.borrowinfoService.updateById(borrowinfoEntity);
        return R.ok();
    }

    @SysLog(value="\u5220\u9664")
    @PostMapping(value={"/delete"})
    @RequiresPermissions(value={"pmjk:borrowinfo:delete"})
    public R delete(@RequestBody List<Long> ids) {
        this.borrowinfoService.deleteBatchIds(ids);
        return R.ok();
    }

    @SysLog(value="\u4e0a\u4f20\u6587\u4ef6")
    @PostMapping(value={"/upload"})
    public R upload(@RequestParam(value="file") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                this.saveFile(file);
                List<BorrowinfoEntity> list = this.parseFile(file);
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
        pathLevelList.add("borrowinfo");
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

    private List<BorrowinfoEntity> parseFile(MultipartFile file) throws Exception {
        String text = "{'sheetNum':1,'startLineStrategyType':'NUMBER','startLineStrategyValue':2,'endLineStrategyType':'NUMBER','endLineStrategyValue':-1,'list':[{'columnNum':1,'fieldName':'XXX','classType':'String','isRequired':true},{'columnNum':2,'fieldName':'XXX','classType':'String','isRequired':true},{'columnNum':3,'fieldName':'XXX','classType':'String','isRequired':true},{'columnNum':4,'fieldName':'XXX','classType':'BigDecimal','isRequired':true},{'columnNum':5,'fieldName':'XXX','classType':'String','isRequired':true}]}";
        FileToClassMapping fileToTableMapping = (FileToClassMapping)JSONObject.parseObject((String)text, FileToClassMapping.class);
        List<BorrowinfoEntity> list = null;
        ExcelReadDataFromFileTemplate<BorrowinfoEntity> readData = new ExcelReadDataFromFileTemplate<BorrowinfoEntity>();
        list = ((ReadDataFromFileTemplate)readData).parseFile(file, fileToTableMapping, BorrowinfoEntity.class);
        return list;
    }
}

