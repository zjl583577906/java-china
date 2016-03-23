import com.github.rjeschke.txtmark.Processor;

public class MDTest {

	public static void main(String[] args) {
		String processed = Processor.process("## Hello World");
		System.out.println(processed);
	}

}
