package com.javachina.service;

import java.util.List;

import com.blade.jdbc.Page;
import com.blade.jdbc.QueryParam;

import com.javachina.model.Userlog;

public interface UserlogService {
	
	public Userlog getUserlog(Integer id);
	
	public List<Userlog> getUserlogList(QueryParam queryParam);
	
	public Page<Userlog> getPageList(QueryParam queryParam);
	
	public boolean save( Integer uid, String action, String content, Integer createTime );
	
	public boolean delete(Integer id);
		
}
