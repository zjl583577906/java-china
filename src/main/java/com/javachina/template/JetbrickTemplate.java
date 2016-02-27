package com.javachina.template;

import java.io.Writer;
import java.util.Map;
import java.util.Set;

import com.blade.context.BladeWebContext;
import com.blade.view.template.ModelAndView;
import com.blade.view.template.TemplateEngine;
import com.blade.web.http.Request;
import com.blade.web.http.wrapper.Session;

import jetbrick.template.JetContext;
import jetbrick.template.JetEngine;
import jetbrick.template.JetTemplate;
import jetbrick.template.TemplateException;
import jetbrick.template.web.JetWebEngine;

public class JetbrickTemplate implements TemplateEngine {

	private static JetEngine jetEngine;

	public JetbrickTemplate() {
	}
	
	public JetEngine getJetEngine() {
		return jetEngine;
	}

	@Override
	public void render(ModelAndView modelAndView, Writer writer) throws TemplateException {
		
		if(null == jetEngine){
			jetEngine = JetWebEngine.create(BladeWebContext.me().getContext());
		}
		
		JetTemplate template = jetEngine.getTemplate(modelAndView.getView());
		Map<String, Object> modelMap = modelAndView.getModel();
		JetContext context = new JetContext(modelMap.size());

		Request request = BladeWebContext.request();
		Session session = request.session();

		Set<String> attrs = request.attributes();
		if (null != attrs && attrs.size() > 0) {
			for (String attr : attrs) {
				context.put(attr, request.attribute(attr));
			}
		}

		Set<String> session_attrs = session.attributes();
		if (null != session_attrs && session_attrs.size() > 0) {
			for (String attr : session_attrs) {
				context.put(attr, session.attribute(attr));
			}
		}

		context.putAll(modelMap);
		template.render(context, writer);
	}

}