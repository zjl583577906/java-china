package com.javachina.service;

import java.util.List;

import com.blade.jdbc.Page;
import com.blade.jdbc.QueryParam;

import com.javachina.model.Settings;

public interface SettingsService {
	
	Settings getSettings(String skey);
	
	List<Settings> getSettingsList(QueryParam queryParam);
	
	Page<Settings> getPageList(QueryParam queryParam);
	
	boolean save( String svalue );
	
	boolean delete(String skey);
		
}
