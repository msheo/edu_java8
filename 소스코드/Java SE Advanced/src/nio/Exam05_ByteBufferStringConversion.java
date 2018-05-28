package nio;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class Exam05_ByteBufferStringConversion {

	public static void main(String[] args) {
		
		// 문자열을 ByteBuffer로 변환
		Charset charset = Charset.forName("UTF-8");
		
		String data = "이것은 소리없는 아우성";
		
		ByteBuffer buffer = charset.encode(data);	// 문자열을 ByteBuffer로 encoding, 지금의 경우는 non-direct buffer, 자동으로 ByteBuffer 크기를 계산해서 공간을 확보함
		
		// ByteBuffer 안에 있는 데이터를 문자열로 변환
		data = charset.decode(buffer).toString();
		System.out.println(data);
		
	}
	
}
