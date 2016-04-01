package com.javachina.interceptor;

import com.blade.interceptor.Interceptor;
import com.blade.interceptor.annotation.Intercept;
import com.blade.web.http.Request;
import com.blade.web.http.Response;
import com.javachina.kit.SessionKit;
import com.javachina.model.User;

@Intercept
public class BaseInterceptor implements Interceptor {
	
	@Override
	public boolean before(Request request, Response response) {
		String uri = request.uri();
		
		if(uri.indexOf("/admin/") != -1){
			User user = SessionKit.getLoginUser();
			if(null == user){
				response.go("/signin");
				return false;
			}
		}
		
		request.attribute("base", request.contextPath());
		return true;
	}

	@Override
	public boolean after(Request request, Response response) {
		return true;
	}
	
}
