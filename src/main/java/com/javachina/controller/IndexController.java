package com.javachina.controller;

import java.util.List;
import java.util.Map;

import com.blade.ioc.annotation.Inject;
import com.blade.jdbc.Page;
import com.blade.jdbc.QueryParam;
import com.blade.patchca.PatchcaService;
import com.blade.route.annotation.Path;
import com.blade.route.annotation.Route;
import com.blade.view.ModelAndView;
import com.blade.web.http.HttpMethod;
import com.blade.web.http.Request;
import com.blade.web.http.Response;
import com.javachina.kit.SessionKit;
import com.javachina.kit.TimeKit;
import com.javachina.model.Node;
import com.javachina.model.User;
import com.javachina.service.ActivecodeService;
import com.javachina.service.NodeService;
import com.javachina.service.TopicService;
import com.javachina.service.UserService;

import blade.kit.PatternKit;
import blade.kit.StringKit;

@Path("/")
public class IndexController extends BaseController {

	@Inject
	private ActivecodeService activecodeService;
	
	@Inject
	private UserService userService;
	
	@Inject
	private TopicService topicService;
	
	@Inject
	private NodeService nodeService;
	
	/**
	 * 首页
	 */
	@Route(value = "/")
	public ModelAndView show_home(Request request, Response response){
		
		// 帖子
		QueryParam tp = QueryParam.me();
		tp.eq("status", 1).orderby("update_time, comments, views desc").page(1, 15);
		Page<Map<String, Object>> topicPage = topicService.getPageList(tp);
		request.attribute("topicPage", topicPage);
		
		// 最热帖子
		QueryParam hp = QueryParam.me();
		Integer start_time = TimeKit.getTodayTime();
		Integer end_time = TimeKit.getTomorrowTime();
		hp.eq("status", 1).between("update_time", start_time, end_time).orderby("comments, views desc").add("limit 10");
		List<Map<String, Object>> hot_topics = topicService.getTopicList(hp);
		request.attribute("hot_topics", hot_topics);
		
		// 最热门的10个节点
		QueryParam np = QueryParam.me();
		np.eq("is_del", 0).orderby("topics desc").add("limit 10");
		List<Node> hot_nodes = nodeService.getNodeList(np);
		request.attribute("hot_nodes", hot_nodes);
		
		return this.getView("home");
	}
	
	/**
	 * 获取验证码
	 */
	@Route(value = "/captcha")
	public void show_captcha(Request request, Response response){
		PatchcaService.get().size(200, 40).render(request, response);
	}
	
	/**
	 * 登录页面
	 */
	@Route(value = "/signin", method = HttpMethod.GET)
	public ModelAndView show_signin(Request request, Response response){
		return this.getView("signin");
	}
	
	/**
	 * 登录操作
	 */
	@Route(value = "/signin", method = HttpMethod.POST)
	public ModelAndView signin(Request request, Response response){
		String login_name = request.query("login_name");
		String pass_word = request.query("pass_word");
		if(StringKit.isBlank(login_name) || StringKit.isBlank(pass_word)){
			request.attribute(this.ERROR, "用户名和密码不能为空");
			return this.getView("signin");
		}
		User user = userService.signin(login_name, pass_word);
		if(null == user){
			request.attribute(this.ERROR, "用户名或密码错误");
			return this.getView("signin");
		}
		SessionKit.setUser(request.session(), user);
		response.go("/");
		return null;
	}
	
	/**
	 * 注册页面
	 */
	@Route(value = "/signup", method = HttpMethod.GET)
	public ModelAndView show_signup(Request request, Response response){
		return this.getView("signup");
	}
	
	/**
	 * 注销
	 */
	@Route(value = "/logout")
	public void logout(Request request, Response response){
		SessionKit.removeUser(request.session());
		response.go("/");
	}
	
	/**
	 * 注册操作
	 */
	@Route(value = "/signup", method = HttpMethod.POST)
	public ModelAndView signup(Request request, Response response){
		
		String login_name = request.query("login_name");
		String email = request.query("email");
		String pass_word = request.query("pass_word");
		
		if(StringKit.isBlank(login_name) || StringKit.isBlank(pass_word) || StringKit.isBlank(email)){
			request.attribute(this.ERROR, "参数不能为空");
			return this.getView("signup");
		}
		
		if(!PatternKit.isEmail(email)){
			request.attribute(this.ERROR, "请输入正确的邮箱");
			return this.getView("signup");
		}
		
		QueryParam queryParam = QueryParam.me();
		queryParam.eq("login_name", login_name);
		queryParam.in("status", 0, 1);
		User user = userService.getUser(queryParam);
		if(null != user){
			request.attribute(this.ERROR, "该用户名已经被占用，请更换用户名");
			return this.getView("signup");
		}
		
		queryParam = QueryParam.me();
		queryParam.eq("email", email);
		queryParam.in("status", 0, 1);
		user = userService.getUser(queryParam);
		if(null != user){
			request.attribute(this.ERROR, "该邮箱已经被注册，请直接登录");
			return this.getView("signup");
		}
		
		boolean flag = userService.signup(login_name, pass_word, email);
		if(flag){
			request.attribute(this.INFO, "注册成功，已经向您的邮箱 " + email + " 发送了一封激活申请，请注意查收！");
		} else {
			request.attribute(this.ERROR, "注册发生异常");
		}
		return this.getView("signup");
	}
	
	/**
	 * 忘记密码页面
	 */
	@Route(value = "/forgot", method = HttpMethod.GET)
	public ModelAndView show_forgot(Request request, Response response){
		return this.getView("forgot");
	}
	
	/**
	 * 忘记密码发送链接
	 */
	@Route(value = "/forgot", method = HttpMethod.POST)
	public ModelAndView forgot(Request request, Response response){
		String email = request.query("email");
		if(StringKit.isBlank(email)){
			request.attribute(this.ERROR, "参数不能为空");
			return this.getView("forgot");
		}
		
		if(!PatternKit.isEmail(email)){
			request.attribute(this.ERROR, "请输入正确的邮箱");
			return this.getView("forgot");
		}
		
		User user = userService.getUser(QueryParam.me().eq("email", email).eq("status", 1));
		if(null == user){
			request.attribute(this.ERROR, "该邮箱没有注册");
			return this.getView("forgot");
		}
		userService.resetPwd(email);
		request.attribute(this.STATUS, 200);
		return this.getView("forgot");
	}
	
}
