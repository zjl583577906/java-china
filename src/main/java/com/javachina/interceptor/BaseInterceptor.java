package com.javachina.interceptor;

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
		
		LOGGE.info("user addr >>> " + request.address());
		
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

	@Override
	public boolean after(Request request, Response response) {
		return true;
	}
	
}
