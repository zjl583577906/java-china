package com.javachina.controller;

import com.blade.route.annotation.Path;
import com.blade.route.annotation.Route;
import com.blade.view.template.ModelAndView;
import com.blade.web.http.HttpMethod;
import com.blade.web.http.Request;
import com.blade.web.http.Response;

@Path("/")
public class IndexController extends BaseController {

	/**
	 * 首页
	 */
	@Route(value = "/")
	public ModelAndView show_home(Request request, Response response){
		return this.getView("home");
	}
	
	/**
	 * 登录页面
	 */
	@Route(value = "/signin", method = HttpMethod.GET)
	public ModelAndView show_signin(Request request, Response response){
		return this.getView("signin");
	}
	
	/**
	 * 注册页面
	 */
	@Route(value = "/signup", method = HttpMethod.GET)
	public ModelAndView show_signup(Request request, Response response){
		return this.getView("signup");
	}
	
	/**
	 * 忘记密码页面
	 */
	@Route(value = "/forgot", method = HttpMethod.GET)
	public ModelAndView show_forgot(Request request, Response response){
		return this.getView("forgot");
	}
	
}
