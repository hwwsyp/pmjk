package com.tpfh.fintech.modules.${prefix}.${function}.controller;

import java.nio.file.Files;
import java.nio.file.Paths;
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

import com.alibaba.fastjson2.JSONObject;
import com.tpfh.fintech.common.annotation.SysLog;
import com.tpfh.fintech.common.utils.DateUtils;
import com.tpfh.fintech.common.utils.PageUtils;
import com.tpfh.fintech.common.utils.R;
import com.tpfh.fintech.modules.share.template.ExcelReadDataFromFileTemplate;
import com.tpfh.fintech.modules.share.template.FileToClassMapping;
import com.tpfh.fintech.modules.share.template.ReadDataFromFileTemplate;
import com.tpfh.fintech.modules.${prefix}.${function}.entity.${entityName}Entity;
import com.tpfh.fintech.modules.${prefix}.${function}.service.${entityName}Service;
import com.tpfh.fintech.modules.sys.controller.AbstractController;


/**
 * 如果该模块是新增的，请务必确认是否需要在ShiroConfig的shirFilter方法中配置权限控制
 * filterMap.put("/${prefix}/**", "oauth2");
 */

@RestController
@RequestMapping("/${prefix}/${function}")
public class ${entityName}Controller extends AbstractController {
	
	@Autowired
	private ${entityName}Service ${function}Service;

	/**
	 *  获取分页数据
	 */
	@GetMapping("/list")
	@RequiresPermissions("${prefix}:${function}:list")
	public R list(@RequestParam HashMap<String,Object> params){
		PageUtils page = ${function}Service.queryPage(params);
		return R.ok().put("page", page);
	}
	
	/**
	 * 获取全部数据
	 */
	@GetMapping("/get${entityName}List")
	@RequiresPermissions("${prefix}:${function}:list")
	public R get${entityName}List(@RequestParam HashMap<String,Object> params){
		List<${entityName}Entity> list = ${function}Service.getInfoList(params);
		return R.ok().put("result", list);
	}
	
	/**
	 * 获取详情
	 */
	@GetMapping("/info/{id}")
	@RequiresPermissions("${prefix}:${function}:info")
	public R info(@PathVariable("id") Long id){
		${entityName}Entity ${function}Info = ${function}Service.getInfoById(id);
		return R.ok().put("${function}Info", ${function}Info);
	}
	
	/**
	 *  增加
	 */
	@SysLog("新增")
	@PostMapping("/save")
	@RequiresPermissions("${prefix}:${function}:save")
	public R add(@RequestBody ${entityName}Entity ${function}Entity ){
		${function}Service.insert(${function}Entity);
		return R.ok();
	}
	
	/**
	 *  更新
	 */
	@SysLog("更新")
	@PostMapping("/update")
	@RequiresPermissions("${prefix}:${function}:update")
	public R update(@RequestBody ${entityName}Entity ${function}Entity ){
		${function}Service.updateById(${function}Entity);
		return R.ok();
	}
	
	/**
	 *  删除
	 */
	@SysLog("删除")
	@PostMapping("/delete")
	@RequiresPermissions("${prefix}:${function}:delete")
	public R delete(@RequestBody List<Long> ids ){
		${function}Service.deleteBatchIds(ids);
		return R.ok();
	}
	
	@Value("<#noparse>${</#noparse>tpfh.filePath<#noparse>}</#noparse>")
	String filePath;
	
	@SysLog("上传文件")
	@PostMapping("/upload")
	public R upload(@RequestParam("file") MultipartFile file) {
		if (!file.isEmpty()) {
			try {
				//保存文件
				this.saveFile(file);
				
				//解析文件
				List<${entityName}Entity> list = this.parseFile(file);

				return R.ok("上传成功");
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				return R.error(e.getMessage());
			}
		} else {
			return R.error("上传失败,文件为空");
		}
	}
	
	/**
	 * 上传文件，保存在服务器中
	 * @param file
	 * @param bankCode
	 */
	private void saveFile(MultipartFile file) throws Exception{
		//实际上传日期
		String today = DateUtils.format(new Date());

		//文件层级： 2023-03-30/POSITION/JPM
		List<String> pathLevelList = new ArrayList<String>();
		pathLevelList.add(today);
		pathLevelList.add("${function}");

		String bankTodayUploadPath = filePath;
		//循环处理文件夹的创建
		for(String path : pathLevelList) {
			bankTodayUploadPath = bankTodayUploadPath + "/" + path;
			if(!Files.exists(Paths.get(bankTodayUploadPath))) {
				Files.createDirectory(Paths.get(bankTodayUploadPath));
			}
		}

		// 获取文件名
		String fileName = file.getOriginalFilename();
		// 获取文件的后缀名
		String suffixName = fileName.substring(fileName.lastIndexOf("."));
		fileName = fileName.substring(0, fileName.lastIndexOf(".")) + "-" + System.currentTimeMillis() + suffixName;

		logger.info(Paths.get(bankTodayUploadPath, fileName).toAbsolutePath().toString());
		Files.copy(file.getInputStream(), Paths.get(bankTodayUploadPath, fileName));
	}
	
	/**
	 * 解析文件数据
	 * @param file
	 * @return
	 * @throws Exception
	 */
	private List<${entityName}Entity> parseFile(MultipartFile file) throws Exception {
		//解析配置信息json
		String text = "{'sheetNum':1,"
				+ "'startLineStrategyType':'NUMBER','startLineStrategyValue':2,"
				+ "'endLineStrategyType':'NUMBER','endLineStrategyValue':-1,"
				+ "'list':"
				+ "[{'columnNum':1,'fieldName':'XXX','classType':'String','isRequired':true}"
				+ ",{'columnNum':2,'fieldName':'XXX','classType':'String','isRequired':true}"
				+ ",{'columnNum':3,'fieldName':'XXX','classType':'String','isRequired':true}"
				+ ",{'columnNum':4,'fieldName':'XXX','classType':'BigDecimal','isRequired':true}"
				+ ",{'columnNum':5,'fieldName':'XXX','classType':'String','isRequired':true}]}";
		FileToClassMapping fileToTableMapping = JSONObject.parseObject(text, FileToClassMapping.class);
		
		//开始根据配置进行文件的初步解析
		List<${entityName}Entity> list = null;
		ReadDataFromFileTemplate<${entityName}Entity> readData =  new ExcelReadDataFromFileTemplate<${entityName}Entity>();
		list = readData.parseFile(file, fileToTableMapping, ${entityName}Entity.class);

		return list;
	}
	
	
}