package com.javachina.service;

import java.util.List;

import com.blade.jdbc.Page;
import com.blade.jdbc.QueryParam;

import com.javachina.model.Favorite;

public interface FavoriteService {
	
	Favorite getFavorite(Integer id);
	
	List<Favorite> getFavoriteList(QueryParam queryParam);
	
	Page<Favorite> getPageList(QueryParam queryParam);
	
	boolean save( String type, Integer uid, Integer eventId, Integer createTime );
	
	boolean delete(Integer id);
		
}
