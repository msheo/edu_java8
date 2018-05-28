package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Exam14_ChatServerNonBlocking extends Application {

	TextArea textarea;
	Button btn;
	
	Selector selector;
	ServerSocketChannel serverSocketChannel;
	List<Client> clients = new ArrayList<Client>();
	
	private void startServer() {
		try {
			selector = Selector.open();
			serverSocketChannel = ServerSocketChannel.open();
			serverSocketChannel.configureBlocking(false);
			serverSocketChannel.bind(new InetSocketAddress(5555));
			serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		} catch (Exception e) {
			if(serverSocketChannel.isOpen()) {
				stopServer();
			}
		}
		
		Thread thread = new Thread() {
			
			@Override
			public void run() {
				while(true) {
					try {
						int keyCount = selector.select();
						if( keyCount == 0 ) continue;
						
						Set<SelectionKey> selectedKey = selector.selectedKeys();
						Iterator<SelectionKey> iterator = selectedKey.iterator();
						
						while(iterator.hasNext()) {
							SelectionKey selectionKey = iterator.next();
							if(selectionKey.isAcceptable()) {
								accept(selectionKey);
							} else if(selectionKey.isReadable()) {
								Client client = (Client)selectionKey.attachment();
								client.receive();
							} else if(selectionKey.isWritable()) {
								Client client = (Client)selectionKey.attachment();
								client.send(selectionKey);
							}
							iterator.remove();
						}
					} catch (IOException e) {
						if(serverSocketChannel.isOpen()) {
							stopServer();
						}
						break;
					}
				}
			}
		};
		thread.start();
		Platform.runLater(() -> {
			textarea.appendText("[서버 기동]\n");
		});
	}
	
	private void stopServer() {
		
		try {
			Iterator<Client> iterator = clients.iterator();
			
			while(iterator.hasNext()) {
				Client client = iterator.next();
				client.socketChannel.close();
				iterator.remove();
			}			
			
			if(serverSocketChannel.isOpen()) {
				serverSocketChannel.close();
			}
			
			if(selector.isOpen()) {
				selector.close();
			}

			Platform.runLater(() -> {
				textarea.appendText("[서버 중지]\n");
			});
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void accept(SelectionKey selectionKey) {
		try {
			ServerSocketChannel s = (ServerSocketChannel)selectionKey.channel();
			SocketChannel channel = s.accept();
			
			Platform.runLater(() -> {
				textarea.appendText("[클라이언트 접속]\n");
			});
				
			Client client = new Client(channel);
			clients.add(client);

			Platform.runLater(() -> {
				textarea.appendText("[접속 클라이언트 개수] " + clients.size() + "\n");
			});			
		} catch (Exception e) {
			if(serverSocketChannel.isOpen()) {
				stopServer();
			}
		}

		
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		BorderPane root = new BorderPane(); 
		root.setPrefSize(500,300);  // 가로길이, 세로길이 세팅
		
		textarea = new TextArea();
		textarea.setEditable(false);
		root.setCenter(textarea);
		
		btn = new Button("start");
		btn.setPrefHeight(30);
		btn.setMaxWidth(Double.MAX_VALUE);
		
		btn.setOnAction(e -> {
			if(btn.getText().equals("start")) {
				startServer();
			} else if(btn.getText().equals("stop")) {
				stopServer();
			}
		});
		
		root.setBottom(btn);
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Server");
		primaryStage.setOnCloseRequest(event -> {
			stopServer();
		});
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch();
	}
	
	class Client {

		SocketChannel socketChannel;
		String msg;
		
		public Client(SocketChannel channel) throws Exception {
			this.socketChannel = channel;

			socketChannel.configureBlocking(false);
			SelectionKey selectionKey = 
					socketChannel.register(selector, SelectionKey.OP_READ);
			selectionKey.attach(this);
		}


		
		private void receive() {			
			try {
				ByteBuffer buffer = ByteBuffer.allocate(100);
				
				int bytes = socketChannel.read(buffer);
				
				// 클라이언트 정상 종료
				if( bytes == -1 ) {
					throw new Exception();
				}
				
				Platform.runLater(() -> {
					textarea.appendText("클라이언트 요청 받음" + "\n");
				});
				
				buffer.flip();
				Charset charset = Charset.forName("UTF-8");
				String msg = charset.decode(buffer).toString();
				
				for(Client c : clients) {
					c.msg = msg;
					SelectionKey key = c.socketChannel.keyFor(selector);
					key.interestOps(SelectionKey.OP_WRITE);
				}
				selector.wakeup();
				
			} catch (Exception e) {
				clients.remove(Client.this);
				Platform.runLater(() -> {
					textarea.appendText("클라이언트 통신 종료!" + "\n");									
				});								
				try {
					socketChannel.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}				
			}
			
		}
		
		private void send(SelectionKey selectionKey) {
			try {
	            Charset charset = Charset.forName("UTF-8");
	            ByteBuffer byteBuffer = charset.encode(msg);
	 
	            socketChannel.write(byteBuffer);
	            selectionKey.interestOps(SelectionKey.OP_READ);
	            selector.wakeup();
	        } catch (Exception e) {
				clients.remove(Client.this);
				Platform.runLater(() -> {
					textarea.appendText("클라이언트 통신 종료!" + "\n");									
				});								
				try {
					socketChannel.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}				
	        }
		}
	}

}
