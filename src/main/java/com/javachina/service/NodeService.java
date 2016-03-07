package com.javachina.service;

import java.util.List;

import com.blade.jdbc.Page;
import com.blade.jdbc.QueryParam;

import com.javachina.model.Node;

public interface NodeService {
	
	public Node getNode(Integer nid);
	
	public List<Node> getNodeList(QueryParam queryParam);
	
	public Page<Node> getPageList(QueryParam queryParam);
	
	public boolean save( Integer pid, String title, String description, String slug, Integer topics, Integer createTime, Integer isDel );
	
	public boolean delete(Integer nid);
		
}
