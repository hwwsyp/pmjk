package com.tpfh.fintech.modules.sys.entity;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import lombok.Data;

/**
 * 系统配置信息
 * 
 * @author tpfh
 * @email tpfh@tpfh.com
 * @date 2016年12月4日 下午6:43:36
 */
@TableName("sys_config")
@Data
public class SysConfigEntity {
	@TableId
	private Long id;
	@NotBlank(message="参数名不能为空")
	private String key;
	@NotBlank(message="参数值不能为空")
	private String value; 
	private String remark;
	private Long parentId;
	
	@TableField(exist=false)
	private String parentName;
	
	private Integer orderNum;

	private Boolean open;	

	private List<?> list;
	
}
