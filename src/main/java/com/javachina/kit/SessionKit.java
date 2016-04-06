package com.javachina.kit;

import com.blade.context.BladeWebContext;
import com.blade.web.http.wrapper.Session;
import com.javachina.Constant;
import com.javachina.model.LoginUser;

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
}
