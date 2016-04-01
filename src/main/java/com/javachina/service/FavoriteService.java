package com.javachina.service;

import java.util.List;

import com.blade.jdbc.Page;
import com.blade.jdbc.QueryParam;

import com.javachina.model.Favorite;

public interface FavoriteService {
	
	boolean isFavorite(String type, Long uid, Long event_id);
	
	List<Favorite> getFavoriteList(QueryParam queryParam);
	
	Page<Favorite> getPageList(QueryParam queryParam);
	
	Long favorite(String type, Long uid, Long eventId);
		
}
