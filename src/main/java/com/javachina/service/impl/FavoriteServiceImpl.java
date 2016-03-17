package com.javachina.service.impl;

import java.util.List;
import com.blade.jdbc.AR;
import com.blade.jdbc.Page;
import com.blade.jdbc.QueryParam;
import com.blade.ioc.annotation.Service;
import com.javachina.model.Favorite;
import com.javachina.service.FavoriteService;

@Service
public class FavoriteServiceImpl implements FavoriteService {
	
	@Override
	public Favorite getFavorite(Integer id) {
		return AR.findById(Favorite.class, id);
	}
		
	@Override
	public List<Favorite> getFavoriteList(QueryParam queryParam) {
		if(null != queryParam){
			return AR.find(queryParam).list(Favorite.class);
		}
		return null;
	}
	
	@Override
	public Page<Favorite> getPageList(QueryParam queryParam) {
		if(null != queryParam){
			return AR.find(queryParam).page(Favorite.class);
		}
		return null;
	}
	
	@Override
	public boolean save( String type, Integer uid, Integer eventId, Integer createTime ) {
		return false;
	}
	
	@Override
	public boolean delete(Integer id) {
		if(null != id){
			AR.update("delete from t_favorite where id = ?", id).executeUpdate();
			return true;
		}
		return false;
	}
		
}
