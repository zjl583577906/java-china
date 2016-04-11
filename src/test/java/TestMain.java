import java.util.ArrayList;
import java.util.List;

import com.javachina.model.User;

import blade.kit.json.JSONArray;
import blade.kit.json.JSONKit;
import blade.kit.json.JSONObject;

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
		
		String content_ = "hahah @biezhi hello".replaceAll("@([a-zA-Z_0-9-]+)\\s", "<a href='/$1' target='_blank'>$1</a>&nbsp;");

		System.out.println(content_);
		
	}

}
