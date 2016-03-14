package com.javachina.kit;

import com.javachina.Constant;

public class ImageKit {

	public static String getAvatar(String avatar){
		return Constant.SITE_URL + "/assets/avatar/" + avatar;
	}
	
	public static String getImgURL(String path){
		return Constant.SITE_URL + "/assets/images/" + path;
	}
	
}
