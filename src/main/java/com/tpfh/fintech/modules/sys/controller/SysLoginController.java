/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.servlet.ServletException
 *  javax.servlet.ServletOutputStream
 *  javax.servlet.http.HttpServletResponse
 *  org.apache.commons.io.IOUtils
 *  org.apache.shiro.crypto.hash.Sha256Hash
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.beans.factory.annotation.Value
 *  org.springframework.web.bind.annotation.GetMapping
 *  org.springframework.web.bind.annotation.PostMapping
 *  org.springframework.web.bind.annotation.RequestBody
 *  org.springframework.web.bind.annotation.RestController
 */
package com.tpfh.fintech.modules.sys.controller;

import com.tpfh.fintech.common.annotation.SysLog;
import com.tpfh.fintech.common.utils.AESDecryptorUtil;
import com.tpfh.fintech.common.utils.HttpUtil;
import com.tpfh.fintech.common.utils.LdapUserAuth;
import com.tpfh.fintech.common.utils.R;
import com.tpfh.fintech.modules.sys.controller.AbstractController;
import com.tpfh.fintech.modules.sys.entity.SysUserEntity;
import com.tpfh.fintech.modules.sys.form.SysLoginForm;
import com.tpfh.fintech.modules.sys.service.SysCaptchaService;
import com.tpfh.fintech.modules.sys.service.SysUserService;
import com.tpfh.fintech.modules.sys.service.SysUserTokenService;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SysLoginController
extends AbstractController {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserTokenService sysUserTokenService;
    @Autowired
    private SysCaptchaService sysCaptchaService;
    @Autowired
    private HttpUtil httpUtil;
    @Value(value="${tpfh.logUrl}")
    private String logUrl;

    @GetMapping(value={"captcha.jpg"})
    public void captcha(HttpServletResponse response, String uuid) throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        BufferedImage image = this.sysCaptchaService.getCaptcha(uuid);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write((RenderedImage)image, "jpg", (OutputStream)out);
        IOUtils.closeQuietly((OutputStream)out);
    }

    @PostMapping(value={"/sys/login"})
    public Map<String, Object> login(@RequestBody SysLoginForm form) throws IOException {
        SysUserEntity user = this.sysUserService.queryByUserName(form.getUsername());
        if (user == null) {
            return R.error("\u8d26\u53f7\u672a\u6388\u6743\uff0c\u65e0\u6cd5\u767b\u5f55");
        }
        if (user.getType() == 2) {
            Map<String, Object> map = new LdapUserAuth().ldapUserAuth(form.getUsername(), form.getPassword());
            if (!map.get("code").equals(100)) {
                return R.error(map.get("msg").toString());
            }
        } else {
            String encryptedData = form.getPassword();
            String key = "jk%fdsa2QERX_2+2";
            String iv = "1iopi7&FDS123456";
            String decrypted = "";
            try {
                decrypted = AESDecryptorUtil.decrypt(encryptedData, key, iv);
                System.out.println("\u89e3\u5bc6\u7ed3\u679c\uff1a" + decrypted);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            if (!user.getPassword().equals(new Sha256Hash((Object)decrypted, (Object)user.getSalt()).toHex())) {
                return R.error("\u8d26\u53f7\u6216\u5bc6\u7801\u4e0d\u6b63\u786e");
            }
            if (user.getStatus() == 0) {
                return R.error("\u8d26\u53f7\u5df2\u88ab\u9501\u5b9a,\u8bf7\u8054\u7cfb\u7ba1\u7406\u5458");
            }
        }
        R r = this.sysUserTokenService.createToken(user.getUserId());
        HashMap<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("userName", r.get("token"));
        paramsMap.put("subSysAlias", "ZGINVEST");
        paramsMap.put("remark", "login");
        this.httpUtil.sendGet(this.logUrl, paramsMap);
        return r;
    }

    @SysLog(value="\u9000\u51fa\u7cfb\u7edf")
    @PostMapping(value={"/sys/logout"})
    public R logout() {
        this.sysUserTokenService.logout(this.getUserId());
        return R.ok();
    }
}

