package com.javachina.controller;

import java.util.HashMap;
import java.util.Map;

import com.blade.view.template.ModelAndView;

public class BaseController {
	
	final String ERROR = "error";
	final String INFO = "info";
	final String SUCCESS = "success";
	final String FAILURE = "failure";
	final String STATUS = "status";
	
	final int count = 20;
	
	public ModelAndView getView(String view){
		return getView(new HashMap<String, Object>(), view);
	}
	
	public ModelAndView getView(Map<String, Object> map, String view){
		return new ModelAndView(map, view + ".html");
	}
	
	public ModelAndView getAdminView(String view){
		return this.getView("/admin/" + view);
	}
	
	public ModelAndView getAdminView(Map<String, Object> map, String view){
		return this.getView(map, "/admin/" + view);
	}
	
}
