package com.javachina.kit;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import com.javachina.Constant;

public class ImageKit {

	public static String getAvatar(String avatar){
		return Constant.SITE_URL + "/assets/avatar/" + avatar;
	}
	
	public static String getImgURL(String path){
		return Constant.SITE_URL + "/assets/images/" + path;
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
}
