package com.javachina.controller;

import java.util.Map;

import com.blade.ioc.annotation.Inject;
import com.blade.jdbc.Page;
import com.blade.jdbc.QueryParam;
import com.blade.route.annotation.Path;
import com.blade.route.annotation.Route;
import com.blade.view.ModelAndView;
import com.blade.web.http.HttpMethod;
import com.blade.web.http.Request;
import com.blade.web.http.Response;
import com.javachina.service.ActivecodeService;
import com.javachina.service.FavoriteService;
import com.javachina.service.NodeService;
import com.javachina.service.NoticeService;
import com.javachina.service.SettingsService;
import com.javachina.service.TopicService;
import com.javachina.service.UserService;

@Path("/")
public class ChosenController extends BaseController {

	@Inject
	private ActivecodeService activecodeService;
	
	@Inject
	private UserService userService;
	
	@Inject
	private TopicService topicService;
	
	@Inject
	private NodeService nodeService;
	
	@Inject
	private FavoriteService favoriteService;
	
	@Inject
	private SettingsService settingsService;
	
	@Inject
	private NoticeService noticeService;
	
	/**
	 * 精选页面
	 */
	@Route(value = "/chosen", method = HttpMethod.GET)
	public ModelAndView chosen(Request request, Response response){
		
		// 帖子
		QueryParam tp = QueryParam.me();
		Integer page = request.queryAsInt("p");
		
		if(null == page || page < 1){
			page = 1;
		}
		
		tp.eq("status", 1).eq("is_chosen", 1).orderby("update_time desc").page(page, 15);
		Page<Map<String, Object>> topicPage = topicService.getPageList(tp);
		request.attribute("topicPage", topicPage);
		
		return this.getView("chosen");
	}
	
	/**
	 * 精选详情页面
	 */
	@Route(value = "/chosen/:tid", method = HttpMethod.GET)
	public ModelAndView chosenDetail(Request request, Response response){
		return this.getView("chosen_detail");
	}
	
}
