package com.javachina.service;

import com.javachina.model.Activecode;
import com.javachina.model.User;

public interface ActivecodeService {
	
	Activecode getActivecode(String code, String type);
	
	String save(User user, String type);
	
	boolean useCode(Long code, String type);
	
}
