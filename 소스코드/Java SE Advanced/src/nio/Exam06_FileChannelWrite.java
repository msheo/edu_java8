package nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Exam06_FileChannelWrite {
	
	public static void main(String[] args) {
		
		// 1. 어떤 파일에 write 할지 결정
		Path path = Paths.get("asset/FileChannelWriteAndRead.txt");
		
		// 2. 해당 경로객체를 이용해서 File Channel을 open
		try {
			FileChannel fileChannel = FileChannel.open(path, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
			
			//3. CharSet 필요 - UTF-8 문자열을 ByteBuffer로 변환하기 위하여 
			Charset charset = Charset.forName("UTF-8");
			ByteBuffer buffer = charset.encode("이것은 소리없는 아우성");
			int size = fileChannel.write(buffer);
			System.out.println("파일 쓰기 성공 : " + size + "byte(s)" );
			fileChannel.close();

		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		
	}
	
}
