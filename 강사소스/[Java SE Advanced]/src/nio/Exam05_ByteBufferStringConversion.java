package nio;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class Exam05_ByteBufferStringConversion {

	public static void main(String[] args) {

		// 문자열을 byteBuffer로 변환할려고 해요.
		
		Charset charset = Charset.forName("UTF-8");
		
		String data = "이것은 소리없는 아우성!!";
		
		ByteBuffer buffer = charset.encode(data);   // 문자열을 ByteBuffer로 encoding
		                                            // non-direct ByteBuffer
		                                            // 자동으로 ByteBuffer의 크기를 계산해서 공간 확보
		
		// ByteBuffer안에 있는 데이터를 문자열로 환원
		data = charset.decode(buffer).toString();
		System.out.println(data);
		
		

	}

}
