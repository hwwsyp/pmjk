package com.tpfh.fintech.modules.sys.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.tpfh.fintech.common.validator.group.AddGroup;
import com.tpfh.fintech.common.validator.group.UpdateGroup;

import lombok.Data;

/**
 * 系统用户
 * 
 * @author tpfh
 * @email tpfh@tpfh.com
 */
@TableName("sys_user")
@Data
public class SysUserEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 用户ID
	 */
	@TableId
	private Long userId;

	/**
	 * 用户名
	 */
	@NotBlank(message="用户名不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String username;

	/**
	 * 密码
	 */
	@TableField
	private String password;

	/**
	 * 盐
	 */
	private String salt;

	/**
	 * 邮箱
	 */
	@NotBlank(message="邮箱不能为空", groups = {AddGroup.class, UpdateGroup.class})
	@Email(message="邮箱格式不正确", groups = {AddGroup.class, UpdateGroup.class})
	private String email;

	/**
	 * 手机号
	 */
	private String mobile;
	private String name;
	
	/**
	 * 类型  1：系统用户   2：Ldap用户
	 */
	private Integer type;

	/**
	 * 状态  0：禁用   1：正常   2：初始化（首次登录须修改密码）
	 */
	private Integer status;
	
	/**
	 * 角色ID列表
	 */
	@TableField(exist=false)
	private List<Long> roleIdList;
	
	/**
	 * 部门名称
	 */
	@TableField(exist=false)
	private String deptName;
	

	/**
	 * 角色名称
	 */
	@TableField(exist=false)
	private String roleName;
	
	
	/**
	 * 创建者ID
	 */
	private Long createUserId;

	/**
	 * 创建时间
	 */
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
	private Date createTime;
	
	/**
	 * 部门ID
	 */
	private String deptId;
	
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	/**
	 * 上一级部门ID
	 */
	@TableField(exist=false)
	private String deptParentId;
	
	@TableField(exist = false)
	private Integer oaUserId;
	
}
