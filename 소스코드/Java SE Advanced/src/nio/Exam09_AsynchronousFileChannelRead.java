package nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


// 개인이 고민해보라고 하심
public class Exam09_AsynchronousFileChannelRead {

	public static void main(String[] args) {
		
		int coreNum = Runtime.getRuntime().availableProcessors();
		ExecutorService executorService = Executors.newFixedThreadPool(coreNum);
		
		for(int i = 0 ; i < 10 ; i ++) {
			Path path = Paths.get("asset/file" + i + ".txt");
			
			try {
				AsynchronousFileChannel asyncFileChannel = 
						AsynchronousFileChannel.open(path, EnumSet.of(StandardOpenOption.READ), executorService);
				
				ByteBuffer buffer = ByteBuffer.allocate(100);
				
				String result = "";
				
				while(true) {
					
				}
				
				
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
}
