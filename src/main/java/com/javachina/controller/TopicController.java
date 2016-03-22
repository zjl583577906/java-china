package com.javachina.controller;


import java.util.List;
import java.util.Map;

import com.blade.ioc.annotation.Inject;
import com.blade.route.annotation.Path;
import com.blade.route.annotation.Route;
import com.blade.view.ModelAndView;
import com.blade.web.http.HttpMethod;
import com.blade.web.http.Request;
import com.blade.web.http.Response;
import com.javachina.service.ActivecodeService;
import com.javachina.service.NodeService;
import com.javachina.service.TopicService;
import com.javachina.service.UserService;

@Path("/")
public class TopicController extends BaseController {

	@Inject
	private ActivecodeService activecodeService;
	
	@Inject
	private UserService userService;
	
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
		
		return this.getView("topic_add");
	}
	
}
