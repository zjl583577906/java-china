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
import com.javachina.service.UserinfoService;

import blade.kit.EncrypKit;
import blade.kit.StringKit;

@Path("/")
public class UserController extends BaseController {
	
	@Inject
	private UserService userService;
	
	@Inject
	private UserinfoService userinfoService;
	
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
	public void settings(Request request, Response response){
		
		User user = SessionKit.getLoginUser();
		if(null == user){
			response.text(this.SIGNIN);
			return;
		}
		
		String type = request.query("type");
		if(StringKit.isBlank(type)){
			return;
		}
		
		String avatar = request.query("avatar");
		
		// 修改头像
		if(type.equals("avatar") && StringKit.isNotBlank(avatar)){
			userService.updateAvatar(user.getUid(), avatar);
			response.text(this.SUCCESS);
			return;
		}
		
		// 修改基本信息
		if(type.equals("info")){
			String nickName = request.query("nick_name");
			String jobs = request.query("jobs");
			String webSite = request.query("web_site");
			String github = request.query("github");
			String signature = request.query("signature");
			String instructions = request.query("instructions");
			userinfoService.update(user.getUid(), nickName, jobs, webSite, github, signature, instructions);
			userService.updateAvatar(user.getUid(), avatar);
			response.text(this.SUCCESS);
		}
				
		// 修改密码
		if(type.equals("pwd")){
			
			String curpwd = request.query("curpwd");
			String newpwd = request.query("newpwd");
			
			if(StringKit.isBlank(curpwd)){
				response.text(this.NOTNULL);
				return;
			}
			if(StringKit.isBlank(newpwd)){
				response.text(this.NOTNULL);
				return;
			}
			
			if(!EncrypKit.md5(user.getLogin_name() + curpwd).equals(user.getPass_word())){
				response.text("pwd_error");
				return;
			}
			
			String new_pwd = EncrypKit.md5(user.getLogin_name() + newpwd);
			userService.updatePwd(user.getUid(), new_pwd);
			response.text(this.SUCCESS);
		}
		
	}
	
}
