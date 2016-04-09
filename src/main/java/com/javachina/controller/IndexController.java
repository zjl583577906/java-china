package com.javachina.controller;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.blade.Blade;
import com.blade.ioc.annotation.Inject;
import com.blade.jdbc.AR;
import com.blade.jdbc.Page;
import com.blade.jdbc.QueryParam;
import com.blade.patchca.PatchcaService;
import com.blade.route.annotation.Path;
import com.blade.route.annotation.PathVariable;
import com.blade.route.annotation.Route;
import com.blade.view.ModelAndView;
import com.blade.web.http.HttpMethod;
import com.blade.web.http.Request;
import com.blade.web.http.Response;
import com.blade.web.multipart.FileItem;
import com.javachina.Constant;
import com.javachina.Types;
import com.javachina.kit.ImageKit;
import com.javachina.kit.SessionKit;
import com.javachina.model.Activecode;
import com.javachina.model.LoginUser;
import com.javachina.model.Node;
import com.javachina.model.User;
import com.javachina.service.ActivecodeService;
import com.javachina.service.FavoriteService;
import com.javachina.service.NodeService;
import com.javachina.service.NoticeService;
import com.javachina.service.SettingsService;
import com.javachina.service.TopicService;
import com.javachina.service.UserService;

import blade.kit.DateKit;
import blade.kit.FileKit;
import blade.kit.PatternKit;
import blade.kit.StringKit;
import blade.kit.json.JSONObject;

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
	
	@Inject
	private FavoriteService favoriteService;
	
	@Inject
	private SettingsService settingsService;
	
	@Inject
	private NoticeService noticeService;
	
	
	/**
	 * 首页
	 */
	@Route(value = "/", method = HttpMethod.GET)
	public ModelAndView show_home(Request request, Response response){
		
		this.putData(request, null);
		
		// 最热帖子
		QueryParam hp = QueryParam.me();
		hp.eq("status", 1)/*.between("update_time", start_time, end_time)*/.orderby("tid, comments, views desc").add("limit 10");
		List<Map<String, Object>> hot_topics = topicService.getTopicList(hp);
		request.attribute("hot_topics", hot_topics);
		
		// 最热门的10个节点
		QueryParam np = QueryParam.me();
		np.eq("is_del", 0).notEq("pid", 0).orderby("topics desc").add("limit 10");
		List<Node> hot_nodes = nodeService.getNodeList(np);
		request.attribute("hot_nodes", hot_nodes);
		
		return this.getView("home");
	}
	
	private void putData(Request request, Long nid){
		
		// 帖子
		QueryParam tp = QueryParam.me();
		
		String tab = request.query("tab");
		Integer page = request.queryAsInt("p");
		
		if(StringKit.isNotBlank(tab)){
			QueryParam np = QueryParam.me();
			np.eq("is_del", 0).eq("slug", tab);
			Node node = nodeService.getNode(np);
			if(null != node){
				tp.eq("nid", node.getNid());
				request.attribute("tab", tab);
				request.attribute("node_name", node.getTitle());
			}
		} else {
			if(null != nid){
				tp.eq("nid", nid);
			}
		}
		
		if(null == page || page < 1){
			page = 1;
		}
		
		tp.eq("status", 1).orderby("update_time desc").page(page, 15);
		Page<Map<String, Object>> topicPage = topicService.getPageList(tp);
		request.attribute("topicPage", topicPage);
		
		// 读取节点列表
		List<Map<String, Object>> nodes = nodeService.getNodeList();
		request.attribute("nodes", nodes);
	}
	
	/**
	 * 节点主题页
	 */
	@Route(value = "/go/:slug", method = HttpMethod.GET)
	public ModelAndView go(@PathVariable("slug") String slug,
			Request request, Response response){
		
		QueryParam np = QueryParam.me();
		np.eq("is_del", 0).eq("slug", slug);
		
		Node node = nodeService.getNode(np);
		if(null == node){
			// 不存在的节点
			response.text("not found node.");
			return null;
		}
		
		this.putData(request, node.getNid());
		
		Map<String, Object> nodeMap = nodeService.getNodeDetail(null, node.getNid());
		request.attribute("node", nodeMap);
		
		return this.getView("node_detail");
	}
	
	/**
	 * 获取验证码
	 */
	@Route(value = "/captcha", method = HttpMethod.GET)
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
		String rememberme = request.query("rememberme");
		
		if(StringKit.isBlank(login_name) || StringKit.isBlank(pass_word)){
			request.attribute(this.ERROR, "用户名和密码不能为空");
			request.attribute("login_name", login_name);
			return this.getView("signin");
		}
		
		User user = userService.signin(login_name, pass_word);
		if(null == user){
			request.attribute(this.ERROR, "用户名或密码错误");
			request.attribute("login_name", login_name);
			return this.getView("signin");
		}
		
		LoginUser loginUser = userService.getLoginUser(user, null);
		SessionKit.setLoginUser(request.session(), loginUser);
		if(StringKit.isNotBlank(rememberme) && rememberme.equals("on")){
			SessionKit.setCookie(response, Constant.USER_IN_COOKIE, loginUser.getUid());
		}
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
		SessionKit.removeCookie(response);
		response.go("/");
	}
	
	/**
	 * 通知列表
	 */
	@Route(value = "/notices", method = HttpMethod.GET)
	public ModelAndView notices(Request request, Response response){
		LoginUser user = SessionKit.getLoginUser();
		if(null == user){
			response.go("/signin");
			return null;
		}
		
		List<Map<String, Object>> notices = noticeService.getNotices(user.getUid());
		request.attribute("notices", notices);
		
		// 清空我的通知
		userService.updateCount(user.getUid(), Types.notices.toString(), -999);
		noticeService.read(user.getUid());
		
		return this.getView("notices");
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
			request.attribute("login_name", login_name);
			request.attribute("email", email);
			return this.getView("signup");
		}
		
		if(!PatternKit.isEmail(email)){
			request.attribute(this.ERROR, "请输入正确的邮箱");
			request.attribute("login_name", login_name);
			request.attribute("email", email);
			return this.getView("signup");
		}
		
		QueryParam queryParam = QueryParam.me();
		queryParam.eq("login_name", login_name);
		queryParam.in("status", AR.in(0, 1));
		User user = userService.getUser(queryParam);
		if(null != user){
			request.attribute(this.ERROR, "该用户名已经被占用，请更换用户名");
			request.attribute("login_name", login_name);
			return this.getView("signup");
		}
		
		queryParam = QueryParam.me();
		queryParam.eq("email", email);
		queryParam.in("status", 0, 1);
		user = userService.getUser(queryParam);
		if(null != user){
			request.attribute(this.ERROR, "该邮箱已经被注册，请直接登录");
			request.attribute("login_name", login_name);
			request.attribute("email", email);
			return this.getView("signup");
		}
		
		User user_ = userService.signup(login_name, pass_word, email);
		if(null != user_){
			request.attribute(this.INFO, "注册成功，已经向您的邮箱 " + email + " 发送了一封激活申请，请注意查收！");
		} else {
			request.attribute(this.ERROR, "注册发生异常");
		}
		return this.getView("signup");
	}
	
	/**
	 * 激活账户
	 */
	@Route(value = "/active/:code", method = HttpMethod.GET)
	public ModelAndView activeAccount(@PathVariable("code") String code, Request request, Response response){
		Activecode activecode = activecodeService.getActivecode(code, "signup");
		if(null == activecode){
			request.attribute(this.ERROR, "无效的激活码");
			return this.getView("active");
		}
		Long expries = activecode.getExpires_time();
		if(expries < DateKit.getCurrentUnixTime()){
			request.attribute(this.ERROR, "该激活码已经过期，请重新发送");
			return this.getView("active");
		}
		if(activecode.getIs_use() == 1){
			request.attribute(this.ERROR, "您已经激活成功，不可重复激活");
			return this.getView("active");
		}
		boolean flag = userService.active(activecode.getId(), activecode.getUid());
		if(!flag){
			request.attribute(this.ERROR, "激活失败");
		} else {
			request.attribute(this.INFO, "激活成功，您可以凭密码登陆");
			settingsService.updateCount(Types.user_count.toString(), +1);
		}
		return this.getView("active");
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
			request.attribute(this.ERROR, "邮箱不能为空");
			return this.getView("forgot");
		}
		
		if(!PatternKit.isEmail(email)){
			request.attribute(this.ERROR, "请输入正确的邮箱");
			request.attribute("email", email);
			return this.getView("forgot");
		}
		
		User user = userService.getUser(QueryParam.me().eq("email", email));
		if(null == user){
			request.attribute(this.ERROR, "该邮箱没有注册账户,请检查您的邮箱是否正确");
			request.attribute("email", email);
			return this.getView("forgot");
		}
		if(user.getStatus() == 0){
			request.attribute(this.ERROR, "该邮箱未激活");
			request.attribute("email", email);
			return this.getView("forgot");
		}
		String code = activecodeService.save(user, "forgot");
		if(StringKit.isNotBlank(code)){
			request.attribute(this.INFO, "修改密码链接已经发送到您的邮箱，请注意查收！");
		} else {
			request.attribute(this.ERROR, "找回密码失败");
		}
		
		return this.getView("forgot");
	}
	
	/**
	 * 忘记密码页面
	 */
	@Route(value = "/uploadimg", method = HttpMethod.POST)
	public void uploadimg(Request request, Response response){
		LoginUser user = SessionKit.getLoginUser();
		if(null == user){
			return;
		}
		FileItem[] fileItems = request.files();
		if(null != fileItems && fileItems.length > 0){
			
			FileItem fileItem = fileItems[0];
			
			String type = request.query("type");
			String suffix = FileKit.getExtension(fileItem.getFileName());
			if(StringKit.isNotBlank(suffix)){
				suffix = "." + suffix;
			}
			if(!PatternKit.isImage(suffix)){
				return;
			}
			
			if(null == type){
				type = "temp";
			}
			
			String saveName = DateKit.dateFormat(new Date(), "yyyyMMddHHmmssSSS")  + "_" + StringKit.getRandomChar(10) + suffix;
			File file = new File(Blade.me().webRoot() + File.separator + Constant.UPLOAD_FOLDER + File.separator + saveName);
			
			try {
				
				ImageKit.copyFileUsingFileChannels(fileItem.getFile(), file);
				
				String filePath = Constant.UPLOAD_FOLDER + "/" + saveName;
				
				JSONObject res = new JSONObject();
				res.put("status", 200);
				res.put("savekey", filePath);
				res.put("savepath", filePath);
				res.put("url", Constant.SITE_URL + "/" + filePath);
				
				response.json(res.toString());
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
	
	/**
	 * 收藏和取消收藏
	 */
	@Route(value = "/favorite", method = HttpMethod.POST)
	public void favorite(Request request, Response response){
		LoginUser user = SessionKit.getLoginUser();
		if(null == user){
			response.text(this.SIGNIN);
			return;
		}
		
		// topic：帖子，node：节点
		String type = request.query("type");
		Long tid = request.queryAsLong("tid");
		
		if(StringKit.isBlank(type) || null == tid || tid == 0){
			return;
		}
		
		Long count = favoriteService.favorite(type, user.getUid(), tid);
		JSONObject res = new JSONObject();
		res.put("count", count);
		response.json(res.toString());
		
	}
	
	/**
	 * 点赞和取消赞
	 */
	@Route(value = "/love", method = HttpMethod.POST)
	public String love(Request request, Response response){
		
		LoginUser user = SessionKit.getLoginUser();
		if(null == user){
			response.text(this.SIGNIN);
			return null;
		}
		
		Long tid = request.queryAsLong("tid");
		if(null == tid || tid == 0){
			return null;
		}
		
		try {
			long count = topicService.love(user.getUid(), tid);
			response.text(count+"");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * markdown页面
	 */
	@Route(value = "/markdown", method = HttpMethod.GET)
	public ModelAndView markdown(Request request, Response response){
		return this.getView("markdown");
	}
	
}
