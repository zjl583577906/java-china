package com.javachina.service;

import java.util.List;
import java.util.Map;

public interface NoticeService {
	
	boolean save(String type, Long uid, Long to_uid, Long event_id);
	
	List<Map<String, Object>> read(Long uid);
}
