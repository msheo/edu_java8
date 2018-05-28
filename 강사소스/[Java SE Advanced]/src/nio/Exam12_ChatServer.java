package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Exam12_ChatServer extends Application {

	TextArea textarea;  // 상태창
	Button startBtn, stopBtn;    // 서버시작버튼 -> startServer() 호출
	                             // 서버종료버튼 -> stopServer()호출
	ServerSocketChannel serverSocketChannel;   
	ExecutorService executorService = Executors.newFixedThreadPool(4);  // Thread Pool
	List<Client> clients = new ArrayList<Client>();  // 클라이언트 List

	public void startServer() {
		
		try {
			// serverSocketChannel 포트 바인딩
			serverSocketChannel = ServerSocketChannel.open();
			serverSocketChannel.configureBlocking(true);
			serverSocketChannel.bind(new InetSocketAddress(5555));			
		} catch (Exception e2) {
			e2.printStackTrace();
		}

		Runnable server = () -> {
			// 서버기동
			printmsg("서버기동");

			while(true) {
				try {
					printmsg("클라이언트 접속 대기중");
					// 클라이언트 접속 대기
					SocketChannel socketChannel = serverSocketChannel.accept();
					// 클라이언트 접속 후 List에 클라이언트 추가하면서 클라이언트 Thread 시작
					clients.add(new Client(socketChannel));
					printmsg("클라이언트 접속 - 현재 클라이언트 " + clients.size() + "명");					
				} catch (Exception e) {
					if(serverSocketChannel.isOpen()) {
						stopServer();
					}
					break;   // 서버 exception발생 루프 빠져 나가면서 서버 종료
				}
			}			
		};	
		executorService.submit(server);
	}
	
	public void stopServer() {
		try {
			// List안의 모든 클라이언트 Channel 종료 후 List에서 클라이언트 삭제
			Iterator<Client> iterator = clients.iterator();
			
			while(iterator.hasNext()) {
				Client client = iterator.next();
				client.socketChannel.close();
				iterator.remove();
			}
			// serverSocketChannel close()
			if(serverSocketChannel.isOpen()) {
				serverSocketChannel.close();
			}
			// Thread Pool 종료
			if(!executorService.isShutdown()) {
				executorService.shutdown();
			}
			printmsg("서버 종료");
		} catch (Exception e) {
			e.printStackTrace();	
		}		
	}
	
	
	private void printmsg(String msg) {
		Platform.runLater(() -> {
			textarea.appendText("[" + msg + "] \n");	
		});
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
		
		startBtn = new Button("서버기동");
		startBtn.setPrefSize(100, 60);
		startBtn.setOnAction(e -> {
			startServer();
		});

		stopBtn = new Button("서버종료");
		stopBtn.setPrefSize(100, 60);
		stopBtn.setOnAction(e -> {
			stopServer();
		});
		
		bottom.setRight(stopBtn);
		bottom.setLeft(startBtn);
		
		root.setBottom(bottom);
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("채팅서버");
		primaryStage.setOnCloseRequest(e->{
			stopServer();
		});
		primaryStage.show();
		
		
	}
	public static void main(String[] args) {
		launch();
	}

	class Client {
		private SocketChannel socketChannel;
		
		public Client(SocketChannel socketChannel) {
			this.socketChannel = socketChannel;
			// 클라이언트 객체 생성하면서 바로 receive Thread 기동
			receive();
		}
		
		public void receive() {
			Runnable clientReceive = () -> {
				
				while(true) {
					try {
						ByteBuffer buffer = ByteBuffer.allocate(100);
						int bytes = socketChannel.read(buffer);
						if( bytes == -1 ) 
							throw new Exception();  // 클라이언트 종료
						buffer.flip();
						Charset charset = Charset.forName("UTF-8");
						String msg = charset.decode(buffer).toString();
						clients.stream().forEach(e -> e.send(msg));
					} catch (Exception e) {
						clients.remove(Client.this);
						printmsg("클라이언트 통신종료");
						try {
							socketChannel.close();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						break;   // Exception 발생 후 클라이언트 삭제 후 루프 탈출 후 종료
					}					
				}
			};
			executorService.submit(clientReceive);			
		}
		
		public void send(String msg) {
			Runnable clientSend = () -> {
				Charset charset = Charset.forName("UTF-8");
				ByteBuffer buffer = charset.encode(msg);
				
				try {
					socketChannel.write(buffer);
				} catch (IOException e) {
					printmsg("클라이언트 통신안됨");
					// 클라이언트 통신안되기 때문에 삭제
					clients.remove(Client.this);
					try {
						socketChannel.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					e.printStackTrace();
				}
			};
			
			executorService.submit(clientSend);
		}
	}
}
