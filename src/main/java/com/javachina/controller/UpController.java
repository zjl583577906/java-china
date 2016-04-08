package com.javachina.controller;

import java.io.File;
import java.util.Date;

import com.blade.Blade;
import com.blade.route.annotation.Path;
import com.blade.route.annotation.Route;
import com.blade.web.http.HttpMethod;
import com.blade.web.http.Request;
import com.blade.web.http.Response;
import com.blade.web.multipart.FileItem;
import com.javachina.Constant;
import com.javachina.kit.ImageKit;

import blade.kit.DateKit;
import blade.kit.FileKit;
import blade.kit.PatternKit;
import blade.kit.StringKit;
import blade.kit.json.JSONObject;

@Path("/")
public class UpController extends BaseController {

	@Route(value = "/up_img", method = HttpMethod.POST)
	public void uploadimg(Request request, Response response){
		FileItem[] fileItems = request.files();
		if(null != fileItems && fileItems.length > 0){
			
			FileItem fileItem = fileItems[0];
			
			String type = request.query("type");
			String suffix = FileKit.getExtension(fileItem.getFileName());
			if(StringKit.isNotBlank(suffix)){
				suffix = "." + suffix;
			}
			if(!PatternKit.isImage(suffix)){
				return;
			}
			
			if(null == type){
				type = "temp";
			}
			
			String saveName = DateKit.dateFormat(new Date(), "yyyyMMddHHmmssSSS")  + "_" + StringKit.getRandomChar(10) + suffix;
			File file = new File(Blade.me().webRoot() + File.separator + Constant.UPLOAD_FOLDER + File.separator + saveName);
			
			try {
				
				ImageKit.copyFileUsingFileChannels(fileItem.getFile(), file);
				
				String filePath = Constant.UPLOAD_FOLDER + "/" + saveName;
				
				JSONObject res = new JSONObject();
				res.put("status", 200);
				res.put("savekey", filePath);
				res.put("savepath", filePath);
				res.put("url", Constant.SITE_URL + "/" + filePath);
				
				response.json(res.toString());
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
	
	
}
