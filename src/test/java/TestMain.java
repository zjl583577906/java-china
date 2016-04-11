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
		
		System.out.println(Utils.isSignup("hello"));
		
	}

}
