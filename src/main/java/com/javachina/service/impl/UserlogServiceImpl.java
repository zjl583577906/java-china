package com.javachina.service.impl;

import java.util.List;
import com.blade.jdbc.AR;
import com.blade.jdbc.Page;
import com.blade.jdbc.QueryParam;
import com.blade.ioc.annotation.Service;
import com.javachina.model.Userlog;
import com.javachina.service.UserlogService;

@Service
public class UserlogServiceImpl implements UserlogService {
	
	@Override
	public Userlog getUserlog(Integer id) {
		return AR.findById(Userlog.class, id);
	}
		
	@Override
	public List<Userlog> getUserlogList(QueryParam queryParam) {
		if(null != queryParam){
			return AR.find(queryParam).list(Userlog.class);
		}
		return null;
	}
	
	@Override
	public Page<Userlog> getPageList(QueryParam queryParam) {
		if(null != queryParam){
			return AR.find(queryParam).page(Userlog.class);
		}
		return null;
	}
	
	@Override
	public boolean save( Integer uid, String action, String content, Integer createTime ) {
		return false;
	}
	
	@Override
	public boolean delete(Integer id) {
		if(null != id){
			AR.update("delete from t_userlog where id = ?", id).commit();
			return true;
		}
		return false;
	}
		
}
