package com.javachina.controller.admin;

import java.util.List;
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
import com.javachina.controller.BaseController;
import com.javachina.model.Node;
import com.javachina.service.NodeService;
import com.javachina.service.TopicService;

@Path("/admin/")
public class IndexController extends BaseController {

	@Inject
	private TopicService topicService;
	
	@Inject
	private NodeService nodeService;
	
	/**
	 * 首页
	 */
	@Route(value = "/")
	public ModelAndView show_home(Request request, Response response){
		return this.getAdminView("home");
	}
	
	/**
	 * 节点列表页面
	 */
	@Route(value = "nodes")
	public ModelAndView show_nodes(Request request, Response response){
		Integer page = request.queryAsInt("p");
		if(null == page || page < 1){
			page = 1;
		}
		QueryParam np = QueryParam.me();
		np.eq("is_del", 0).orderby("topics desc").page(page, 10);
		Page<Map<String, Object>> nodePage = nodeService.getPageList(np);
		request.attribute("nodePage", nodePage);
		return this.getAdminView("nodes");
	}
	
	/**
	 * 添加节点页面
	 */
	@Route(value = "nodes/add", method = HttpMethod.GET)
	public ModelAndView show_add_node(Request request, Response response){
		putData(request);
		return this.getAdminView("add_node");
	}
	
	public void putData(Request request){
		QueryParam np = QueryParam.me();
		np.eq("is_del", 0).eq("pid", 0).orderby("topics desc");
		List<Node> nodes = nodeService.getNodeList(np);
		request.attribute("nodes", nodes);
	}
	
	/**
	 * 添加新节点 
	 * @return
	 */
	@Route(value = "nodes/add", method = HttpMethod.POST)
	public ModelAndView add_node(Request request, Response response){
		String node_name = request.query("node_name");
		String node_slug = request.query("node_slug");
		Integer pid = request.queryAsInt("pid");
		String node_pic = request.query("node_pic");
		
		
		
		return this.getAdminView("add_node");
	}
	
	/**
	 * 编辑节点页面
	 */
	@Route(value = "nodes/:nid")
	public ModelAndView show_edit_node(Request request, Response response){
		return this.getAdminView("edit_node");
	}
	
	/**
	 * 帖子列表页面
	 */
	@Route(value = "topics")
	public ModelAndView show_topics(Request request, Response response){
		return this.getAdminView("topics");
	}
	
	/**
	 * 用户列表页面
	 */
	@Route(value = "users")
	public ModelAndView show_users(Request request, Response response){
		return this.getAdminView("users");
	}
	
	/**
	 * 友链页面
	 */
	@Route(value = "links")
	public ModelAndView show_links(Request request, Response response){
		return this.getAdminView("links");
	}
	
	/**
	 * 系统设置页面
	 */
	@Route(value = "settings")
	public ModelAndView show_settings(Request request, Response response){
		return this.getAdminView("settings");
	}
	
}
