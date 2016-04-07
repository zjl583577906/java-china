package com.javachina.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.blade.ioc.annotation.Service;
import com.blade.jdbc.AR;
import com.javachina.model.Settings;
import com.javachina.service.SettingsService;

import blade.kit.CollectionKit;
import blade.kit.StringKit;

@Service
public class SettingsServiceImpl implements SettingsService {
	
	@Override
	public Settings getSettings(String skey) {
		return AR.findById(Settings.class, skey);
	}
	
	@Override
	public boolean save( String svalue ) {
		return false;
	}
	
	@Override
	public boolean delete(String skey) {
		if(null != skey){
			AR.update("delete from t_settings where skey = ?", skey).executeUpdate();
			return true;
		}
		return false;
	}

	@Override
	public Map<String, Object> getSystemInfo() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Settings> settings = AR.find("select * from t_settings").cache(false).list(Settings.class);
		if(CollectionKit.isNotEmpty(settings)){
			for(Settings setting : settings){
				map.put(setting.getSkey(), setting.getSvalue());
			}
		}
		return map;
	}

	@Override
	public void updateCount(String skey, int count) {
		try {
			if (StringKit.isNotBlank(skey) && count != 0) {
				Settings settings = AR.findById(Settings.class, skey);
				if (null != settings) {
					if (StringKit.isNumber(settings.getSvalue().trim())) {
						Long cur_count = Long.valueOf(settings.getSvalue().trim());
						String val = (cur_count + count) + "";
						AR.update("update t_settings set svalue = ? where skey = ?", val, skey).executeUpdate();
					}
				}
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
}
