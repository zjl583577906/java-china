package com.javachina.service;

import java.util.Map;

import com.javachina.model.Settings;

public interface SettingsService {
	
	Settings getSettings(String skey);
	
	Map<String, Object> getSystemInfo();
	
	boolean save( String svalue );
	
	boolean delete(String skey);

	void updateCount(String skey, int count);
		
}
