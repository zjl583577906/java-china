package com.javachina.kit;

import com.blade.context.BladeWebContext;
import com.blade.web.http.Request;
import com.blade.web.http.Response;
import com.blade.web.http.wrapper.Session;
import com.javachina.Constant;
import com.javachina.model.LoginUser;

import blade.kit.StringKit;

public class SessionKit {

	public static void setLoginUser(Session session, LoginUser login_user){
		if(null != session && null != login_user){
			removeUser(session);
			session.attribute(Constant.LOGIN_SESSION_KEY, login_user);
		}
	}
	
	public static void removeUser(Session session){
		session.removeAttribute(Constant.LOGIN_SESSION_KEY);
	}
	
	public static LoginUser getLoginUser() {
		Session session = BladeWebContext.session();
		if(null == session){
			return null;
		}
		LoginUser user = session.attribute(Constant.LOGIN_SESSION_KEY);
		return user;
	}
	
	public static void setCookie(Response response, String cookieName, Long uid) {
		if(null != response && StringKit.isNotBlank(cookieName) && null != uid){
			String val = AES.encrypt(uid+"");
			boolean isSSL = Constant.SITE_URL.startsWith("https");
			response.cookie(cookieName, val, 604800, isSSL);
		}
	}
	
	public static String getCookie(Request request, String cookieName) {
		if(null != request && StringKit.isNotBlank(cookieName)){
			String val = request.cookie(cookieName);
			if(StringKit.isNotBlank(val)){
				return AES.decrypt(val);
			}
		}
		return null;
	}
	
}
