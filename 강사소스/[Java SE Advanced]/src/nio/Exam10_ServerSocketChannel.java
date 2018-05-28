package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Exam10_ServerSocketChannel extends Application {

	TextArea textarea;
	Button startBtn;
	
	// 문자열 받아서 textarea에 출력
	private void printMsg(String msg) {
		Platform.runLater(() -> textarea.appendText("[" + msg + "] \n"));
	}
	
	private void startserver() {
	 // 네트워크를 통해서 클라이언트의 접속을 받을 수 있는 서버 Thread를 생성.
		new Thread(()->{
			try {
				ServerSocketChannel server = ServerSocketChannel.open();
				server.configureBlocking(true);
				server.bind(new InetSocketAddress(5555));  // 5555 포트번호로 접속을 받아요.
				
				while(true) {
					printMsg("클라이언트 접속 대기중!");
					SocketChannel socketChannel = server.accept(); // 일단 대기... 클라이언트 접속이 들어올때까지
					// 접속이 성공. 클라이언트에게 환영합니다. 라는 메시지를 전달.
					Charset charset = Charset.forName("UTF-8");
					ByteBuffer buffer = charset.encode("환영합니다.!!");
					socketChannel.write(buffer);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}).start();
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
	
		BorderPane root = new BorderPane();
		root.setPrefSize(700, 500);
		
		textarea = new TextArea();
		textarea.setEditable(false);
		root.setCenter(textarea);
		
		BorderPane bottom = new BorderPane();
		bottom.setPrefSize(700, 50);
		
		startBtn = new Button("서버실행!!");
		startBtn.setOnAction(e->{
			startserver();
		});
		bottom.setCenter(startBtn);
		
		root.setBottom(bottom);
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
		
	}
	
	public static void main(String[] args) {
		launch();

	}

}
