package com.javachina.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.blade.ioc.annotation.Inject;
import com.blade.ioc.annotation.Service;
import com.blade.jdbc.AR;
import com.blade.jdbc.Page;
import com.blade.jdbc.QueryParam;
import com.javachina.kit.ImageKit;
import com.javachina.model.Comment;
import com.javachina.model.Node;
import com.javachina.model.Topic;
import com.javachina.model.User;
import com.javachina.service.CommentService;
import com.javachina.service.NodeService;
import com.javachina.service.TopicService;
import com.javachina.service.UserService;

@Service
public class TopicServiceImpl implements TopicService {
	
	@Inject
	private UserService userService;
	
	@Inject
	private NodeService nodeService;
	
	@Inject
	private CommentService commentService;
	
	@Override
	public Topic getTopic(Integer tid) {
		return AR.findById(Topic.class, tid);
	}
	
	@Override
	public List<Map<String, Object>> getTopicList(QueryParam queryParam) {
		if(null != queryParam){
			List<Topic> topics = AR.find(queryParam).list(Topic.class);
			return this.getTopicListMap(topics);
		}
		return null;
	}
	
	@Override
	public Page<Map<String, Object>> getPageList(QueryParam queryParam) {
		if(null != queryParam){
			Page<Topic> topicPage = AR.find(queryParam).page(Topic.class);
			return this.getTopicPageMap(topicPage);
		}
		return null;
	}
	
	private List<Map<String, Object>> getTopicListMap(List<Topic> topics){
		List<Map<String, Object>> topicMaps = new ArrayList<Map<String,Object>>();
		if(null != topics && topics.size() > 0){
			for(Topic topic : topics){
				
				Long tid = topic.getTid();
				Long uid = topic.getUid();
				Long nid = topic.getNid();
				Long comments = topic.getComments();
				
				User user = userService.getUser(uid);
				Node node = nodeService.getNode(nid);
				
				if(null == user || null == node){
					continue;
				}
				
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("tid", tid);
				map.put("views", topic.getViews());
				map.put("stars", topic.getStars());
				map.put("favorites", topic.getFavorites());
				map.put("comments", comments);
				map.put("title", topic.getTitle());
				map.put("create_time", topic.getCreate_time());
				map.put("update_time", topic.getUpdate_time());
				map.put("user_name", user.getLogin_name());
				
				String avatar = ImageKit.getAvatar(user.getAvatar());
				
				map.put("avatar", avatar);
				map.put("node_name", node.getTitle());
				map.put("node_slug", node.getSlug());
				
				if(comments > 0){
					Comment comment = commentService.getTopicLastComment(tid);
					if(null != comment){
						User reply_user = userService.getUser(comment.getUid());
						map.put("reply_name", reply_user.getLogin_name());
					}
				}
				
				topicMaps.add(map);
			}
		}
		return topicMaps;
	}
	
	private Page<Map<String, Object>> getTopicPageMap(Page<Topic> topicPage){
		
		long totalCount = topicPage.getTotalCount();
		int page = topicPage.getPage();
		int pageSize = topicPage.getPageSize();
		Page<Map<String, Object>> result = new Page<Map<String,Object>>(totalCount, page, pageSize);
		
		List<Topic> topics = topicPage.getResults();
		List<Map<String, Object>> topicMaps = this.getTopicListMap(topics);
		result.setResults(topicMaps);
		
		return result;
	}
	
	@Override
	public boolean save( Integer uid, Integer nid, String title, String content, Integer views, Integer favorites, Integer stars, Integer comments, Integer isTop, Integer createTime, Integer updateTime, Integer status ) {
		return false;
	}
	
	@Override
	public boolean delete(Integer tid) {
		if(null != tid){
			AR.update("delete from t_topic where tid = ?", tid).executeUpdate();
			return true;
		}
		return false;
	}
		
}
