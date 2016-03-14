package com.javachina.service;

import java.util.List;
import java.util.Map;

import com.blade.jdbc.Page;
import com.blade.jdbc.QueryParam;

import com.javachina.model.Topic;

public interface TopicService {
	
	public Topic getTopic(Integer tid);
	
	public List<Map<String, Object>> getTopicList(QueryParam queryParam);
	
	public Page<Map<String, Object>> getPageList(QueryParam queryParam);
	
	public boolean save( Integer uid, Integer nid, String title, String content, Integer views, Integer favorites, Integer stars, Integer comments, Integer isTop, Integer createTime, Integer updateTime, Integer status );
	
	public boolean delete(Integer tid);
		
}
