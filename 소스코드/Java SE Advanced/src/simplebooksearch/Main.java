package simplebooksearch;

import java.util.List;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import simplebooksearch.service.BookService;
import simplebooksearch.vo.BookEntity;

/*
 * 프로그램의 Entry point이고 우리는 View를 이 class를 사용하여 처리할 것
 * JavaFx 이용을 위하여 e(fx)clipse 를 플로그인 -> package import를 쉽게 처리하기 위하여
 * Application은 abstract class이다. 따라서 abstract method를 재정의 해야한다.
 * 
 */

public class Main extends Application{
	
	TextArea textarea;
	Button searchBtn, deleteBtn;
	TextField input;
	BookService service = new BookService();	// 로직 처리하는 객체
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// launch()하면 이 method가 실행되서 화면에 먼가를 출력해야 한다.
		
		BorderPane root = new BorderPane(); // 전체 화면 layout
		root.setPrefSize(700, 500); 		// 창의 크기
		
		textarea = new TextArea(); 		// 결과가 출력되는 텍스트 영역
		textarea.setEditable(false); 	// 결과를 확인하는 용도로 사용이되기 때문에 수정 불가
		
		BorderPane bottom = new BorderPane();	// 화면 하단을 위한 layout
		bottom.setPrefSize(700, 40);
		
		searchBtn = new Button("keyword로 검색"); 	// 검색버튼
		searchBtn.setPrefSize(120, 40);
		searchBtn.setOnAction(e -> {			//Lambda expression을 이용하여 event처리를 간단하게 구현
			// textarea.appendText 하면 동기처리(blocking)가 되버려.. 
						
			List<BookEntity> result = service.selectBookByKeyword(input.getText());
			
			// 비동기 통신을 위해서 thread 처리, javaFx에서의 UI 스레드는 Platform(인자로 들어오는 Runnable 객체를 다른 Thread를 이용해서 실행해줘~)
			//로직처리
			Platform.runLater(() -> {
				textarea.clear();
				result.stream().forEach(t -> {
					textarea.appendText(t.getBisbn() + "\t" + t.getBtitle() + "\n");
				});			
			});
			
			Platform.runLater(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					
				}
			});
		});
		
		input = new TextField();				//keyword 입력상자
		input.setPrefSize(60, 40);
		
		deleteBtn = new Button("ISBN으로 삭제");
		deleteBtn.setPrefSize(120, 40);
		deleteBtn.setOnAction(e -> {
			boolean result = service.deleteBookByIsbn(input.getText());
			Platform.runLater(() -> {
				textarea.clear();
				if(result) {
					textarea.appendText("[ 삭 제 성 공 ]");
				}
				else {
					textarea.appendText("[ 삭 제 실 패]");
				}
			});
		});
		
		bottom.setLeft(searchBtn);
		bottom.setCenter(input);
		bottom.setRight(deleteBtn);
		
		root.setCenter(textarea);
		root.setBottom(bottom);
		
		Scene scene = new Scene(root);		//scene 생성
		
		primaryStage.setTitle("도서검색 및 삭제 예제");
		primaryStage.setScene(scene);   // 실제 창(window)에 scene을 설정
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		//프로그램이 시작되면 launch()라는 static method를 호출해서 Application 실행.
		launch();
	}
	
}
