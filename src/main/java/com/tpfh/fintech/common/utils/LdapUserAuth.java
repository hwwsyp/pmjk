/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.novell.ldap.LDAPAttribute
 *  com.novell.ldap.LDAPAttributeSet
 *  com.novell.ldap.LDAPConnection
 *  com.novell.ldap.LDAPEntry
 *  com.novell.ldap.LDAPException
 *  com.novell.ldap.LDAPSearchResults
 *  org.apache.commons.lang.StringUtils
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package com.tpfh.fintech.common.utils;

import com.novell.ldap.LDAPAttribute;
import com.novell.ldap.LDAPAttributeSet;
import com.novell.ldap.LDAPConnection;
import com.novell.ldap.LDAPEntry;
import com.novell.ldap.LDAPException;
import com.novell.ldap.LDAPSearchResults;
import com.tpfh.fintech.common.utils.LdapConstant;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LdapUserAuth {
    private static Logger logger = LoggerFactory.getLogger(LdapUserAuth.class);

    public static synchronized LDAPConnection getLDAPConnection(String ldapHost, String loginDN, String password) {
        LDAPConnection lc = null;
        try {
            int ldapVersion = 3;
            int ldapPort = 389;
            lc = new LDAPConnection();
            lc.connect(ldapHost, ldapPort);
            lc.bind(ldapVersion, loginDN, password);
            return lc;
        }
        catch (LDAPException e) {
            e.printStackTrace();
            if (lc != null) {
                try {
                    lc.disconnect();
                    lc = null;
                }
                catch (LDAPException e1) {
                    e1.printStackTrace();
                }
            }
            return null;
        }
    }

    public static void releaseLDAPConnection(LDAPConnection lc) {
        try {
            if (lc != null) {
                lc.disconnect();
                lc = null;
            }
        }
        catch (LDAPException e) {
            e.printStackTrace();
        }
    }

    public static synchronized String getDN(LDAPConnection lc, String cn) {
        block4: {
            try {
                if (cn != null && !"".equals(cn)) break block4;
                return null;
            }
            catch (LDAPException t) {
                t.printStackTrace();
                return null;
            }
        }
        String searchBase = "o=tpfh";
        String searchFilter = "(&(cn=" + cn + ")(objectClass=user))";
        int searchScope = 2;
        LDAPSearchResults searchResults = lc.search(searchBase, searchScope, searchFilter.toString(), null, false);
        if (searchResults.hasMore()) {
            LDAPEntry nextEntry = null;
            nextEntry = searchResults.next();
            String str = nextEntry.getDN();
            return str;
        }
        return null;
    }

    public Map<String, Object> checkInfo(String ldapHost, String loginDN, String loginPassword, String dn, String pwd) {
        int ldapVersion = 3;
        int ldapPort = 389;
        LDAPConnection lc = new LDAPConnection();
        HashMap<String, Object> msgMap = new HashMap<String, Object>();
        try {
            int loginIntruderAttemptsInt;
            lc.connect(ldapHost, ldapPort);
            lc.bind(ldapVersion, loginDN, loginPassword.getBytes("UTF8"));
            Map<String, String> map = this.getOperationalAttrs(lc, dn);
            String logindisabled = map.get("logindisabled");
            if (logindisabled.equalsIgnoreCase("TRUE")) {
                msgMap.put("code", LdapConstant.LdapUserAuthEnum.USER_LOCKED.getCode());
                msgMap.put("msg", LdapConstant.LdapUserAuthEnum.USER_LOCKED.getMsg());
                return msgMap;
            }
            String loginIntruderAttempts = map.get("loginIntruderAttempts");
            if (loginIntruderAttempts != null && !"".equals(loginIntruderAttempts) && (loginIntruderAttemptsInt = Integer.parseInt(loginIntruderAttempts)) >= 5) {
                msgMap.put("code", LdapConstant.LdapUserAuthEnum.ACCOUNT_ERROR.getCode());
                msgMap.put("msg", LdapConstant.LdapUserAuthEnum.ACCOUNT_ERROR.getMsg().replace("{errorCount}", String.valueOf(loginIntruderAttemptsInt)));
                return msgMap;
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssZ");
            String now = sdf.format(new Date());
            String loginExpirationTime = map.get("loginExpirationTime");
            if (loginExpirationTime != null && !"".equals(loginExpirationTime) && now.compareTo(loginExpirationTime) > 0) {
                msgMap.put("code", LdapConstant.LdapUserAuthEnum.PWD_OVERDUE.getCode());
                msgMap.put("msg", LdapConstant.LdapUserAuthEnum.PWD_OVERDUE.getMsg());
                return msgMap;
            }
            String passwordExpirationTime = map.get("passwordExpirationTime");
            if (passwordExpirationTime != null && !"".equals(passwordExpirationTime) && now.compareTo(passwordExpirationTime) > 0) {
                msgMap.put("code", LdapConstant.LdapUserAuthEnum.PWD_OVER_TWO_MONTH.getCode());
                msgMap.put("msg", LdapConstant.LdapUserAuthEnum.PWD_OVER_TWO_MONTH.getMsg());
                return msgMap;
            }
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
        }
        catch (LDAPException e) {
            msgMap.put("code", LdapConstant.LdapUserAuthEnum.LDAP_CONNECT_ERROR.getCode());
            msgMap.put("msg", LdapConstant.LdapUserAuthEnum.LDAP_CONNECT_ERROR.getMsg());
            logger.error("Error: " + e.toString());
        }
        catch (UnsupportedEncodingException e) {
            msgMap.put("code", LdapConstant.LdapUserAuthEnum.LDAP_CONNECT_ERROR.getCode());
            msgMap.put("msg", LdapConstant.LdapUserAuthEnum.LDAP_CONNECT_ERROR.getMsg());
            logger.error("Error: " + e.toString());
        }
        catch (Exception e) {
            msgMap.put("code", LdapConstant.LdapUserAuthEnum.LDAP_CONNECT_ERROR.getCode());
            msgMap.put("msg", LdapConstant.LdapUserAuthEnum.LDAP_CONNECT_ERROR.getMsg());
            logger.error("Error: " + e.toString());
        }
        return msgMap;
    }

    public Map<String, String> getOperationalAttrs(LDAPConnection lc, String dn) throws LDAPException {
        String[] returnAttrs = new String[]{"logindisabled", "loginExpirationTime", "loginIntruderAttempts", "passwordExpirationTime"};
        HashMap<String, String> map = new HashMap<String, String>();
        String[] stringArray = returnAttrs;
        int n = returnAttrs.length;
        int n2 = 0;
        while (n2 < n) {
            String str = stringArray[n2];
            map.put(str, "");
            ++n2;
        }
        try {
            LDAPEntry entry = lc.read(dn, returnAttrs);
            LDAPAttributeSet attributeSet = entry.getAttributeSet();
            for (LDAPAttribute attribute : attributeSet) {
                String attrValue;
                String attributeName = attribute.getName();
                Enumeration allValues = attribute.getStringValues();
                if (allValues == null || (attrValue = (String)allValues.nextElement()) == null) continue;
                map.put(attributeName, attrValue);
            }
            return map;
        }
        catch (LDAPException e) {
            System.err.println("\nOperationalAttrs() failed.");
            System.err.println("\nError: " + e.toString());
            System.exit(1);
            return map;
        }
    }

    public Map<String, Object> ldapUserAuth(String userCode, String password) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            String ldapHost = "10.135.7.142";
            String loginDN = "cn=jksjxsuser,ou=user,o=services";
            String loginPassword = "jksjxs@20170522";
            LDAPConnection connection = LdapUserAuth.getLDAPConnection(ldapHost, loginDN, loginPassword);
            String dn = LdapUserAuth.getDN(connection, userCode);
            if (StringUtils.isBlank((String)dn)) {
                resultMap.put("code", LdapConstant.LdapUserAuthEnum.USERCODE_NOT_EXIST.getCode());
                resultMap.put("msg", LdapConstant.LdapUserAuthEnum.USERCODE_NOT_EXIST.getMsg());
            } else {
                resultMap = this.checkInfo(ldapHost, loginDN, loginPassword, dn, password);
            }
            LdapUserAuth.releaseLDAPConnection(connection);
            return resultMap;
        }
        catch (Exception e) {
            logger.error("ladp\u8fde\u63a5\u5f02\u5e38", (Object)e.toString());
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

