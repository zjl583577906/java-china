package com.javachina.service.impl;

import com.blade.ioc.annotation.Service;
import com.blade.jdbc.AR;
import com.blade.jdbc.QueryParam;
import com.javachina.model.Love;
import com.javachina.service.LoveService;

@Service
public class LoveServiceImpl implements LoveService {
	
	public Love getLove(Long uid, Long tid) {
		return AR.find(QueryParam.me().eq("uid", uid).eq("tid", tid)).first(Love.class);
	}
	
	@Override
	public Long love(Long uid, Long tid) {
		Love love = this.getLove(uid, tid);
		try {
			
			if(null == love){
				AR.update("insert into t_love(uid, tid) values(?, ?)", uid, tid).executeUpdate();
			} else {
				AR.update("delete from t_love where id = ?", love.getId()).executeUpdate();
			}
			
			return AR.find("select count(1) from t_love where tid = ?", tid).first(Long.class);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0L;
	}

	@Override
	public boolean isLove(Long uid, Long tid) {
		if(null != uid && null != tid){
			return null != this.getLove(uid, tid);
		}
		return false;
	}

	@Override
	public Long getLoveCount(Long tid) {
		if(null != tid){
			return AR.find("select count(1) from t_love where tid = ?", tid).first(Long.class);
		}
		return 0L;
	}
		
}
