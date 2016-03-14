package com.javachina.service;

import java.util.List;
import java.util.Map;

import com.blade.jdbc.Page;
import com.blade.jdbc.QueryParam;

import com.javachina.model.Node;

public interface NodeService {
	
	Node getNode(Integer nid);
	
	Node getNode(QueryParam queryParam);
	
	Map<String, Object> getNodeDetail(Integer nid);
	
	List<Node> getNodeList(QueryParam queryParam);
	
	Page<Node> getPageList(QueryParam queryParam);
	
	boolean save(Integer pid, String title, String description, String slug, Integer topics, Integer createTime, Integer isDel );
	
	boolean delete(Integer nid);
		
}