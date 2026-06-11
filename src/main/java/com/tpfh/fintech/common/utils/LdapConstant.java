package com.tpfh.fintech.common.utils;

/**
 * 
 * @author xiaodeng
 *
 */
public class LdapConstant {
	
	 /**
     * ldap请求参数变量名称（生产环境）
     */
    public static final String LDAPHOST="10.135.7.142";
    public static final String LOGINDN = "cn=jksjxsuser,ou=user,o=services";
    public static final String LOGINPASSWORD = "jksjxs@20170522";

    /**
     * 
     * @author xiaodeng
     *
     */
    public enum LdapUserAuthEnum {
    	
    	/**
    	 * 用户验证通过！
    	 */
    	SUCCESS(100, "用户验证通过！"),
        /**
         * 用户已被锁定！
         */
    	USER_LOCKED(10001, "用户已被锁定！"),
    	/**
    	 * 密码错误，连续输入错误密码5次账号将被锁定！
    	 */
    	PWD_ERROR(10002, "密码错误，若连续输入错误密码5次账号将被锁定！"),
        /**
         * 账号锁定，输入错误密码次数{loginIntruderAttemptsInt}次！
         */
    	ACCOUNT_ERROR(10003, "账号锁定，输入错误密码次数{errorCount}次！"),
    	/**
    	 * 2个月内未修改密码，请修改后再登录!
    	 */
    	PWD_OVER_TWO_MONTH(10004, "2个月内未修改密码，请修改后再登录!"),
    	/**
    	 * 密码已过期！
    	 */
    	PWD_OVERDUE(10005, "密码已过期！"),
    	/**
    	 * ladp连接异常，请稍后再试！
    	 */
    	LDAP_CONNECT_ERROR(10006, "ladp连接异常，请稍后再试！"),
    	/**
    	 * 用户名不存在！
    	 */
    	USERCODE_NOT_EXIST(10007, "用户名不存在！");

        private int code;
        private String msg;

        LdapUserAuthEnum(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }
        
        public int getCode() {
            return code;
        }
        public String getMsg() {
        	return msg;
        }
        
    }
    
}
