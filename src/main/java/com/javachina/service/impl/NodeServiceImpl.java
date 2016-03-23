package com.javachina.service.impl;

import java.util.ArrayList;
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
	public Node getNode(Long nid) {
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
	public List<Map<String, Object>> getNodeList() {
		List<Map<String, Object>> result= new ArrayList<Map<String,Object>>();
		QueryParam np = QueryParam.me();
		np.eq("is_del", 0).eq("pid", 0).orderby("topics desc");
		List<Node> parents = this.getNodeList(np);
		for(Node node : parents){
			Map<String, Object> nodeMap = this.getNodeDetail(node, null);
			if(null != nodeMap && !nodeMap.isEmpty()){
				List<Map<String, Object>> items = new ArrayList<Map<String,Object>>();
				QueryParam cp = QueryParam.me();
				cp.eq("is_del", 0).eq("pid", node.getPid()).orderby("topics desc");
				List<Node> nodes = this.getNodeList(cp);
				for(Node item : nodes){
					Map<String, Object> itemMap = this.getNodeDetail(item, null);
					if(null != itemMap && !itemMap.isEmpty()){
						items.add(itemMap);
					}
				}
				nodeMap.put("items", items);
				result.add(nodeMap);
			}
		}
		return result;
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
	public boolean save( Long pid, String title, String description, String slug, Long topics, Long createTime, Integer isDel ) {
		return false;
	}
	
	@Override
	public boolean delete(Long nid) {
		if(null != nid){
			AR.update("delete from t_node where nid = ?", nid).executeUpdate();
			return true;
		}
		return false;
	}

	@Override
	public Map<String, Object> getNodeDetail(Node node, Long nid) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(null == node){
			node = this.getNode(nid);
		}
		if(null != node){
			map.put("nid", node.getNid());
			map.put("node_name", node.getTitle());
			map.put("node_slug", node.getSlug());
			map.put("topic_count", node.getTopics());
			map.put("pid", node.getPid());
			map.put("description", node.getDescription());
			if(StringKit.isNotBlank(node.getPic())){
				String pic = ImageKit.getImgURL(node.getPic());
				map.put("pic", pic);
			}
		}
		return map;
	}
		
}
