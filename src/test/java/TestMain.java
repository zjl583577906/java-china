import java.io.File;

import blade.kit.FileKit;

public class TestMain {

	public static void main(String[] args) {
		File[] files = FileKit.listDirSuffixFiles("/Users/Anne/workspace/java/java-china/src/main/webapp/assets/emojis", "png");
		StringBuffer sBuffer = new StringBuffer();
		for(File file : files){
			String name = file.getName();
			sBuffer.append("\""+name.substring(0, name.length() - 4)+"\", ");
		}
		System.out.println(sBuffer.toString());
	}

}
