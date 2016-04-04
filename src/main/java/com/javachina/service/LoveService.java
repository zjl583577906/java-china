package com.javachina.service;

public interface LoveService {
	
	Long love(Long uid, Long tid);

	boolean isLove(Long uid, Long tid);
	
	Long getLoveCount(Long tid);
}
