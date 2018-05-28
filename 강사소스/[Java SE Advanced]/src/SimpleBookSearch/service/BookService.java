package SimpleBookSearch.service;

import java.util.List;

import SimpleBookSearch.dao.BookDAO;
import SimpleBookSearch.dao.LotteConnectionMaker;
import SimpleBookSearch.vo.BookEntity;

// 로직을 처리하는 클래스( 로직만 처리.. + Transaction처리 )
public class BookService {

	public List<BookEntity> selectBookByKeyword(String text) {

		// 키워드로 책을 검색하는 로직을 수행하면 되요!! ( 로직 : 더하고 빼고, 곱하고 나누고, if, while... )
		// Database처리는 logic이 아니예요.
		// 그런게 우리 예제는... 로직처리할게 없어요..바로 DB처리하면 될 듯 보여요.
		// Database처리하기 위해 Database처리 전담 객체를 하나 생성해서 이용.
		// 도서와 관련된 데이터베이스 처리를 전담하는 객체 : BookDAO ( Data Access Object )
		LotteConnectionMaker maker = new LotteConnectionMaker();
		BookDAO dao = new BookDAO(maker);
		return dao.select(text);
		
	}

	public boolean deleteBookByIsbn(String text) {
		// TODO Auto-generated method stub
		LotteConnectionMaker maker = new LotteConnectionMaker();
		BookDAO dao = new BookDAO(maker);
		// BookDAO에 의존성(Dependency)이 있는 LotteConnectionMaker 객체를 주입(Injection)해요.
		// DI작업이 일어나요. ( Constructor Injection )
		// IoC( Inversion of Control ) : 제어의 역전.
		//
		return dao.delete(text);

	}

}
