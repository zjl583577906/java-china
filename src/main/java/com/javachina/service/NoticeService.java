package com.javachina.service;

public interface NoticeService {
	
	boolean save(String type, Long uid, Long to_uid, Long event_id);
	
	boolean read(String type, Integer event_id);
		
}
