package com.javachina.service.impl;

import com.blade.ioc.annotation.Service;
import com.javachina.service.NoticeService;

@Service
public class NoticeServiceImpl implements NoticeService {
	
	@Override
	public boolean save(String type, Long uid, Long to_uid, Long event_id) {
		
		return false;
	}

	@Override
	public boolean read(String type, Integer event_id) {
		
		return false;
	}

}
