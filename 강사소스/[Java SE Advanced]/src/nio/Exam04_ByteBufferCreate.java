package nio;

import java.nio.ByteBuffer;

public class Exam04_ByteBufferCreate {

	public static void main(String[] args) {

		int capacity = 10*1024*1024*1024;   // M 정도의 버퍼크기
		
		long start,end;
		
		start = System.nanoTime();
		// ByteBuffer를 생성할 꺼예요. ( non-direct방식 )
		// JVM이 관리하는 Heap영역에 생성. 빠른생성이 가능.대신 입출력처리는 늦어요.(오버헤드가 존재)
		ByteBuffer buffer = ByteBuffer.allocate(capacity);
		end = System.nanoTime();
		System.out.println("버퍼생성에 걸린 시간 : " + (end-start));

	}

}
