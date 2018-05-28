package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class Exam11_SocketChannel {

	public static void main(String[] args) {

		// 클라이언트는 시작하면
		// 바로 서버에 네트워크 접속을 시도.
		// SocketChannel을 이용해서 서버에 접속
		try {
			SocketChannel client = SocketChannel.open();
			client.configureBlocking(true);
			client.connect(new InetSocketAddress("localhost", 5555));
			System.out.println("서버접속 성공");
			
			ByteBuffer buffer = ByteBuffer.allocate(1000);
			int size = client.read(buffer);  // size는 받은 데이터의 양.
			Charset charset = Charset.forName("UTF-8");
			buffer.flip();
			String result = charset.decode(buffer).toString();
			System.out.println("서버에서 온 메시지는 : " + result);
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
