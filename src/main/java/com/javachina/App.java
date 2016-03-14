package com.javachina;

import com.blade.Blade;
import com.blade.Bootstrap;
import com.blade.context.BladeWebContext;
import com.blade.jdbc.DB;
import com.bladejava.view.template.JetbrickTemplateEngine;
import com.javachina.ext.Funcs;
import com.javachina.ext.Methods;

import blade.kit.MailKit;
import jetbrick.template.resolver.GlobalResolver;

public class App extends Bootstrap {
	
	@Override
	public void init(Blade blade) {
		// 模板引擎
		JetbrickTemplateEngine jetbrickTemplateEngine = new JetbrickTemplateEngine(BladeWebContext.servletContext());
		
		GlobalResolver resolver = jetbrickTemplateEngine.getJetEngine().getGlobalResolver();
		resolver.registerFunctions(Funcs.class);
		resolver.registerMethods(Methods.class);
		
		blade.viewEngin(jetbrickTemplateEngine);
		
		// 配置邮箱管理员
		MailKit.config( blade.config().get("app.mail.smtp"), 
						blade.config().get("app.mail.user"), 
						blade.config().get("app.mail.pass"));
		
		// 配置数据库
		DB.open(blade.config().get("jdbc.driver"), 
				blade.config().get("jdbc.url"), 
				blade.config().get("jdbc.user"), 
				blade.config().get("jdbc.pass"), true);
		
		// 初始化配置
		Constant.SITE_URL = blade.config().get("app.site_url");
	}
	
}
