package com.javachina.service;

import java.util.Map;

import com.blade.jdbc.Page;

public interface FavoriteService {
	
	boolean isFavorite(String type, Long uid, Long event_id);
	
	// 查询我收藏的帖子
	Page<Map<String, Object>> getFavorites(Long uid, Integer page, Integer count);
	
	// 查询我关注的用户
	Page<Map<String, Object>> getFollowing(Long uid, Integer page, Integer count);
	
	Long save(String type, Long uid, Long eventId);
	
	Long favorites(String type, Long uid);
}
