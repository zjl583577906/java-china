package com.javachina;

import java.util.Map;

import com.blade.Blade;
import com.blade.Bootstrap;
import com.blade.context.BladeWebContext;
import com.blade.ioc.annotation.Inject;
import com.blade.jdbc.DB;
import com.blade.jdbc.cache.memory.FIFOCache;
import com.bladejava.view.template.JetbrickTemplateEngine;
import com.javachina.ext.Funcs;
import com.javachina.ext.Methods;
import com.javachina.service.SettingsService;

import jetbrick.template.resolver.GlobalResolver;

public class App extends Bootstrap {
	
	@Inject
	private SettingsService settingsService;
	
	@Override
	public void init(Blade blade) {
		// 模板引擎
		JetbrickTemplateEngine jetbrickTemplateEngine = new JetbrickTemplateEngine(BladeWebContext.servletContext());
		
		GlobalResolver resolver = jetbrickTemplateEngine.getJetEngine().getGlobalResolver();
		resolver.registerFunctions(Funcs.class);
		resolver.registerMethods(Methods.class);
		Constant.VIEW_CONTEXT = jetbrickTemplateEngine.getJetEngine().getGlobalContext();
		blade.viewEngin(jetbrickTemplateEngine);
		
		// 配置数据库
		DB.open(blade.config().get("jdbc.driver"), 
				blade.config().get("jdbc.url"), 
				blade.config().get("jdbc.user"), 
				blade.config().get("jdbc.pass"), true);
		
		// 开启数据库缓存
		if(blade.config().getAsBoolean("app.db_cahce")){
			DB.setCache(new FIFOCache());
		}
		
		// 初始化配置
		Constant.SITE_URL = blade.config().get("app.site_url");
		Constant.APP_VERSION = blade.config().get("app.version");
		Constant.AES_SALT = blade.config().get("app.aes_salt");
		Constant.CDN_URL = blade.config().get("qiniu.cdn");
		
		// 配置邮箱
		Constant.MAIL_HOST = blade.config().get("app.mail.host");
		Constant.MAIL_NICK = blade.config().get("app.mail.nick");
		Constant.MAIL_USER = blade.config().get("app.mail.user");
		Constant.MAIL_PASS = blade.config().get("app.mail.pass");
		Constant.MAIL_ADMIN = blade.config().get("app.mail.admin");
	}
	
	@Override
	public void contextInitialized(Blade blade) {
		Constant.SYS_INFO = settingsService.getSystemInfo();
		Constant.VIEW_CONTEXT.set(Map.class, "sys_info", Constant.SYS_INFO);
	}
}
