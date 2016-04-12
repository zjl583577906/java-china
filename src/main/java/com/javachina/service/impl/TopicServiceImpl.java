package com.javachina.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.blade.ioc.annotation.Inject;
import com.blade.ioc.annotation.Service;
import com.blade.jdbc.AR;
import com.blade.jdbc.Page;
import com.blade.jdbc.QueryParam;
import com.javachina.ImageTypes;
import com.javachina.Types;
import com.javachina.kit.DateKit;
import com.javachina.kit.Utils;
import com.javachina.model.Comment;
import com.javachina.model.Node;
import com.javachina.model.Topic;
import com.javachina.model.User;
import com.javachina.service.CommentService;
import com.javachina.service.NodeService;
import com.javachina.service.NoticeService;
import com.javachina.service.SettingsService;
import com.javachina.service.TopicService;
import com.javachina.service.UserService;

import blade.kit.CollectionKit;
import blade.kit.StringKit;

@Service
public class TopicServiceImpl implements TopicService {
	
	@Inject
	private UserService userService;
	
	@Inject
	private NodeService nodeService;
	
	@Inject
	private CommentService commentService;
	
	@Inject
	private NoticeService noticeService;
	
	@Inject
	private SettingsService settingsService;
	
	@Override
	public Topic getTopic(Long tid) {
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
				Map<String, Object> map = this.getTopicMap(topic, false);
				if(null != map && !map.isEmpty()){
					topicMaps.add(map);
				}
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
	public Long save(Long uid, Long nid, String title, String content, Integer isTop) {
		
		try {
			Integer time = DateKit.getCurrentUnixTime();
			
			Long tid = (Long) AR
					.update("insert into t_topic(uid, nid, title, content, is_top, create_time, update_time, status) values(?, ?, ?, ?, ?, ?, ?, ?)",
							uid, nid, title, content, isTop, time, time, 1).key();
			
			// 更新我的发帖数
			nodeService.updateCount(nid, Types.topics.toString(), +1);
			
			// 更新节点下的帖子数
			userService.updateCount(uid, Types.topics.toString(), +1);
			
			// 更新总贴数
			settingsService.updateCount(Types.topic_count.toString(), +1);
			
			// 通知@的人
			if(null != tid){
				Set<String> atUsers = Utils.getAtUsers(content);
				if(CollectionKit.isNotEmpty(atUsers)){
					for(String user_name : atUsers){
						User user = userService.getUser(user_name);
						if(null != user && !user.getUid().equals(uid)){
							noticeService.save(Types.at.toString(), uid, user.getUid(), tid);
						}
					}
				}
			}
			return tid;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public boolean delete(Long tid) {
		if(null != tid){
			AR.update("delete from t_topic where tid = ?", tid).executeUpdate();
			return true;
		}
		return false;
	}

	@Override
	public Map<String, Object> getTopicMap(Topic topic, boolean isDetail) {
		if(null == topic){
			return null;
		}
		Long tid = topic.getTid();
		Long uid = topic.getUid();
		Long nid = topic.getNid();
		Long comments = topic.getComments();
		
		User user = userService.getUser(uid);
		Node node = nodeService.getNode(nid);
		
		if(null == user || null == node){
			return null;
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tid", tid);
		map.put("views", topic.getViews());
		map.put("loves", topic.getLoves());
		map.put("favorites", topic.getFavorites());
		map.put("comments", comments);
		map.put("title", topic.getTitle());
		map.put("is_essence", topic.getIs_essence());
		map.put("create_time", topic.getCreate_time());
		map.put("update_time", topic.getUpdate_time());
		map.put("user_name", user.getLogin_name());
		
		String avatar = Utils.getAvatar(user.getAvatar(), ImageTypes.small);
		
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
		
		if(isDetail){
			String content = Utils.markdown2html(topic.getContent());
			map.put("content", content);
		}
		return map;
	}

	@Override
	public boolean updateCount(Long tid, String type, long count, boolean updateTime) {
		if(null != tid && StringKit.isNotBlank(type)){
			try {
				StringBuffer upSql = new StringBuffer("update t_topic set %s = (%s + ?) ");
				if(updateTime){
					upSql.append(", update_time = " + DateKit.getCurrentUnixTime());
				}
				upSql.append(" where tid = ?");
				AR.update(String.format(upSql.toString(), type, type), count, tid).executeUpdate();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * 评论帖子
	 * @param uid		评论人uid
	 * @param to_uid	发帖人uid
	 * @param tid		帖子id
	 * @param content	评论内容
	 * @return
	 */
	@Override
	public boolean comment(Long uid, Long to_uid, Long tid, String content) {
		boolean flag = commentService.save(uid, to_uid, tid, content);
		if(flag){
			this.updateCount(tid, Types.comments.toString(), +1, true);
			// 通知
			if(!uid.equals(to_uid)){
				noticeService.save(Types.comment.toString(), uid, to_uid, tid);
				
				// 通知@的用户
				Set<String> atUsers = Utils.getAtUsers(content);
				if(CollectionKit.isNotEmpty(atUsers)){
					for(String user_name : atUsers){
						User user = userService.getUser(user_name);
						if(null != user && !user.getUid().equals(uid)){
							noticeService.save(Types.at.toString(), uid, user.getUid(), tid);
						}
					}
				}
				
				// 更新总评论数
				settingsService.updateCount(Types.comment_count.toString(), +1);
			}
			return true;
		}
		return false;
	}

	@Override
	public Long getTopics(Long uid) {
		if(null != uid){
			return AR.find("select count(1) from t_topic where uid = ? and status = 1", uid).first(Long.class);
		}
		return 0L;
	}

	@Override
	public Long update(Long tid, Long nid, String title, String content) {
		
		return null;
	}
	
}
