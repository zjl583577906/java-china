package com.javachina.controller;

import java.io.File;
import java.util.Map;

import com.blade.Blade;
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
import com.javachina.model.LoginUser;
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
		
		LoginUser user = SessionKit.getLoginUser();
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
		
		LoginUser loginUser = SessionKit.getLoginUser();
		if(null == loginUser){
			response.text(this.SIGNIN);
			return null;
		}
		
		String type = request.query("type");
		if(StringKit.isBlank(type)){
			return null;
		}
		
		String avatar = request.query("avatar");
		
		// 修改头像
		if(type.equals("avatar") && StringKit.isNotBlank(avatar)){
			String avatar_path = Blade.me().webRoot() + File.separator + avatar;
			userService.updateAvatar(loginUser.getUid(), avatar_path);
			
			LoginUser loginUserTemp = userService.getLoginUser(null, loginUser.getUid());
			SessionKit.setLoginUser(request.session(), loginUserTemp);
			response.text(this.SUCCESS);
			return null;
		}
		
		// 修改基本信息
		if(type.equals("info")){
			String nickName = request.query("nick_name");
			String jobs = request.query("jobs");
			String webSite = request.query("web_site");
			String github = request.query("github");
			String signature = request.query("signature");
			String instructions = request.query("instructions");
			userinfoService.update(loginUser.getUid(), nickName, jobs, webSite, github, signature, instructions);
			userService.updateAvatar(loginUser.getUid(), avatar);
			
			LoginUser loginUserTemp = userService.getLoginUser(null, loginUser.getUid());
			SessionKit.setLoginUser(request.session(), loginUserTemp);
			response.text(this.SUCCESS);
			return null;
		}
				
		// 修改密码
		if(type.equals("pwd")){
			
			Map<String, Object> profile = userService.getUserDetail(loginUser.getUid());
			request.attribute("profile", profile);
			
			String curpwd = request.query("curpwd");
			String newpwd = request.query("newpwd");
			
			if(StringKit.isBlank(curpwd) || StringKit.isBlank(newpwd)){
				request.attribute(this.ERROR, "参数不能为空!");
				return this.getView("settings");
			}
			
			if(!EncrypKit.md5(loginUser.getUser_name() + curpwd).equals(loginUser.getPass_word())){
				request.attribute(this.ERROR, "旧密码输入错误");
				return this.getView("settings");
			}
			
			String new_pwd = EncrypKit.md5(loginUser.getUser_name() + newpwd);
			userService.updatePwd(loginUser.getUid(), new_pwd);
			
			LoginUser loginUserTemp = userService.getLoginUser(null, loginUser.getUid());
			SessionKit.setLoginUser(request.session(), loginUserTemp);
			
			request.attribute(this.INFO, "密码修改成功");
			return this.getView("settings");
		}
		return null;
		
	}
	
}
