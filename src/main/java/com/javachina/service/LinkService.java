package com.javachina.service;

import java.util.List;

import com.blade.jdbc.Page;
import com.blade.jdbc.QueryParam;

import com.javachina.model.Link;

public interface LinkService {
	
	public Link getLink(Integer id);
	
	public List<Link> getLinkList(QueryParam queryParam);
	
	public Page<Link> getPageList(QueryParam queryParam);
	
	public boolean save( String title, String url, Integer createTime );
	
	public boolean delete(Integer id);
		
}
