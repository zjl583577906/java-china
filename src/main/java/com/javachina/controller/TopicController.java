package com.javachina.controller;


import java.util.List;
import java.util.Map;

import com.blade.ioc.annotation.Inject;
import com.blade.jdbc.Page;
import com.blade.jdbc.QueryParam;
import com.blade.route.annotation.Path;
import com.blade.route.annotation.PathVariable;
import com.blade.route.annotation.Route;
import com.blade.view.ModelAndView;
import com.blade.web.http.HttpMethod;
import com.blade.web.http.Request;
import com.blade.web.http.Response;
import com.javachina.Types;
import com.javachina.kit.SessionKit;
import com.javachina.model.LoginUser;
import com.javachina.model.Topic;
import com.javachina.service.CommentService;
import com.javachina.service.FavoriteService;
import com.javachina.service.LoveService;
import com.javachina.service.NodeService;
import com.javachina.service.TopicService;

import blade.kit.StringKit;

@Path("/")
public class TopicController extends BaseController {

	@Inject
	private TopicService topicService;
	
	@Inject
	private NodeService nodeService;
	
	@Inject
	private CommentService commentService;
	
	@Inject
	private FavoriteService favoriteService;
	
	@Inject
	private LoveService loveService;
	
	/**
	 * 发布帖子页面
	 */
	@Route(value = "/topic/add", method = HttpMethod.GET)
	public ModelAndView show_add_topic(Request request, Response response){
		putData(request);
		return this.getView("topic_add");
	}
	
	/**
	 * 发布帖子操作
	 */
	@Route(value = "/topic/add", method = HttpMethod.POST)
	public ModelAndView add_topic(Request request, Response response){
		String title = request.query("title");
		String content = request.query("content");
		Long nid = request.queryAsLong("nid");
		
		LoginUser user = SessionKit.getLoginUser();
		
		putData(request);
		
		if(StringKit.isBlank(title) || StringKit.isBlank(content) || null == nid){
			request.attribute(this.ERROR, "骚年，有些东西木有填哎！！");
			request.attribute("title", title);
			request.attribute("content", content);
			return this.getView("topic_add");
		}
		
		if(title.length() > 40){
			request.attribute(this.ERROR, "标题太长了，试试少写点儿～");
			request.attribute("title", title);
			request.attribute("content", content);
			return this.getView("topic_add");
		}
		
		if(content.length() > 5000){
			request.attribute(this.ERROR, "内容太长了，试试少吐点口水。。。");
			request.attribute("title", title);
			request.attribute("content", content);
			return this.getView("topic_add");
		}
		
		// 发布帖子
		Long tid = topicService.save(user.getUid(), nid, title, content, 0);
		if(null != tid){
			response.go("/topic/" + tid);
			return null;
		} else {
			request.attribute("title", title);
			request.attribute("content", content);
			request.attribute(this.ERROR, "帖子发布失败。。。");
		}
		return this.getView("topic_add");
	}
	
	private void putData(Request request){
		List<Map<String, Object>> nodes = nodeService.getNodeList();
		request.attribute("nodes", nodes);
	}
	
	/**
	 * 帖子详情页面
	 */
	@Route(value = "/topic/:tid", method = HttpMethod.GET)
	public ModelAndView show_add_topic(@PathVariable("tid") Long tid, Request request, Response response){
		
		LoginUser user = SessionKit.getLoginUser();
		
		Long uid = null;
		if(null != user){
			uid = user.getUid();
		}
		
		putDetail(request, response, uid, tid);
		
		// 刷新浏览数
		topicService.updateCount(tid, Types.views.toString(), +1);
		return this.getView("topic_detail");
	}
	
	private void putDetail(Request request, Response response, Long uid, Long tid){
		
		Topic topic = topicService.getTopic(tid);
		if(null == topic){
			response.go("/");
			return;
		}
		
		Integer page = request.queryAsInt("p");
		if(null == page || page < 1){
			page = 1;
		}
		
		// 帖子详情
		Map<String, Object> topicMap = topicService.getTopicMap(topic, true);
		request.attribute("topic", topicMap);
		
		// 是否收藏
		boolean is_favorite = favoriteService.isFavorite(Types.topic.toString(), uid, tid);
		request.attribute("is_favorite", is_favorite);
		
		// 是否点赞
		boolean is_love = loveService.isLove(uid, tid);
		request.attribute("is_love", is_love);
		
		QueryParam cp = QueryParam.me();
		cp.eq("tid", tid).orderby("cid desc").page(page, 10);
		Page<Map<String, Object>> commentPage = commentService.getPageListMap(cp);
		request.attribute("commentPage", commentPage);
	}
	
	/**
	 * 评论帖子操作
	 */
	@Route(value = "/comment/add", method = HttpMethod.POST)
	public ModelAndView add_comment(Request request, Response response){
		
		LoginUser user = SessionKit.getLoginUser();
		if(null == user){
			response.go("/");
			return null;
		}
		
		Long uid = user.getUid();
		
		String content = request.query("content");
		Long tid = request.queryAsLong("tid");
		Topic topic = topicService.getTopic(tid);
		if(null == topic){
			response.go("/");
			return null;
		}
		
		if(null == tid || StringKit.isBlank(content)){
			request.attribute(this.ERROR, "骚年，有些东西木有填哎！！");
			this.putDetail(request, response, uid, tid);
			request.attribute("content", content);
			return this.getView("topic_detail");
		}
		
		if(content.length() > 5000){
			request.attribute(this.ERROR, "内容太长了，试试少吐点口水。。。");
			this.putDetail(request, response, uid, tid);
			request.attribute("content", content);
			return this.getView("topic_detail");
		}
		
		// 评论帖子
		boolean flag = topicService.comment(uid, topic.getUid(), tid, content);
		if(flag){
			response.go("/topic/" + tid);
			return null;
		} else {
			request.attribute(this.ERROR, "帖子评论失败。。。");
		}
		
		return this.getView("topic_detail");
	}
	
}
