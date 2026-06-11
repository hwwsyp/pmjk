package com.tpfh.fintech.common.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.tpfh.fintech.common.exception.TpfhException;
import com.tpfh.fintech.modules.sys.entity.SysUserEntity;

/**
 * Shiro工具类
 * 
 * @author tpfh
 * @email tpfh@tpfh.com
 * @date 2016年11月12日 上午9:49:19
 */
public class ShiroUtils {

	public static Session getSession() {
		return SecurityUtils.getSubject().getSession();
	}

	public static Subject getSubject() {
		return SecurityUtils.getSubject();
	}

	public static SysUserEntity getUserEntity() {
		return (SysUserEntity)SecurityUtils.getSubject().getPrincipal();
	}

	public static Long getUserId() {
		return getUserEntity().getUserId();
	}
	
	public static void setSessionAttribute(Object key, Object value) {
		getSession().setAttribute(key, value);
	}

	public static Object getSessionAttribute(Object key) {
		return getSession().getAttribute(key);
	}

	public static boolean isLogin() {
		return SecurityUtils.getSubject().getPrincipal() != null;
	}

	public static String getKaptcha(String key) {
		Object kaptcha = getSessionAttribute(key);
		if(kaptcha == null){
			throw new TpfhException("验证码已失效");
		}
		getSession().removeAttribute(key);
		return kaptcha.toString();
	}
	
	/**
	 * add by owen in 20230605 如果当前用户不存在，则返回system作为当前用户，
	 * 主要用途，在定时任务框架quartz，此时并没有用户登录
	 * @return
	 */
	public static String getUserNameEvenError() {
		try {
			SysUserEntity userEntity = (SysUserEntity)SecurityUtils.getSubject().getPrincipal();
			return userEntity.getUsername();
		}catch (Exception e) {
			return "System";
		}
	}

}
