package com.javachina;

import com.blade.Blade;
import com.blade.Bootstrap;
import com.blade.context.BladeWebContext;
import com.bladejava.view.template.JetbrickTemplateEngine;

public class App extends Bootstrap {
	
	@Override
	public void init(Blade blade) {
		blade.viewEngin(new JetbrickTemplateEngine(BladeWebContext.servletContext()));
	}

}
