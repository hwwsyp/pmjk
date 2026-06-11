package com.tpfh.fintech.modules.sys.controller;

import org.apache.commons.io.IOUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tpfh.fintech.common.annotation.SysLog;
import com.tpfh.fintech.common.utils.AESDecryptorUtil;
import com.tpfh.fintech.common.utils.HttpUtil;
import com.tpfh.fintech.common.utils.LdapUserAuth;
import com.tpfh.fintech.common.utils.R;
import com.tpfh.fintech.modules.sys.entity.SysUserEntity;
import com.tpfh.fintech.modules.sys.form.SysLoginForm;
import com.tpfh.fintech.modules.sys.service.SysCaptchaService;
import com.tpfh.fintech.modules.sys.service.SysUserService;
import com.tpfh.fintech.modules.sys.service.SysUserTokenService;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录相关
 * 
 * @author tpfh
 * @email tpfh@tpfh.com
 * @date 2016年11月10日 下午1:15:31
 */
@RestController
public class SysLoginController extends AbstractController {
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysUserTokenService sysUserTokenService;
	@Autowired
	private SysCaptchaService sysCaptchaService;
	
	@Autowired
	private HttpUtil httpUtil;
	
	@Value(value = "${tpfh.logUrl}")
	private String logUrl;

	/**
	 * 验证码
	 */
	@GetMapping("captcha.jpg")
	public void captcha(HttpServletResponse response, String uuid)throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-store, no-cache");
		response.setContentType("image/jpeg");

		//获取图片验证码
		BufferedImage image = sysCaptchaService.getCaptcha(uuid);

		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(image, "jpg", out);
		IOUtils.closeQuietly(out);
	}

	/**
	 * 登录
	 */
	@PostMapping("/sys/login")
	public Map<String, Object> login(@RequestBody SysLoginForm form)throws IOException {
		/*boolean captcha = sysCaptchaService.validate(form.getUuid(), form.getCaptcha());
		if(!captcha){
			return R.error("验证码不正确");
		}*/

		//用户信息
		SysUserEntity user = sysUserService.queryByUserName(form.getUsername());

		if(user == null){
			return R.error("账号未授权，无法登录");
//			Map<String, Object> map = new LdapUserAuth().ldapUserAuth(form.getUsername(),form.getPassword());
//			if(map.get("code").equals(100)){
//				user=sysUserService.saveLdap(form.getUsername());
//			}else{
//				return R.error(map.get("msg").toString());
//			}
		}else{
			//判断用户类型
			if(user.getType()==2){
				Map<String, Object> map = new LdapUserAuth().ldapUserAuth(form.getUsername(),form.getPassword());
				if(!map.get("code").equals(100)){
					return R.error(map.get("msg").toString());
				}
			}else{
				
				//add by owen in 20250612 解密
				String encryptedData = form.getPassword(); // Base64格式的加密数据
			    String key = "jk%fdsa2QERX_2+2"; // 16字节密钥（128位）
			    String iv = "1iopi7&FDS123456"; // 16字节IV
			    
			    String decrypted = "";
			    try {
			        decrypted = AESDecryptorUtil.decrypt(encryptedData, key, iv);
			        System.out.println("解密结果：" + decrypted); // 输出：Hello World!
			    } catch (Exception e) {
			        e.printStackTrace();
			    }
				
				
				if(!user.getPassword().equals(new Sha256Hash(decrypted, user.getSalt()).toHex())){
					return R.error("账号或密码不正确");
				}
				//账号锁定
				if(user.getStatus() == 0){
					return R.error("账号已被锁定,请联系管理员");
				}
			}
		}

		//生成token，并保存到数据库
		R r = sysUserTokenService.createToken(user.getUserId());
		Map<String,Object> paramsMap = new HashMap<String,Object>();
		paramsMap.put("userName", r.get("token"));
		paramsMap.put("subSysAlias","ZGINVEST");
		paramsMap.put("remark","login");
		httpUtil.sendGet(logUrl, paramsMap);
		return r;
	}


	/**
	 * 退出
	 */
	@SysLog("退出系统")
	@PostMapping("/sys/logout")
	public R logout() {
		sysUserTokenService.logout(getUserId());
		return R.ok();
	}
	
}
