package com.javachina.service.impl;

import com.blade.ioc.annotation.Service;
import com.blade.jdbc.AR;
import com.javachina.model.Activecode;
import com.javachina.service.ActivecodeService;

import blade.kit.DateKit;
import blade.kit.StringKit;

@Service
public class ActivecodeServiceImpl implements ActivecodeService {
	
	@Override
	public Activecode getActivecode(String code, String type) {
		if(StringKit.isBlank(code) || StringKit.isBlank(type)){
			return null;
		}
		return AR.find("select * from t_activecode where type = ? and code = ? and is_use = ?", type, code, 0)
				.first(Activecode.class);
	}
	
	public Activecode getActivecodeById(Integer id) {
		if(null == id){
			return null;
		}
		return AR.findById(Activecode.class, id);
	}
		
	@Override
	public String save(Integer uid, String type) {
		if(null == uid || StringKit.isBlank(type)){
			return null;
		}
		
		int time = DateKit.getCurrentUnixTime();
		Activecode activecode = AR.find("select * from t_activecode where type = ? and expires_time > ? and is_use = ?", type, time, 0)
		.first(Activecode.class);
		
		if(null == activecode){
			int expires_time = time + 3600;
			String code = StringKit.getRandomChar(32);
			try {
				AR.update("insert into t_activecode(uid, code, type, expires_time, create_time) values(?, ?, ?, ?, ?)",
						uid, code, type, expires_time, time).commit();
				return code;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}

	@Override
	public boolean useCode(String code, String type) {
		if(StringKit.isBlank(code) || StringKit.isBlank(type)){
			return false;
		}
		Activecode activecode = this.getActivecode(code, type);
		if(null == activecode){
			return false;
		}
		try {
			AR.update("update t_activecode set is_use = ? where id = ?", 1, activecode.getId()).commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
