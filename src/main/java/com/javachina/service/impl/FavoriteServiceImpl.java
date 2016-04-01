package com.javachina.service.impl;

import java.util.List;

import com.blade.ioc.annotation.Service;
import com.blade.jdbc.AR;
import com.blade.jdbc.Page;
import com.blade.jdbc.QueryParam;
import com.javachina.model.Favorite;
import com.javachina.service.FavoriteService;

import blade.kit.StringKit;

@Service
public class FavoriteServiceImpl implements FavoriteService {
	
	public Favorite getFavorite(String type,Long uid, Long event_id) {
		return AR.find(QueryParam.me().eq("type", type).eq("uid", uid).eq("event_id", event_id)).first(Favorite.class);
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
	public Long favorite(String type, Long uid, Long event_id) {
		
		Favorite favorite = this.getFavorite(type, uid, event_id);
		try {
			
			if(null == favorite){
				AR.update("insert into t_favorite(type, uid, event_id) values(?, ?, ?)", type, uid, event_id).executeUpdate();
			} else {
				AR.update("delete from t_favorite where id = ?", favorite.getId()).executeUpdate();
			}
			
			return AR.find("select count(1) from t_favorite where type = ? and event_id = ?", type, event_id).first(Long.class);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0L;
	}

	@Override
	public boolean isFavorite(String type, Long uid, Long event_id) {
		if (StringKit.isBlank(type) || null == uid || null == event_id) {
			return false;
		}
		return null != this.getFavorite(type, uid, event_id);
	}
	
}
