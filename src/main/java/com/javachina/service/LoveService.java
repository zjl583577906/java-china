package com.javachina.service;

import com.javachina.model.Love;

public interface LoveService {
	
	Love getLove(Long uid, Long tid);
	
	boolean love(Long uid, Long tid);
	
}
