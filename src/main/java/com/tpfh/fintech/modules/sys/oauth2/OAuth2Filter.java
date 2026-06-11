package com.tpfh.fintech.modules.sys.oauth2;

import java.io.IOException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tpfh.fintech.common.utils.HttpContextUtils;
import com.tpfh.fintech.common.utils.JsonUtil;
import com.tpfh.fintech.common.utils.R;
import com.tpfh.fintech.modules.sys.entity.SysUserEntity;

/**
 * oauth2过滤器
 *
 * @author tpfh
 * @email tpfh@tpfh.com
 * @date 2017-05-20 13:00
 */
public class OAuth2Filter extends AuthenticatingFilter {

    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception {
        //获取请求token
        String token = getRequestToken((HttpServletRequest) request);

        if(StringUtils.isBlank(token)){
            return null;
        }

        return new OAuth2Token(token);
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if(((HttpServletRequest) request).getMethod().equals(RequestMethod.OPTIONS.name())){
            return true;
        }

        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        //获取请求token，如果token不存在，直接返回401
        String token = getRequestToken((HttpServletRequest) request);
        if(StringUtils.isBlank(token)){
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
            httpResponse.setHeader("Access-Control-Allow-Origin", HttpContextUtils.getOrigin());

            String json = JsonUtil.getJsonByObj(R.error(HttpStatus.UNAUTHORIZED.value(), "invalid token"));

            httpResponse.getWriter().print(json);

            return false;
        }

        boolean loggedIn = executeLogin(request, response);
        if (!loggedIn) {
            return false;
        }

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        Subject subject = SecurityUtils.getSubject();
        Object principal = subject.getPrincipal();
        if (principal instanceof SysUserEntity) {
            SysUserEntity user = (SysUserEntity) principal;
            if (user.getStatus() != null && user.getStatus() == 2 && !isInitUserAllowedPath(httpRequest)) {
                HttpServletResponse httpResponse = (HttpServletResponse) response;
                httpResponse.setContentType("application/json;charset=utf-8");
                httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
                httpResponse.setHeader("Access-Control-Allow-Origin", HttpContextUtils.getOrigin());
                String json = JsonUtil.getJsonByObj(R.error(HttpStatus.FORBIDDEN.value(), "首次登录须修改密码"));
                httpResponse.getWriter().print(json);
                return false;
            }
        }
        return true;
    }

    private boolean isInitUserAllowedPath(HttpServletRequest request) {
        String path = request.getServletPath();
        if (StringUtils.isBlank(path)) {
            path = request.getRequestURI();
        }
        return "/sys/user/info".equals(path)
                || "/sys/user/password".equals(path)
                || "/sys/logout".equals(path);
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setContentType("application/json;charset=utf-8");
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpResponse.setHeader("Access-Control-Allow-Origin", HttpContextUtils.getOrigin());
        try {
            //处理登录失败的异常
            Throwable throwable = e.getCause() == null ? e : e.getCause();
            R r = R.error(HttpStatus.UNAUTHORIZED.value(), throwable.getMessage());

            String json = JsonUtil.getJsonByObj(r);
            httpResponse.getWriter().print(json);
        } catch (IOException e1) {

        }

        return false;
    }

    /**
     * 获取请求的token
     */
    private String getRequestToken(HttpServletRequest httpRequest){
        //从header中获取token
        String token = httpRequest.getHeader("token");

        //如果header中不存在token，则从参数中获取token
        if(StringUtils.isBlank(token)){
            token = httpRequest.getParameter("token");
        }
       /* try {
			BufferedReader br = httpRequest.getReader();
			String str, wholeStr = "";
			while((str = br.readLine()) != null){
			wholeStr += str;
			}
			System.out.println(wholeStr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

        return token;
    }


}
