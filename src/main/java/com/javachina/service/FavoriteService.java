package com.javachina.service;

import java.util.List;

import com.blade.jdbc.Page;
import com.blade.jdbc.QueryParam;

import com.javachina.model.Favorite;

public interface FavoriteService {
	
	public Favorite getFavorite(Integer id);
	
	public List<Favorite> getFavoriteList(QueryParam queryParam);
	
	public Page<Favorite> getPageList(QueryParam queryParam);
	
	public boolean save( String type, Integer uid, Integer eventId, Integer createTime );
	
	public boolean delete(Integer id);
		
}
