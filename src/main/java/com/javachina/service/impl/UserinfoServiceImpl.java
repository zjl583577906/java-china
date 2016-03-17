package com.javachina.service.impl;

import java.util.List;
import com.blade.jdbc.AR;
import com.blade.jdbc.Page;
import com.blade.jdbc.QueryParam;
import com.blade.ioc.annotation.Service;
import com.javachina.model.Userinfo;
import com.javachina.service.UserinfoService;

@Service
public class UserinfoServiceImpl implements UserinfoService {
	
	@Override
	public Userinfo getUserinfo(Integer uid) {
		return AR.findById(Userinfo.class, uid);
	}
		
	@Override
	public List<Userinfo> getUserinfoList(QueryParam queryParam) {
		if(null != queryParam){
			return AR.find(queryParam).list(Userinfo.class);
		}
		return null;
	}
	
	@Override
	public Page<Userinfo> getPageList(QueryParam queryParam) {
		if(null != queryParam){
			return AR.find(queryParam).page(Userinfo.class);
		}
		return null;
	}
	
	@Override
	public boolean save(Integer uid) {
		if(null == uid){
			return false;
		}
		try {
			AR.update("insert into t_userinfo(uid) values(?)", uid).executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public boolean update( String nickName, String webSite, String github, String email, String signature, String instructions ) {
		return false;
	}
	
	@Override
	public boolean delete(Integer uid) {
		if(null != uid){
			AR.update("delete from t_userinfo where uid = ?", uid).executeUpdate();
			return true;
		}
		return false;
	}

}
