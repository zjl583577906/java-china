package com.javachina.controller;


import java.util.List;
import java.util.Map;

import com.blade.ioc.annotation.Inject;
import com.blade.route.annotation.Path;
import com.blade.route.annotation.PathVariable;
import com.blade.route.annotation.Route;
import com.blade.view.ModelAndView;
import com.blade.web.http.HttpMethod;
import com.blade.web.http.Request;
import com.blade.web.http.Response;
import com.javachina.kit.SessionKit;
import com.javachina.model.Topic;
import com.javachina.model.User;
import com.javachina.service.NodeService;
import com.javachina.service.TopicService;

import blade.kit.StringKit;

@Path("/")
public class TopicController extends BaseController {

	@Inject
	private TopicService topicService;
	
	@Inject
	private NodeService nodeService;
	
	/**
	 * 发布帖子页面
	 */
	@Route(value = "/topic/add", method = HttpMethod.GET)
	public ModelAndView show_add_topic(Request request, Response response){
		List<Map<String, Object>> nodes = nodeService.getNodeList();
		request.attribute("nodes", nodes);
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
		
		User user = SessionKit.getLoginUser();
		
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
		
		if(content.length() > 10){
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
			request.attribute(this.ERROR, "帖子发布失败。。。");
		}
		return this.getView("topic_add");
	}
	
	/**
	 * 帖子详情页面
	 */
	@Route(value = "/topic/:tid", method = HttpMethod.GET)
	public ModelAndView show_add_topic(@PathVariable("tid") Long tid, Request request, Response response){
		Topic topic = topicService.getTopic(tid);
		if(null == topic){
			response.go("/");
			return null;
		}
		Map<String, Object> topicMap = topicService.getTopicMap(topic, true);
		request.attribute("topic", topicMap);
		return this.getView("topic_detail");
	}
	
}
