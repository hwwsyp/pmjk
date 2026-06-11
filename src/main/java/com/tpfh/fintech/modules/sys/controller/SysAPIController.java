package com.tpfh.fintech.modules.sys.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tpfh.fintech.common.utils.R;

/***
 * 20210716 新增用于统计每次用户访问接口
 * @author vhww
 *
 */
@RestController
@RequestMapping("/sys/api")
public class SysAPIController extends AbstractController {
	
	/**
	 * 每次用户登录是，保存用户访问的记录
	 * @param userName 用户名称
	 * @param subSysAlias 系统名称
	 * @param remark 访问的路径
	 */
	@GetMapping("/saveAccStatis")
	public R saveAccessStatistics(@RequestParam String token, @RequestParam String subSysAlias, @RequestParam String remark) {
		System.out.println(token==null?"无名氏":token);
		System.out.println(subSysAlias==null?"无当前项目名":subSysAlias);
		System.out.println(remark==null?"无访问路径":remark);
		return R.ok();
	}
}