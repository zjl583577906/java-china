package com.javachina.kit;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.blade.web.http.Request;
import com.javachina.ImageTypes;

import blade.kit.StringKit;

public class Utils {

	public static String getIpAddr(Request request) {
		if (null == request) {
			return "0.0.0.0";
		}
		String ip = request.header("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.header("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.header("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.address();
		}
		return ip;
	}
	
	public static Set<String> getAtUsers(String str){
		Set<String> users = new HashSet<String>();
		if(StringKit.isNotBlank(str)){
			Pattern pattern= Pattern.compile("\\@([a-zA-Z_0-9-]+)\\s");
			Matcher matcher = pattern.matcher(str);
			while(matcher.find()){
//				System.out.println(matcher.group(0));
//				System.out.println(matcher.group(1));
				users.add(matcher.group(1));
			}
		}
		
		return users;
	}
	
	public static String getAvatar(String avatar, ImageTypes imageTypes){
		return QiniuKit.getUrl(avatar + '-' + imageTypes.toString());
	}
	
	@SuppressWarnings("resource")
	public static void copyFileUsingFileChannels(File source, File dest) throws IOException {    
        FileChannel inputChannel = null;    
        FileChannel outputChannel = null;    
	    try {
	        inputChannel = new FileInputStream(source).getChannel();
	        outputChannel = new FileOutputStream(dest).getChannel();
	        outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
	    } finally {
	        inputChannel.close();
	        outputChannel.close();
	    }
	}
	
	public static Integer getTodayTime() {
		Calendar today = Calendar.getInstance();
		today.set(Calendar.HOUR_OF_DAY, 0);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);
		return Integer.valueOf(String.valueOf(today.getTimeInMillis()).substring(0, 10));
	}
	
	public static Integer getYesterdayTime() {
		Calendar today = Calendar.getInstance();
		today.set(Calendar.HOUR_OF_DAY, -24);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);
		return Integer.valueOf(String.valueOf(today.getTimeInMillis()).substring(0, 10));
	}

	public static Integer getTomorrowTime() {
		Calendar tomorrow = Calendar.getInstance();
		tomorrow.set(Calendar.HOUR_OF_DAY, 24);
		tomorrow.set(Calendar.MINUTE, 0);
		tomorrow.set(Calendar.SECOND, 0);
		return Integer.valueOf(String.valueOf(tomorrow.getTimeInMillis()).substring(0, 10));
	}
	
	public static void run(Runnable t){
		Executors.newSingleThreadExecutor().submit(t);
	}
	
}
