package com.javachina.service;

import java.util.List;

import com.blade.jdbc.Page;
import com.blade.jdbc.QueryParam;

import com.javachina.model.Userinfo;

public interface UserinfoService {
	
	Userinfo getUserinfo(Long uid);
	
	List<Userinfo> getUserinfoList(QueryParam queryParam);
	
	Page<Userinfo> getPageList(QueryParam queryParam);
	
	boolean save(Long uid);
	
	boolean update(String nickName, String webSite, String github, String email, String signature, String instructions );
	
	boolean delete(Long uid);
		
}
