package com.javachina.service;

import java.util.List;
import java.util.Map;

import com.blade.jdbc.Page;
import com.blade.jdbc.QueryParam;

import com.javachina.model.Node;

public interface NodeService {
	
	Node getNode(Long nid);
	
	Node getNode(QueryParam queryParam);
	
	Map<String, Object> getNodeDetail(Long nid);
	
	List<Node> getNodeList(QueryParam queryParam);
	
	Page<Node> getPageList(QueryParam queryParam);
	
	boolean save(Long pid, String title, String description, String slug, Long topics, Long createTime, Integer isDel );
	
	boolean delete(Long nid);
		
}