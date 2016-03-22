package com.javachina.kit;

import com.blade.context.BladeWebContext;
import com.blade.web.http.wrapper.Session;
import com.javachina.Constant;
import com.javachina.model.User;

public class SessionKit {

	public static void setUser(Session session, User user){
		session.attribute(Constant.LOGIN_SESSION_KEY, user);
	}
	
	public static void removeUser(Session session){
		session.removeAttribute(Constant.LOGIN_SESSION_KEY);
	}

	public static User getLoginUser() {
		Session session = BladeWebContext.session();
		User user = session.attribute(Constant.LOGIN_SESSION_KEY);
		return user;
	}
}
