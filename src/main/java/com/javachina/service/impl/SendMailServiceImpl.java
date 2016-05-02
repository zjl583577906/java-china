package com.javachina.service.impl;

import com.blade.ioc.annotation.Component;
import com.blade.ioc.annotation.Inject;
import com.javachina.Actions;
import com.javachina.Constant;
import com.javachina.service.SendMailService;
import com.javachina.service.UserlogService;

import blade.kit.http.HttpRequest;
import blade.kit.json.JSONArray;
import blade.kit.json.JSONObject;
import blade.kit.logging.Logger;
import blade.kit.logging.LoggerFactory;

@Component
public class SendMailServiceImpl implements SendMailService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SendMailServiceImpl.class);
	
	final String url = "http://api.sendcloud.net/apiv2/mail/sendtemplate";
	
	@Inject
	private UserlogService userlogService;
	
	@Override
	public void signup(String username, String email, String code) {

		String link_url = Constant.SITE_URL + "/active/" + code;
		
		String url = "<a href='"+link_url+"'>"+ link_url + "</a>";
		
		JSONObject x_smtpapi = new JSONObject();
		x_smtpapi.put("to", new JSONArray().add(email));
		JSONObject sub = new JSONObject();
		sub.put("%username%", new JSONArray().add(username));
		sub.put("%url%", new JSONArray().add(link_url));
		x_smtpapi.put("sub", sub);
		
		System.out.println(x_smtpapi.toString());
		
		String body = HttpRequest.post(url, true, 
				"apiUser", Constant.MAIL_API_USER,
				"apiKey", Constant.MAIL_API_KEY,
				"templateInvokeName", "java_china_signup",
				"from", "support@java-china.org",
				"fromName", "Java中国",
				"xsmtpapi", x_smtpapi.toString(),
				"subject", "Java中国账户激活").body();
		System.out.println(body);
		
		/*
		JetEngine engine = JetEngine.create();
		
		JetTemplate template = engine.getTemplate("/template/register.html");
		
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("username", username);
		context.put("email", Constant.MAIL_ADMIN);
		context.put("url", url);

		StringWriter writer = new StringWriter();
		template.render(context, writer);
		
		String output = writer.toString();
		
		MailMessage mailMessage = new MailMessage();
		mailMessage
		.subject("注册激活邮件")
		.from(Constant.MAIL_NICK, Constant.MAIL_USER)
		.content(output)
		.addTo(email);
		
		mailSender.host(Constant.MAIL_HOST).username(Constant.MAIL_USER).password(Constant.MAIL_PASS);
		
		mailSender.send(mailMessage, true);*/
		
		userlogService.save(0L, Actions.SEND_MAIL, email + ":" + code + ":" + "signup");
		
		LOGGER.info("user {} signup, send mail [{}] success, code = [{}]", username, email, code);
	}

	@Override
	public void forgot(String username, String email, String code) {
		String link_url = Constant.SITE_URL + "/active/" + code;
		
		String url = "<a href='"+link_url+"'>"+ link_url +"</a>";
		
		JSONObject x_smtpapi = new JSONObject();
		x_smtpapi.put("to", new JSONArray().add(email));
		JSONObject sub = new JSONObject();
		sub.put("%username%", new JSONArray().add(username));
		sub.put("%url%", new JSONArray().add(link_url));
		x_smtpapi.put("sub", sub);
		
		System.out.println(x_smtpapi.toString());
		
		String body = HttpRequest.post(url, true, 
				"apiUser", Constant.MAIL_API_USER,
				"apiKey", Constant.MAIL_API_KEY,
				"templateInvokeName", "java_china_forgot",
				"from", "support@java-china.org",
				"fromName", "Java中国",
				"xsmtpapi", x_smtpapi.toString(),
				"subject", "Java中国密码找回").body();
		System.out.println(body);
		
		/*JetEngine engine = JetEngine.create();
		
		JetTemplate template = engine.getTemplate("/template/forgot.html");
		
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("username", username);
		context.put("email", Constant.MAIL_ADMIN);
		context.put("url", url);
		
		StringWriter writer = new StringWriter();
		template.render(context, writer);
		
		String output = writer.toString();
		
//			System.out.println(output);
		
		MailMessage mailMessage = new MailMessage();
		mailMessage
		.subject("找回密码邮件")
		.from(Constant.MAIL_NICK, Constant.MAIL_USER)
		.content(output)
		.addTo(email);
		
		mailSender.host(Constant.MAIL_HOST).username(Constant.MAIL_USER).password(Constant.MAIL_PASS);
		
		mailSender.send(mailMessage, true);*/
		
		userlogService.save(0L, Actions.SEND_MAIL, email + ":" + code + ":" + "forgot");
		
		LOGGER.info("user {} forgot, send mail [{}] success, code = [{}]", username, email, code);
	}

}
