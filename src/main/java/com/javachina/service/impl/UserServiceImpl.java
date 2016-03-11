package com.javachina.service.impl;

import java.util.List;

import com.blade.ioc.annotation.Inject;
import com.blade.ioc.annotation.Service;
import com.blade.jdbc.AR;
import com.blade.jdbc.Page;
import com.blade.jdbc.QueryParam;
import com.javachina.Constant;
import com.javachina.kit.TaskKit;
import com.javachina.model.User;
import com.javachina.service.ActivecodeService;
import com.javachina.service.UserService;

import blade.kit.DateKit;
import blade.kit.EncrypKit;
import blade.kit.MailKit;
import blade.kit.StringKit;

@Service
public class UserServiceImpl implements UserService {
	
	@Inject
	private ActivecodeService activecodeService;
	
	@Override
	public User getUser(Integer uid) {
		return AR.findById(User.class, uid);
	}
	
	@Override
	public User getUser(QueryParam queryParam) {
		return AR.find(queryParam).first(User.class);
	}
		
	@Override
	public List<User> getUserList(QueryParam queryParam) {
		if(null != queryParam){
			return AR.find(queryParam).list(User.class);
		}
		return null;
	}
	
	@Override
	public Page<User> getPageList(QueryParam queryParam) {
		if(null != queryParam){
			return AR.find(queryParam).page(User.class);
		}
		return null;
	}
	
	@Override
	public boolean signup(String loginName, String passWord, String email) {
		if(StringKit.isBlank(loginName) || StringKit.isBlank(passWord) || StringKit.isBlank(email)){
			return false;
		}
		int time = DateKit.getCurrentUnixTime();
		String pwd = EncrypKit.md5(loginName + passWord);
		try {
			
			Integer uid = (Integer) AR.update("insert into t_user(login_name, pass_word, email, create_time, update_time) values(?, ?, ?, ?, ?)",
					loginName, pwd, email, time, time).key();
			
			// 异步发送邮件通知
			String code = activecodeService.save(uid, "signup");
			sendSignupMail(email, code);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 发送注册邮件
	 * @param email
	 */
	private void sendSignupMail(final String email, final String code){
		Runnable t = new Runnable() {
			@Override
			public void run() {
				String href = Constant.SITE_URL + "/active?code=" + code;
				MailKit.asynSend(email, Constant.MAIL_ACTIVE_TITLE, String.format(Constant.MAIL_ACTIVE_CONTENT, Constant.SITE_NAME, href, href));
			}
		};
		TaskKit.run(t);
	}
	
	@Override
	public boolean delete(Integer uid) {
		if(null != uid){
			AR.update("delete from t_user where uid = ?", uid).commit();
			return true;
		}
		return false;
	}

	@Override
	public boolean resetPwd(String email) {
		
		return false;
	}

	@Override
	public User signin(String loginName, String passWord) {
		if(StringKit.isBlank(loginName) || StringKit.isBlank(passWord)){
			return null;
		}
		String pwd = EncrypKit.md5(loginName + passWord);
	    User user = AR.find("select * from t_user where login_name = ? and pass_word = ? and status = ?",
				loginName, pwd, 1).first(User.class);
		return user;
	}
		
}
