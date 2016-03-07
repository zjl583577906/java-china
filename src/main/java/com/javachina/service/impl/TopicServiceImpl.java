package com.javachina.service.impl;

import java.util.List;
import com.blade.jdbc.AR;
import com.blade.jdbc.Page;
import com.blade.jdbc.QueryParam;
import com.blade.ioc.annotation.Service;
import com.javachina.model.Topic;
import com.javachina.service.TopicService;

@Service
public class TopicServiceImpl implements TopicService {
	
	@Override
	public Topic getTopic(Integer tid) {
		return AR.findById(Topic.class, tid);
	}
		
	@Override
	public List<Topic> getTopicList(QueryParam queryParam) {
		if(null != queryParam){
			return AR.find(queryParam).list(Topic.class);
		}
		return null;
	}
	
	@Override
	public Page<Topic> getPageList(QueryParam queryParam) {
		if(null != queryParam){
			return AR.find(queryParam).page(Topic.class);
		}
		return null;
	}
	
	@Override
	public boolean save( Integer uid, Integer nid, String title, String content, Integer views, Integer favorites, Integer stars, Integer comments, Integer isTop, Integer createTime, Integer updateTime, Integer status ) {
		return false;
	}
	
	@Override
	public boolean delete(Integer tid) {
		if(null != tid){
			AR.update("delete from t_topic where tid = ?", tid).commit();
			return true;
		}
		return false;
	}
		
}
