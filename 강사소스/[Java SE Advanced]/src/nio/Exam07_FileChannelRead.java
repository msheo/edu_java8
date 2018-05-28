package nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Exam07_FileChannelRead {

	public static void main(String[] args) {
		
		// 1. Path객체
		Path path = Paths.get("asset/file.txt");
		
		// 2. FileChannel Open
		try {
			FileChannel fileChannel = FileChannel.open(path, 
					StandardOpenOption.READ);
			
			// 3. 파일로부터 데이터를 받아오기 위해서 ByteBuffer를 allocation
			ByteBuffer buffer = ByteBuffer.allocate(100);
			Charset charset = Charset.forName("UTF-8");
			String result = "";
			int code = 0;
			// 4. 파일로 부터 read
			while(true) {
				code = fileChannel.read(buffer);  // code : 읽어들인 byte의 수.
				if(code == -1) {
					// 읽을게 없어..다 읽었다는 의미.
					break;   // 로프탈출을 위해 break;
				}
				buffer.flip();
				result += charset.decode(buffer).toString();
				buffer.clear();
			}
			fileChannel.close();
			System.out.println("읽어들인 내용은 : " + result);
			
		} catch (IOException e) {

			e.printStackTrace();
		}
		

	}

}
