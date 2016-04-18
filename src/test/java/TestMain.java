import com.javachina.kit.Utils;

public class TestMain {

	public static void main(String[] args) {
		String aaa = "```html\n"
				+ "<script>alert('xss test');</script>\n"
				+ "```";
		
		System.out.println(aaa);
		
		System.out.println("==================================");
		
		String html = Utils.markdown2html(aaa);
		System.out.println(html);
		
		System.out.println("==================================");
	}

}
