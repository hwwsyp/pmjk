package com.tpfh.fintech.modules.sys.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.tpfh.fintech.common.annotation.SysLog;
import com.tpfh.fintech.common.utils.PageUtils;
import com.tpfh.fintech.common.utils.R;
import com.tpfh.fintech.modules.sys.entity.SysAttachmentEntity;
import com.tpfh.fintech.modules.sys.service.SysAttachmentService;


/**
 * 如果该模块是新增的，请务必确认是否需要在ShiroConfig的shirFilter方法中配置权限控制
 * filterMap.put("/sys/**", "oauth2");
 */

@RestController
@RequestMapping("/sys/attachment")
public class SysAttachmentController extends AbstractController {
	
	@Autowired
	private SysAttachmentService attachmentService;

	@GetMapping("/download")
    public void download(@RequestParam("code") String code, HttpServletResponse response) throws IOException {
        EntityWrapper<SysAttachmentEntity> wrapper = new EntityWrapper<>();
		wrapper.eq("code", code);
		SysAttachmentEntity entity = attachmentService.selectOne(wrapper);
        
        //获取文件输入流
        FileInputStream is = new FileInputStream(new File(entity.getFilePath()));
        //附件下载
        response.setHeader("content-disposition", "attachment" + ";fileName=" + URLEncoder.encode(entity.getSrcFileName(), "UTF-8"));
        //获取响应输出流
        ServletOutputStream os = response.getOutputStream();
        //文件拷贝
        IOUtils.copy(is, os);
        IOUtils.closeQuietly(is);
        IOUtils.closeQuietly(os);
    }
	
	/**
	 *  获取分页数据
	 */
	@GetMapping("/list")
	@RequiresPermissions("sys:attachment:list")
	public R list(@RequestParam HashMap<String,Object> params){
		PageUtils page = attachmentService.queryPage(params);
		return R.ok().put("page", page);
	}
	
	/**
	 * 获取全部数据
	 */
	@GetMapping("/getAttachmentList")
	@RequiresPermissions("sys:attachment:list")
	public R getAttachmentList(@RequestParam HashMap<String,Object> params){
		List<SysAttachmentEntity> list = attachmentService.getInfoList(params);
		return R.ok().put("result", list);
	}
	
	/**
	 * 根据附件唯一编码，获取详情
	 */
	@GetMapping("/getAttachmentInfoByCode/{code}")
	@RequiresPermissions("sys:attachment:info")
	public R getAttachmentInfoByCode(@PathVariable("code") String code) {
		HashMap<String,Object> params = new HashMap<>();
		params.put("code", code);
		List<SysAttachmentEntity> infoList = attachmentService.getInfoList(params);
		if(infoList!=null && infoList.size()>0) {
			return R.ok().put("attachmentInfo", infoList.get(0));
		}
		return R.error("没有对应的附件信息");
	}
	
	/**
	 * 获取详情
	 */
	@GetMapping("/info/{id}")
	@RequiresPermissions("sys:attachment:info")
	public R info(@PathVariable("id") Long id){
		SysAttachmentEntity attachmentInfo = attachmentService.getInfoById(id);
		return R.ok().put("attachmentInfo", attachmentInfo);
	}
	
	/**
	 *  增加
	 */
	@SysLog("新增")
	@PostMapping("/save")
	@RequiresPermissions("sys:attachment:save")
	public R add(@RequestBody SysAttachmentEntity SysAttachmentEntity ){
		attachmentService.insert(SysAttachmentEntity);
		return R.ok();
	}
	
	/**
	 *  更新
	 */
	@SysLog("更新")
	@PostMapping("/update")
	@RequiresPermissions("sys:attachment:update")
	public R update(@RequestBody SysAttachmentEntity SysAttachmentEntity ){
		attachmentService.updateById(SysAttachmentEntity);
		return R.ok();
	}
	
	/**
	 *  删除
	 */
	@SysLog("删除")
	@PostMapping("/delete")
	@RequiresPermissions("sys:attachment:delete")
	public R delete(@RequestBody List<Long> ids ){
		attachmentService.deleteBatchIds(ids);
		return R.ok();
	}
}