import com.javachina.ext.markdown.Processor;
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
		
//		System.out.println(Utils.isSignup("jelly_8090"));
		
//		String safe = Jsoup.clean("## hello\r\n > nihao <script>alert('xss');</script>", Whitelist.basic());
//		System.out.println("safe = " + safe);
		
//		System.out.println(Processor.process(" > hello", true));
//		System.out.println(Processor.process("<script>alert(1);</script>", true));
	
		String sql = "update nb set a = a, b = b, where c = sb";
		
		int pos = sql.lastIndexOf(",");
		String a = sql.substring(0, pos) + sql.substring(pos + 1);
		System.out.println(a);
	}

}
