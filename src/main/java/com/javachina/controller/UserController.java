package com.javachina.controller;

import java.util.Map;

import com.blade.ioc.annotation.Inject;
import com.blade.jdbc.QueryParam;
import com.blade.route.annotation.Path;
import com.blade.route.annotation.PathVariable;
import com.blade.route.annotation.Route;
import com.blade.view.ModelAndView;
import com.blade.web.http.HttpMethod;
import com.blade.web.http.Request;
import com.blade.web.http.Response;
import com.javachina.kit.SessionKit;
import com.javachina.model.User;
import com.javachina.service.UserService;

import blade.kit.EncrypKit;
import blade.kit.StringKit;

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
	
	/**
	 * 个人设置
	 */
	@Route(value = "settings", method = HttpMethod.GET)
	public ModelAndView show_settings(Request request, Response response){
		
		User user = SessionKit.getLoginUser();
		if(null == user){
			response.go("/");
			return null;
		}
		Map<String, Object> profile = userService.getUserDetail(user.getUid());
		request.attribute("profile", profile);
		return this.getView("settings");
	}
	
	/**
	 * 个人设置
	 */
	@Route(value = "settings", method = HttpMethod.POST)
	public ModelAndView settings(Request request, Response response){
		
		User user = SessionKit.getLoginUser();
		if(null == user){
			response.go("/");
			return null;
		}
		
		Map<String, Object> profile = userService.getUserDetail(user.getUid());
		request.attribute("profile", profile);
		
		String type = request.query("type");
		if(StringKit.isBlank(type)){
			return this.getView("settings");
		}
		String avatar = request.query("avatar");
		
		// 修改头像
		if(type.equals("avatar") && StringKit.isNotBlank(avatar)){
			userService.updateAvatar(user.getUid(), avatar);
			profile = userService.getUserDetail(user.getUid());
			request.attribute("profile", profile);
			request.attribute(this.INFO, "头像修改成功");
			return this.getView("settings");
		}
		
		// 修改基本信息
		if(type.equals("info")){
			userService.updateAvatar(user.getUid(), avatar);
			profile = userService.getUserDetail(user.getUid());
			request.attribute("profile", profile);
			request.attribute(this.INFO, "头像修改成功");
			return this.getView("settings");
		}
				
		// 修改密码
		if(type.equals("pwd")){
			
			String curpwd = request.query("curpwd");
			String newpwd = request.query("newpwd");
			
			if(StringKit.isBlank(curpwd)){
				request.attribute(this.ERROR, "请输入当前密码");
				return this.getView("settings");
			}
			if(StringKit.isBlank(newpwd)){
				request.attribute(this.ERROR, "请输入新密码");
				return this.getView("settings");
			}
			
			if(!EncrypKit.md5(user.getLogin_name() + curpwd).equals(user.getPass_word())){
				request.attribute(this.ERROR, "当前密码输入错误");
				return this.getView("settings");
			}
			
			String new_pwd = EncrypKit.md5(user.getLogin_name() + newpwd);
			userService.updatePwd(user.getUid(), new_pwd);
			request.attribute(this.INFO, "密码修改成功");
			return this.getView("settings");
		}
		
		return this.getView("settings");
	}
	
}
