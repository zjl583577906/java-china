package com.javachina.service;

import java.util.List;

import com.blade.jdbc.Page;
import com.blade.jdbc.QueryParam;

import com.javachina.model.Userinfo;

public interface UserinfoService {
	
	public Userinfo getUserinfo(Long uid);
	
	public List<Userinfo> getUserinfoList(QueryParam queryParam);
	
	public Page<Userinfo> getPageList(QueryParam queryParam);
	
	public boolean save(Long uid);
	
	public boolean update(String nickName, String webSite, String github, String email, String signature, String instructions );
	
	public boolean delete(Long uid);
		
}
