package com.javachina.interceptor;

import com.blade.interceptor.Interceptor;
import com.blade.interceptor.annotation.Intercept;
import com.blade.web.http.Request;
import com.blade.web.http.Response;

@Intercept
public class BaseInterceptor implements Interceptor {
	
	@Override
	public boolean before(Request request, Response response) {
		request.attribute("base", request.contextPath());
		return true;
	}

	@Override
	public boolean after(Request request, Response response) {
		return true;
	}
	
}
