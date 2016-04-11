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
		
		List<User> users = new ArrayList<User>();
		User u1 = new User();
		u1.setAvatar("aaa");
		u1.setUid(22L);
		users.add(u1);
		
		/*JSONObject jsonObject = new JSONObject(u1);
		System.out.println(jsonObject);
		
		JSONArray obj = new JSONArray(users);
        System.out.println(obj.toString());
		*/
		
		System.out.println(JSONKit.toJSONString(users, true));
		
		JSONObject obj1 = new JSONObject();
		obj1.put("name", "jack");
		obj1.put("user", u1);
		
		System.out.println(obj1.toString());
		System.out.println(obj1.getString("qq"));
		
		JSONArray arr1 = new JSONArray();
		arr1.add("aaa");
		
		System.out.println(arr1.toString());
		
		String json = "{\"login_name\":\"jack\"}";
		
		User u2 = JSONKit.parse(json, User.class);
		System.out.println(u2);
		
		
	}

}
