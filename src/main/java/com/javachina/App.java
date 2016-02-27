package com.javachina;

import com.blade.Blade;
import com.blade.Bootstrap;
import com.javachina.template.JetbrickTemplate;

public class App extends Bootstrap {

	@Override
	public void init(Blade blade) {
		blade.basePackage("com.javachina");
		blade.routes("com.javachina.controller.admin");
		blade.viewEngin(new JetbrickTemplate());
	}

}
