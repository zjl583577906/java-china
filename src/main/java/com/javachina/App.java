package com.javachina;

import com.blade.Blade;
import com.blade.Bootstrap;
import com.blade.context.BladeWebContext;
import com.bladejava.view.template.JetbrickTemplateEngine;

import blade.kit.MailKit;

public class App extends Bootstrap {
	
	@Override
	public void init(Blade blade) {
		// 模板引擎
		blade.viewEngin(new JetbrickTemplateEngine(BladeWebContext.servletContext()));
		
		// 配置邮箱管理员
		MailKit.config( blade.config().get("app.mail.smtp"), 
						blade.config().get("app.mail.user"), 
						blade.config().get("app.mail.pass"));
	}
	
}
