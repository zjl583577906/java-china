package com.javachina.service.impl;

import java.util.List;
import com.blade.jdbc.AR;
import com.blade.jdbc.Page;
import com.blade.jdbc.QueryParam;
import com.blade.ioc.annotation.Service;
import com.javachina.model.Settings;
import com.javachina.service.SettingsService;

@Service
public class SettingsServiceImpl implements SettingsService {
	
	@Override
	public Settings getSettings(String skey) {
		return AR.findById(Settings.class, skey);
	}
		
	@Override
	public List<Settings> getSettingsList(QueryParam queryParam) {
		if(null != queryParam){
			return AR.find(queryParam).list(Settings.class);
		}
		return null;
	}
	
	@Override
	public Page<Settings> getPageList(QueryParam queryParam) {
		if(null != queryParam){
			return AR.find(queryParam).page(Settings.class);
		}
		return null;
	}
	
	@Override
	public boolean save( String svalue ) {
		return false;
	}
	
	@Override
	public boolean delete(String skey) {
		if(null != skey){
			AR.update("delete from t_settings where skey = ?", skey).commit();
			return true;
		}
		return false;
	}
		
}
