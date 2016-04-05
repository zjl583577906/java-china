package com.javachina;

import java.util.Map;

import jetbrick.template.JetGlobalContext;

public class Constant {

	// 登录用户session key
	public static final String LOGIN_SESSION_KEY = "login_user";
	
	public static String SITE_URL = "";
	public static String CDN_URL = "";
	public static String APP_VERSION = "";
	
	public static String MAIL_HOST;
	public static String MAIL_NICK;
	public static String MAIL_USER;
	public static String MAIL_PASS;
	public static String MAIL_ADMIN;

	public static JetGlobalContext VIEW_CONTEXT = null;
	public static Map<String, Object> SYS_INFO = null;
	
}
