package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Exam13_ChatClient extends Application {

	TextArea textarea;
	TextField input;
	Button connBtn,sendBtn;
	SocketChannel socketChannel;

	// 클라이언트 종료
	private void stopClient() {
		try {
			printMsg("연결 종료");
	        if (socketChannel.isOpen()) {
	        	socketChannel.close();
	        }
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
	}
	
	// 클라이언트 시작 ( Thread로 동작 )
	private void startClient() {
		new Thread(()->{
			try {
				socketChannel = SocketChannel.open();
				socketChannel.configureBlocking(true);
				socketChannel.connect(new InetSocketAddress("localhost", 5555));
				printMsg("서버접속 성공");				
			} catch (Exception e2) {
				printMsg("서버접속 실패");
				if(socketChannel.isOpen()) {
					stopClient();
				}
				return;
			}
			receive();  // 데이터 전송 받는 Thread 실행
		}).start();		
	}
	
	private void printMsg(String msg) {
		Platform.runLater(()->{
			textarea.appendText("[" + msg + "] \n");
		});
	}
	
	
	public void receive() {
			while(true) {
				try {
					ByteBuffer buffer = ByteBuffer.allocate(100);
					int bytes = socketChannel.read(buffer);
					if(bytes == -1) {
						throw new Exception();  // 서버와 통신안됨.
					}
					buffer.flip();
					Charset charset = Charset.forName("UTF-8");					
					String msg = charset.decode(buffer).toString();
					printMsg(msg);
				} catch (Exception e) {
					stopClient();
					e.printStackTrace();
					break;
				}
			}
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {

		BorderPane root = new BorderPane();
		root.setPrefSize(700, 500);
		
		textarea = new TextArea();
		textarea.setEditable(false);
		root.setCenter(textarea);
		
		BorderPane bottom = new BorderPane();
		bottom.setPrefHeight(40);
		bottom.setPrefWidth(Double.MAX_VALUE);
		
		connBtn = new Button("서버접속");
		connBtn.setPrefSize(100, 60);
		connBtn.setOnAction(e -> {
			startClient();
		});
		input = new TextField();
		input.setPrefSize(40, 60);
		
		sendBtn = new Button("메시지 전송");		
		sendBtn.setPrefSize(100, 60);
		sendBtn.setOnAction(e-> {
			Charset charset = Charset.forName("UTF-8");
			ByteBuffer buffer = charset.encode(input.getText());
			try {
				socketChannel.write(buffer);
			} catch (Exception e3) {
				stopClient();
				e3.printStackTrace();
			}
		});
		
		bottom.setLeft(connBtn);
		bottom.setCenter(input);
		bottom.setRight(sendBtn);
		
		root.setBottom(bottom);
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("클라이언트");
		primaryStage.show();

		
	}
	public static void main(String[] args) {

		launch();
	}

}
