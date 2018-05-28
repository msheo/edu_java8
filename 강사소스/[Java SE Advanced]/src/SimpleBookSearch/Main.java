package SimpleBookSearch;

import java.util.List;

import SimpleBookSearch.service.BookService;
import SimpleBookSearch.vo.BookEntity;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/*
 * 프로그램의 Entry point이고 우리는 View를 이 class를 사용해서 처리할꺼예요.
 * JavaFx를 이용할꺼구요.. package import를 쉽게 처리하기 위해 e(fx)clipse plugin을 설치했어요.
 * Application은 abstract class예요. 그 이유는 Application안에 abstract method가 존재.
 * 상속받은 class에서 당연히 해당 abstract method를 재정의해야해요(overriding)
 * 
 */
public class Main extends Application {

	TextArea textarea;
	Button searchBtn, deleteBtn;
	TextField input;
	BookService service = new BookService();   // 로직처리하는 객체
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// launch()하면 이 method가 실행되서 화면에 먼가를 출력해야 해요.
		
		BorderPane root = new BorderPane();  // 전체 화면에 대한 layout
		root.setPrefSize(700, 500); // 창의 크기 설정.
		
		
		textarea = new TextArea();    // 결과가 출력되는 text영역 생성
		textarea.setEditable(false);  // 결과를 확인하는 용도로 사용이 되니 수정불가형태로 사용.
		
		BorderPane bottom = new BorderPane();   // 화면 하단을 위한 layout을 생성
		bottom.setPrefSize(700, 40);
		searchBtn = new Button("keyword로 검색"); // 검색버튼을 만들어요.
		searchBtn.setPrefSize(120, 40);           // 검색버튼의 크기
		// Lambda express을 이용해서 event처리를 간단하게 구현.
		searchBtn.setOnAction(e -> {
			List<BookEntity> result = service.selectBookByKeyword(input.getText());
			// 출력해야 해요.
			Platform.runLater(() -> {
				textarea.clear();
				result.stream().forEach(t ->{
					textarea.appendText(t.getBtitle() + " : " + t.getBisbn() +"\n");
				});
			});
		});
		
		input = new TextField();                // keyword입력상자
		input.setPrefSize(60, 40);
		deleteBtn = new Button("ISBN으로 삭제");  // 삭제버튼 생성
		deleteBtn.setPrefSize(120, 40);
		deleteBtn.setOnAction(e -> {
			boolean result = service.deleteBookByIsbn(input.getText());
			Platform.runLater(() -> {
				textarea.clear();
				if(result) {
					textarea.appendText("[삭제성공!!]");
				} else {
					textarea.appendText("[삭제실패.. 먼가 이상해요!!!]");
				}
			});
		});
				
		bottom.setLeft(searchBtn);    // 조립시작
		bottom.setCenter(input);
		bottom.setRight(deleteBtn);
		
		root.setCenter(textarea);
		root.setBottom(bottom);       // 조립 끝.
		
		Scene scene = new Scene(root);  // Scene을 생성
		
		// 실제 창(window)에 Scene을 설정.
		primaryStage.setScene(scene);
		primaryStage.setTitle("도서검색 및 삭제 예제");
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		
		// 프로그램이 시작되면 launch()라는 static method를 호출해서 Application 실행.
		launch();

	}

}
