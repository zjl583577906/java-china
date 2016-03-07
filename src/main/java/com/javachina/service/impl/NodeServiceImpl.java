package com.javachina.service.impl;

import java.util.List;
import com.blade.jdbc.AR;
import com.blade.jdbc.Page;
import com.blade.jdbc.QueryParam;
import com.blade.ioc.annotation.Service;
import com.javachina.model.Node;
import com.javachina.service.NodeService;

@Service
public class NodeServiceImpl implements NodeService {
	
	@Override
	public Node getNode(Integer nid) {
		return AR.findById(Node.class, nid);
	}
		
	@Override
	public List<Node> getNodeList(QueryParam queryParam) {
		if(null != queryParam){
			return AR.find(queryParam).list(Node.class);
		}
		return null;
	}
	
	@Override
	public Page<Node> getPageList(QueryParam queryParam) {
		if(null != queryParam){
			return AR.find(queryParam).page(Node.class);
		}
		return null;
	}
	
	@Override
	public boolean save( Integer pid, String title, String description, String slug, Integer topics, Integer createTime, Integer isDel ) {
		return false;
	}
	
	@Override
	public boolean delete(Integer nid) {
		if(null != nid){
			AR.update("delete from t_node where nid = ?", nid).commit();
			return true;
		}
		return false;
	}
		
}
