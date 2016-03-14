package com.javachina.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.blade.ioc.annotation.Service;
import com.blade.jdbc.AR;
import com.blade.jdbc.Page;
import com.blade.jdbc.QueryParam;
import com.javachina.kit.ImageKit;
import com.javachina.model.Node;
import com.javachina.service.NodeService;

import blade.kit.StringKit;

@Service
public class NodeServiceImpl implements NodeService {
	
	@Override
	public Node getNode(Integer nid) {
		Node node = AR.findById(Node.class, nid);
		if(null != node && node.getIs_del() == 0){
			return node;
		}
		return null;
	}
	
	@Override
	public Node getNode(QueryParam queryParam) {
		return AR.find(queryParam).first(Node.class);
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

	@Override
	public Map<String, Object> getNodeDetail(Integer nid) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(null != nid){
			Node node = this.getNode(nid);
			if(null != node){
				map.put("node_name", node.getTitle());
				map.put("node_slug", node.getSlug());
				map.put("topic_count", node.getTopics());
				map.put("description", node.getDescription());
				if(StringKit.isNotBlank(node.getPic())){
					String pic = ImageKit.getImgURL(node.getPic());
					map.put("pic", pic);
				}
			}
		}
		return map;
	}
		
}
