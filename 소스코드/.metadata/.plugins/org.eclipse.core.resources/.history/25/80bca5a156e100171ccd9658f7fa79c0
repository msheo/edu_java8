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
		
		// 1. Path 객체
		Path path = Paths.get("asset/FileChannelWriteAndRead.txt");
		
		// 2. FileChannel Open
		try {
			FileChannel fileChannel = FileChannel.open(path, StandardOpenOption.READ);
			
			// 3. 파일로부터 데이터를 받아오기 위해서 ByteBuffer allocation
			ByteBuffer buffer = ByteBuffer.allocate(100);
			Charset charset = Charset.forName("UTF-8");
			
			String result = "";
			int code = 0;
			
			// 4. 파일로부터 read
			while (true) {
				code = fileChannel.read(buffer);	// code : 읽어드린 바이트 수 
				if( code == -1) {
					break;	// -1 은 전부 읽었다는 뜻 -> 루프탈출
				}
				buffer.flip();	// 읽은 버퍼를 다시 toString() 하기 위하여 position을 다시 원위치로 옮겨서 limit까지 toString() 해줘야 해
				result += charset.decode(buffer).toString();
				buffer.clear();	// decode 했으니까 다시 또 position이 끝으로 이동해.. 따라서 clear() 해줘야 해
			}
			
			fileChannel.close();
			System.out.println("읽어드린 내용은 : " + result);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
}
