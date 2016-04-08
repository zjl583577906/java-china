package com.javachina.service.impl;

import java.util.List;

import com.blade.ioc.annotation.Service;
import com.blade.jdbc.AR;
import com.blade.jdbc.QueryParam;
import com.javachina.model.Userlog;
import com.javachina.service.UserlogService;

import blade.kit.DateKit;

@Service
public class UserlogServiceImpl implements UserlogService {
	
	@Override
	public List<Userlog> getUserlogList(QueryParam queryParam) {
		if(null != queryParam){
			return AR.find(queryParam).list(Userlog.class);
		}
		return null;
	}
	
	@Override
	public boolean save(Integer uid, String action, String content) {
		try {
			AR.update("insert into t_userlog(uid, action, content, create_time) values(?, ?, ?, ?)",
					uid, action, content, DateKit.getCurrentUnixTime()).executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
}