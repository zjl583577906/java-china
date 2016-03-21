package com.javachina.service;

public interface SendMailService {

	void signup(String username, String email, final String code);
	
}
