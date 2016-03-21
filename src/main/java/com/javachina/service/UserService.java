package com.javachina.service;

import java.util.List;
import java.util.Map;

import com.blade.jdbc.Page;
import com.blade.jdbc.QueryParam;
import com.javachina.model.User;

public interface UserService {
	
	User getUser(Integer uid);
	
	User getUser(QueryParam queryParam);
	
	Map<String, Object> getUserDetail(Integer uid);
	
	List<User> getUserList(QueryParam queryParam);
	
	Page<User> getPageList(QueryParam queryParam);
	
	boolean signup(String loginName, String passWord, String email);
	
	User signin(String loginName, String passWord);
	
	boolean delete(Integer uid);
	
	boolean updateStatus(Integer uid, Integer status);
	
	boolean resetPwd(String email);

	boolean active(Integer id, Integer uid);
	
}
