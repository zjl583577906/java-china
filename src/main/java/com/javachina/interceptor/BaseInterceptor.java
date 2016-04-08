package com.javachina.interceptor;

import javax.servlet.http.HttpServletRequest;

import com.blade.interceptor.Interceptor;
import com.blade.interceptor.annotation.Intercept;
import com.blade.web.http.Request;
import com.blade.web.http.Response;
import com.javachina.Constant;
import com.javachina.kit.SessionKit;
import com.javachina.model.LoginUser;

import blade.kit.logging.Logger;
import blade.kit.logging.LoggerFactory;

@Intercept
public class BaseInterceptor implements Interceptor {
	
	private static final Logger LOGGE = LoggerFactory.getLogger(BaseInterceptor.class);
	
	@Override
	public boolean before(Request request, Response response) {
		
		LOGGE.info("用户访问地址 >>> " + request.raw().getRequestURI() + ", 来路地址  >>> " + getIpAddr(request.raw()));
		
		request.attribute("base", request.contextPath());
		request.attribute("version", Constant.APP_VERSION);
		request.attribute("cdn", Constant.CDN_URL);
		
		String uri = request.uri();
		
		if(uri.indexOf("/admin/") != -1){
			LoginUser user = SessionKit.getLoginUser();
			if(null == user){
				response.go("/signin");
				return false;
			}
		}
		
		return true;
	}
	
	public String getIpAddr(HttpServletRequest request) {
		if (null == request) {
			return "0.0.0.0";
		}
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	@Override
	public boolean after(Request request, Response response) {
		return true;
	}
	
}
