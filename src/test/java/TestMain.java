import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.javachina.Constant;
import com.javachina.kit.Utils;

public class TestMain {

	public static void main(String[] args) {
		/*String str = "## hahah"
				+ "@biezhi niaho, @haha thanks! @aaa i";
		
		Pattern pattern= Pattern.compile("\\@([a-zA-Z_0-9-]+)\\s");
		
		Matcher matcher = pattern.matcher(str);
		while(matcher.find()){
			System.out.println(matcher.group(0));
			System.out.println(matcher.group(1));
		}*/
		
		System.out.println(Utils.isSignup("jelly_8090"));
		
		try {
			String url = "https://github.com/login/oauth/authorize?client_id=%s&redirect_uri=%s";
			String redirect_uri = URLEncoder.encode("http://beta.java-china.org/oauth/github/call", "utf-8");
			System.out.println(String.format(url, "5dae", redirect_uri));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

}
