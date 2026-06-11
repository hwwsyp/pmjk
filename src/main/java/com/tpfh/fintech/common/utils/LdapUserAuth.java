package com.tpfh.fintech.common.utils;


import com.novell.ldap.*;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LdapUserAuth {

	private static Logger logger = LoggerFactory.getLogger(LdapUserAuth.class);

	/**
	 * 获得ldap链接
	 * 
	 * @return
	 */
	public static synchronized LDAPConnection getLDAPConnection(String ldapHost, String loginDN, String password) {
		LDAPConnection lc = null;
		try {
			int ldapVersion = LDAPConnection.LDAP_V3;
			int ldapPort = 389;
			lc = new LDAPConnection();
			lc.connect(ldapHost, ldapPort);
			lc.bind(ldapVersion, loginDN, password);
			return lc;
		} catch (LDAPException e) {
			e.printStackTrace();
			if (lc != null) {
				try {
					lc.disconnect();
					lc = null;
				} catch (LDAPException e1) {
					e1.printStackTrace();
				}
			}
			return null;
		}
	}

	/**
	 * 释放ldap链接
	 * 
	 * @param lc
	 */
	public static void releaseLDAPConnection(LDAPConnection lc) {
		try {
			if (lc != null) {
				lc.disconnect();
				lc = null;
			}
		} catch (LDAPException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获得dn
	 * 
	 * @param lc
	 * @param cn
	 *            : 工号cn
	 * @return
	 */
	public static synchronized String getDN(LDAPConnection lc, String cn) {
		try {
			if ((cn == null || "".equals(cn))) {
				return null;
			}
			// tpfh 下面只查cn
			String searchBase = "o=tpfh";
			String searchFilter = "(&(cn=" + cn + ")(objectClass=user))";
			int searchScope = LDAPConnection.SCOPE_SUB;
			LDAPSearchResults searchResults = lc.search(searchBase, searchScope, searchFilter.toString(), null, false);
			while (searchResults.hasMore()) {
				LDAPEntry nextEntry = null;
				nextEntry = searchResults.next();
				String str = nextEntry.getDN();
				return str;
			}
			return null;
		} catch (LDAPException t) {
			t.printStackTrace();
			return null;
		}
	}

	/**
	 * 请求ldap校验用户是否合法
	 */
	public Map<String, Object> checkInfo(String ldapHost, String loginDN, String loginPassword, String dn, String pwd) {
		int ldapVersion = LDAPConnection.LDAP_V3;
		int ldapPort = 389;
		LDAPConnection lc = new LDAPConnection();
		Map<String, Object> msgMap = new HashMap<String, Object>();
		try {
			lc.connect(ldapHost, ldapPort);
			lc.bind(ldapVersion, loginDN, loginPassword.getBytes("UTF8"));
			Map<String, String> map = getOperationalAttrs(lc, dn);
			// 开始判断
			String logindisabled = map.get("logindisabled");
			if (logindisabled.equalsIgnoreCase("TRUE")) {
				msgMap.put("code", LdapConstant.LdapUserAuthEnum.USER_LOCKED.getCode());
				msgMap.put("msg", LdapConstant.LdapUserAuthEnum.USER_LOCKED.getMsg());
				return msgMap;
			}
			String loginIntruderAttempts = map.get("loginIntruderAttempts");
			if (loginIntruderAttempts != null && !"".equals(loginIntruderAttempts)) {
				int loginIntruderAttemptsInt = Integer.parseInt(loginIntruderAttempts);
				if (loginIntruderAttemptsInt >= 5) {
					msgMap.put("code", LdapConstant.LdapUserAuthEnum.ACCOUNT_ERROR.getCode());
					msgMap.put("msg", 
							LdapConstant.LdapUserAuthEnum.ACCOUNT_ERROR.getMsg()
								.replace("{errorCount}", loginIntruderAttemptsInt+""));
					return msgMap;
				}
			}
			// 超期
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssZ");
			String now = sdf.format(new Date());
			String loginExpirationTime = map.get("loginExpirationTime");
			if (loginExpirationTime != null && !"".equals(loginExpirationTime)) {
				if (now.compareTo(loginExpirationTime) > 0) {
					msgMap.put("code", LdapConstant.LdapUserAuthEnum.PWD_OVERDUE.getCode());
					msgMap.put("msg", LdapConstant.LdapUserAuthEnum.PWD_OVERDUE.getMsg());
					return msgMap;
				}
			}
			// 强制修改密码
			String passwordExpirationTime = map.get("passwordExpirationTime");
			if (passwordExpirationTime != null && !"".equals(passwordExpirationTime)) {
				if (now.compareTo(passwordExpirationTime) > 0) {
					msgMap.put("code", LdapConstant.LdapUserAuthEnum.PWD_OVER_TWO_MONTH.getCode());
					msgMap.put("msg", LdapConstant.LdapUserAuthEnum.PWD_OVER_TWO_MONTH.getMsg());
					return msgMap;
				}
			}
			// 判断密码
			LDAPAttribute pwdAttr = new LDAPAttribute("userPassword", pwd);
			boolean pwdCorrect = lc.compare(dn, pwdAttr);
			if (pwdCorrect) {
				msgMap.put("code", LdapConstant.LdapUserAuthEnum.SUCCESS.getCode());
				msgMap.put("msg", LdapConstant.LdapUserAuthEnum.SUCCESS.getMsg());
			} else {
				msgMap.put("code", LdapConstant.LdapUserAuthEnum.PWD_ERROR.getCode());
				msgMap.put("msg", LdapConstant.LdapUserAuthEnum.PWD_ERROR.getMsg());
			}
			
			lc.disconnect();
			return msgMap;
		} catch (LDAPException e) {
			msgMap.put("code", LdapConstant.LdapUserAuthEnum.LDAP_CONNECT_ERROR.getCode());
			msgMap.put("msg", LdapConstant.LdapUserAuthEnum.LDAP_CONNECT_ERROR.getMsg());
			logger.error("Error: " + e.toString());
		} catch (UnsupportedEncodingException e) {
			msgMap.put("code", LdapConstant.LdapUserAuthEnum.LDAP_CONNECT_ERROR.getCode());
			msgMap.put("msg", LdapConstant.LdapUserAuthEnum.LDAP_CONNECT_ERROR.getMsg());
			logger.error("Error: " + e.toString());
		} catch (Exception e) {
			msgMap.put("code", LdapConstant.LdapUserAuthEnum.LDAP_CONNECT_ERROR.getCode());
			msgMap.put("msg", LdapConstant.LdapUserAuthEnum.LDAP_CONNECT_ERROR.getMsg());
			logger.error("Error: " + e.toString());
		}
		return msgMap;
	}

	/**
	 * 获取用户属性信息
	 * 
	 * @param lc
	 * @param dn
	 * @throws LDAPException
	 */
	public Map<String, String> getOperationalAttrs(LDAPConnection lc, String dn) throws LDAPException {
		String returnAttrs[] = { "logindisabled", "loginExpirationTime", "loginIntruderAttempts",
				"passwordExpirationTime" };
		Map<String, String> map = new HashMap<String, String>();
		String attributeName, attrValue;
		Iterator allAttributes;
		Enumeration allValues;
		LDAPAttribute attribute;
		LDAPAttributeSet attributeSet;
		for (String str : returnAttrs) {
			map.put(str, "");
		}
		try {
			LDAPEntry entry = lc.read(dn, returnAttrs);
			attributeSet = entry.getAttributeSet();
			allAttributes = attributeSet.iterator();
			while (allAttributes.hasNext()) {
				attribute = (LDAPAttribute) allAttributes.next();
				attributeName = attribute.getName();
				if ((allValues = attribute.getStringValues()) != null
						&& (attrValue = (String) allValues.nextElement()) != null) {
					map.put(attributeName, attrValue);
				}
			}
			return map;
		} catch (LDAPException e) {
			System.err.println("\nOperationalAttrs() failed.");
			System.err.println("\nError: " + e.toString());
			System.exit(1);
			return map;
		}
	}
	
	/**
	 * 校验用户是否合法
	 */
	public Map<String, Object> ldapUserAuth(String userCode, String password) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			String ldapHost = LdapConstant.LDAPHOST;
			String loginDN = LdapConstant.LOGINDN;
			String loginPassword = LdapConstant.LOGINPASSWORD;
			// 获取链接
			LDAPConnection connection = LdapUserAuth.getLDAPConnection(ldapHost, loginDN, loginPassword);
			// 获取用户dn
			String dn = LdapUserAuth.getDN(connection, userCode);
			// 请求ldap验证用户
			if(StringUtils.isBlank(dn)) {
				resultMap.put("code", LdapConstant.LdapUserAuthEnum.USERCODE_NOT_EXIST.getCode());
				resultMap.put("msg", LdapConstant.LdapUserAuthEnum.USERCODE_NOT_EXIST.getMsg());
			}else {
				resultMap = checkInfo(ldapHost, loginDN, loginPassword, dn, password);
			}
			// 释放链接
			LdapUserAuth.releaseLDAPConnection(connection);
			return resultMap;
		} catch (Exception e) {
			logger.error("ladp连接异常", e.toString());
			resultMap.put("code", LdapConstant.LdapUserAuthEnum.LDAP_CONNECT_ERROR.getCode());
			resultMap.put("msg", LdapConstant.LdapUserAuthEnum.LDAP_CONNECT_ERROR.getMsg());
			return resultMap;
		}
	}

	public static void main(String[] args) {
		LdapUserAuth info = new LdapUserAuth();
		Map<String, Object> resultMap = info.ldapUserAuth("0143762", "Gb20170123.1");
		System.out.print(resultMap.toString());
	}
}
