package com.javachina.service;

import com.javachina.model.Activecode;

public interface ActivecodeService {
	
	Activecode getActivecode(String code, String type);
	
	String save(Integer uid, String type);
	
	boolean useCode(String code, String type);
	
}
