package com.tpfh.fintech.modules.sys.controller;

import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tpfh.fintech.common.utils.PageUtils;
import com.tpfh.fintech.common.utils.R;
import com.tpfh.fintech.modules.sys.service.SysLogService;


/**
 * 系统日志
 */
@Controller
@RequestMapping("/sys/log")
public class SysLogController {
	@Autowired
	private SysLogService sysLogService;
	
	/**
	 * 列表
	 */
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("sys:log:list")
	public R list(@RequestParam Map<String, Object> params){
		PageUtils page = sysLogService.queryPage(params);
		
		return R.ok().put("page", page);
	}
	
	/*@GetMapping("/getLog/{id}/{busId}")
	public R getLog(@PathVariable("id") Integer id,@PathVariable("busId") String busId){
		List<ExtendActTasklogEntity> logList = sysLogService.selectLog(id,busId);
		return R.ok().put("logList", logList);
	}*/
	
}
