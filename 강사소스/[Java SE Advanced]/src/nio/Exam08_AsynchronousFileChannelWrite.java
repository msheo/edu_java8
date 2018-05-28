package nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Exam08_AsynchronousFileChannelWrite {

	public static void main(String[] args) {
		
		// 현재 가용 Core수를 알아보아요.
		int coreNum = Runtime.getRuntime().availableProcessors();
		System.out.println(coreNum);
		
		// 1.ThreadPool부터 만들어요.
		ExecutorService executorService = 
				Executors.newFixedThreadPool(coreNum);
		
		for(int i=0; i<10; i++) {
			// 2. 파일 경로를 생성
			Path path = Paths.get("asset/file" + i + ".txt");   // file0.txt , file1.txt, file2.txt
			Charset charset = Charset.forName("UTF-8");
			
			try {
				AsynchronousFileChannel asynchFileChannel =
						AsynchronousFileChannel.open(path, 
								EnumSet.of(StandardOpenOption.CREATE, StandardOpenOption.WRITE) ,
								executorService);
				ByteBuffer buffer = charset.encode("이것은 소리없는 아우성 : " + i);

				// 3. 나중에 callback method에 전달될 인자 객체.
				class Attachment {
					AsynchronousFileChannel asynchFileChannel;
					Path path;
				}
				Attachment attachment = new Attachment();
				attachment.asynchFileChannel = asynchFileChannel;
				attachment.path = path;
				
				// 0 : buffer의 시작위치
				// 객체1 : 나중에 객체2가 가지고 있는 method가 호출되는데 이때 전달되는 객체
				// 객체2 : 이벤트 객체로 만약 파일처리가 정상처리되면 이 객체안의 특정 method가 호출
				//         만약 정상처리가 안되면 특정 method가 호출.
				
				// 4. 나중에 사용될 callback method를 가지고 있는 Event 객체 생성
				CompletionHandler<Integer, Attachment> handler = 
						new CompletionHandler<Integer, Attachment>() {

							@Override
							public void completed(Integer result, Attachment attachment) {
								// 작업이 다 끝나면 자동으로 호출
								System.out.println("작업파일명 : " + attachment.path.getFileName() + " : "
										+ Thread.currentThread().getName());
								try {
									attachment.asynchFileChannel.close();
								} catch (IOException e) {
									e.printStackTrace();
								}
							}

							@Override
							public void failed(Throwable exc, Attachment attachment) {
								// 작업 중 에러가 발생하면 호출
								System.out.println("먼가 이상해요");
								
							}
					
				};
				
				// blocking이 걸리지 않아요.. ( thread 파생작업 )
				asynchFileChannel.write(buffer,0, attachment, handler);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
					
		}

	}

}

