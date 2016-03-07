package com.javachina.service;

import java.util.List;

import com.blade.jdbc.Page;
import com.blade.jdbc.QueryParam;
import com.javachina.model.User;

public interface UserService {
	
	User getUser(Integer uid);
	
	User getUser(QueryParam queryParam);
	
	List<User> getUserList(QueryParam queryParam);
	
	Page<User> getPageList(QueryParam queryParam);
	
	boolean signup(String loginName, String passWord, String avatar, String email);
	
	User signin(String loginName, String passWord);
	
	boolean delete(Integer uid);
	
	boolean resetPwd(String email);
	
}
