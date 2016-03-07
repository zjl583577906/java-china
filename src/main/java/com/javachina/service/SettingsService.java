package com.javachina.service;

import java.util.List;

import com.blade.jdbc.Page;
import com.blade.jdbc.QueryParam;

import com.javachina.model.Settings;

public interface SettingsService {
	
	public Settings getSettings(String skey);
	
	public List<Settings> getSettingsList(QueryParam queryParam);
	
	public Page<Settings> getPageList(QueryParam queryParam);
	
	public boolean save( String svalue );
	
	public boolean delete(String skey);
		
}
