package com.javachina.service.impl;

import java.util.List;
import com.blade.jdbc.AR;
import com.blade.jdbc.Page;
import com.blade.jdbc.QueryParam;
import com.blade.ioc.annotation.Service;
import com.javachina.model.Link;
import com.javachina.service.LinkService;

@Service
public class LinkServiceImpl implements LinkService {
	
	@Override
	public Link getLink(Integer id) {
		return AR.findById(Link.class, id);
	}
		
	@Override
	public List<Link> getLinkList(QueryParam queryParam) {
		if(null != queryParam){
			return AR.find(queryParam).list(Link.class);
		}
		return null;
	}
	
	@Override
	public Page<Link> getPageList(QueryParam queryParam) {
		if(null != queryParam){
			return AR.find(queryParam).page(Link.class);
		}
		return null;
	}
	
	@Override
	public boolean save( String title, String url, Integer createTime ) {
		return false;
	}
	
	@Override
	public boolean delete(Integer id) {
		if(null != id){
			AR.update("delete from t_link where id = ?", id).commit();
			return true;
		}
		return false;
	}
		
}
