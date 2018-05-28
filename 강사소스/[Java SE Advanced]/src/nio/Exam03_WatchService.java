package nio;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Exam03_WatchService extends Application {

	private TextArea textarea;
	WatchService watchService;
	
	private void printMsg(String msg) {
		// textarea에 출력하기 위한 method
		Platform.runLater(()->{
			textarea.appendText("[" + msg + "] \n");
		});
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		// 화면 layout 구성
		BorderPane root = new BorderPane();
		root.setPrefSize(700, 500);   // 창의 크기 설정
		
		textarea = new TextArea();
		textarea.setEditable(false);
		
		root.setCenter(textarea);
				
		Scene scene = new Scene(root);  // 화면을 생성
		
		primaryStage.setTitle("파일 변경 감시 시스템");
		primaryStage.setScene(scene);   // 윈도우에 화면을 붙여요.
		primaryStage.setOnCloseRequest(e -> {
			System.exit(1);    // 강제종료
		});
		primaryStage.show();
		
		new Thread(()->{
			try {
				watchService = FileSystems.getDefault().newWatchService();  // watchServer객체 생성.
				Path path = Paths.get("asset");
				path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE,
						StandardWatchEventKinds.ENTRY_DELETE,
						StandardWatchEventKinds.ENTRY_MODIFY);
				
				while(true) {
					WatchKey key = watchService.take();  // 여기서 일단 대기
					List<WatchEvent<?>> list = key.pollEvents(); // 이벤트를 받아서 리스트로 해당이벤트
					                                             // 객체를 저장해요.
					list.stream().forEach(t->{
						Kind<?> kind = t.kind(); // event의 종류를 알아내요(수정,삭제,생성..)
						Path filePath = (Path)t.context();  // 이벤트가 발생한 파일의 이름을 알아내기 
						                                    // 위해 path객체를 얻어와요!
						if( kind == StandardWatchEventKinds.ENTRY_CREATE) {
							// 파일이 생성된 사건이 발생했어요.
							printMsg("파일이 생성됬어요 : " + filePath.getFileName());
						} else if( kind == StandardWatchEventKinds.ENTRY_DELETE) {
							// 파일이 삭제된 사건이 발생했어요.
							printMsg("파일이 삭제됬어요 : " + filePath.getFileName());
						} else if( kind == StandardWatchEventKinds.ENTRY_MODIFY) {
							// 파일명이 변경된 사건이 발생했어요.
							printMsg("파일이 변경됬어요 : " + filePath.getFileName());
						} 						
					});
					boolean valid = key.reset(); // 만약 다시 감시를 시작할때 해당 폴더가
					                             // 없으면 false가 리턴
					if(!valid) {
						break;
					}
				}
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
		
	}
	
	public static void main(String[] args) {
		launch();
	}

}
