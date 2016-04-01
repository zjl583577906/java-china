package com.javachina.service;

import java.util.List;

import com.blade.jdbc.Page;
import com.blade.jdbc.QueryParam;

import com.javachina.model.Userlog;

public interface UserlogService {
	
	Userlog getUserlog(Integer id);
	
	List<Userlog> getUserlogList(QueryParam queryParam);
	
	Page<Userlog> getPageList(QueryParam queryParam);
	
	boolean save( Integer uid, String action, String content, Integer createTime );
	
	boolean delete(Integer id);
		
}
