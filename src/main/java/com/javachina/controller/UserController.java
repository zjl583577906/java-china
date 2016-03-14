package com.javachina.controller;

import java.util.Map;

import com.blade.ioc.annotation.Inject;
import com.blade.jdbc.QueryParam;
import com.blade.route.annotation.Path;
import com.blade.route.annotation.PathVariable;
import com.blade.route.annotation.Route;
import com.blade.view.ModelAndView;
import com.blade.web.http.Request;
import com.blade.web.http.Response;
import com.javachina.model.User;
import com.javachina.service.UserService;

@Path("/")
public class UserController extends BaseController {
	
	@Inject
	private UserService userService;
	
	/**
	 * 用户主页
	 */
	@Route(value = "/member/:username")
	public ModelAndView member(@PathVariable("username") String username,
			Request request, Response response){
		
		QueryParam up = QueryParam.me();
		up.eq("status", 1).eq("login_name", username);
		
		User user = userService.getUser(up);
		if(null == user){
			// 不存在的用户
			response.text("not found user.");
			return null;
		}
		
		Map<String, Object> profile = userService.getUserDetail(user.getUid());
		request.attribute("profile", profile);
		
		return this.getView("member_detail");
	}
	
}
