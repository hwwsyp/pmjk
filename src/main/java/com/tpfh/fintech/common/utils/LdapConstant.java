/*
 * Decompiled with CFR 0.152.
 */
package com.tpfh.fintech.common.utils;

public class LdapConstant {
    public static final String LDAPHOST = "10.135.7.142";
    public static final String LOGINDN = "cn=jksjxsuser,ou=user,o=services";
    public static final String LOGINPASSWORD = "jksjxs@20170522";

    public static enum LdapUserAuthEnum {
        SUCCESS(100, "\u7528\u6237\u9a8c\u8bc1\u901a\u8fc7\uff01"),
        USER_LOCKED(10001, "\u7528\u6237\u5df2\u88ab\u9501\u5b9a\uff01"),
        PWD_ERROR(10002, "\u5bc6\u7801\u9519\u8bef\uff0c\u82e5\u8fde\u7eed\u8f93\u5165\u9519\u8bef\u5bc6\u78015\u6b21\u8d26\u53f7\u5c06\u88ab\u9501\u5b9a\uff01"),
        ACCOUNT_ERROR(10003, "\u8d26\u53f7\u9501\u5b9a\uff0c\u8f93\u5165\u9519\u8bef\u5bc6\u7801\u6b21\u6570{errorCount}\u6b21\uff01"),
        PWD_OVER_TWO_MONTH(10004, "2\u4e2a\u6708\u5185\u672a\u4fee\u6539\u5bc6\u7801\uff0c\u8bf7\u4fee\u6539\u540e\u518d\u767b\u5f55!"),
        PWD_OVERDUE(10005, "\u5bc6\u7801\u5df2\u8fc7\u671f\uff01"),
        LDAP_CONNECT_ERROR(10006, "ladp\u8fde\u63a5\u5f02\u5e38\uff0c\u8bf7\u7a0d\u540e\u518d\u8bd5\uff01"),
        USERCODE_NOT_EXIST(10007, "\u7528\u6237\u540d\u4e0d\u5b58\u5728\uff01");

        private int code;
        private String msg;

        private LdapUserAuthEnum(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return this.code;
        }

        public String getMsg() {
            return this.msg;
        }
    }
}

