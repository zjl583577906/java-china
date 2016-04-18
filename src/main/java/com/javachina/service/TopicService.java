package com.javachina.service;

import java.util.List;
import java.util.Map;

import com.blade.jdbc.Page;
import com.blade.jdbc.QueryParam;
import com.javachina.model.Topic;

public interface TopicService {
	
	Topic getTopic(Long tid);
	
	Map<String, Object> getTopicMap(Topic topic, boolean isDetail);
	
	List<Map<String, Object>> getTopicList(QueryParam queryParam);
	
	Page<Map<String, Object>> getPageList(QueryParam queryParam);
	
	Long save(Long uid, Long nid, String title, String content, Integer isTop);
	
	Long update(Long tid, Long nid, String title, String content);
	
	boolean comment(Long uid, Long to_uid, Long tid, String content, String ua);
	
	boolean delete(Long tid);
	
	boolean updateCount(Long tid, String type, long count, boolean updateCount);

	Long getTopics(Long uid);

	Long getLastTime(Long uid);
	
}
