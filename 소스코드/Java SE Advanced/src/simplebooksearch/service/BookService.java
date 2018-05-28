package simplebooksearch.service;

import java.util.List;

import simplebooksearch.dao.BookDao;
import simplebooksearch.dao.LotteConnectionMaker4;
import simplebooksearch.vo.BookEntity;

// 로직을 처리하는 클래스(로직만 처리 -> transaction)
public class BookService {

	public List<BookEntity> selectBookByKeyword(String text) {
		
		// 키워드로 책을 검색하는 로직을 수행하면 되요!! ( 로직 : 더하고 빼고 곱하고 나누고 if while ...)
		// 우리는 따로 로직 필요 없이 DB 처리하면 될듯
		
		// BookDao에 의존성이 있는 LotteConnectionMaker4 객체를 주입해요(Dependency Injection)
		// DI 작업이 일어나요 -> Constructor Injection
		// BookDao 코드에 만약에 new LotteConnectionMaker4() 가 있으면 커플링이 높아 -> 소스코드에서 필요할때 new 해서 생성하잖아..
		// 근데 여기서 봐봐 지금 서비스에서 실행 시점에 LotteConnectionMaker4 만들고 bookdao에 생성자 인자로 넘겨서 의존성을 실행 시점에 주입시켜주잖아 이게 Dependency Injection이야
		// 그리고 정리해서 이 개념을 IoC(Inversion of Control) 이라고 하는데
		// 일반적인 Control이 클래스 설계하다가 다른 클래스 기능 필요하면 new 해서 생성하는데 이 new 생성을 설계에서 인터페이스를 활용하여가지고 실행시기에서 생성해서 두 클래스의 인스턴스를 연관짓게 하는 것을 제어를 역전했다해서 제어의 역전
		LotteConnectionMaker4 maker = new LotteConnectionMaker4();
		BookDao dao = new BookDao(maker);
		// 근데 한가지 이슈가 생기긴 한다. 저기 의존성 주입을 위해서 서비스에서 db커넥션을 위한 lotteconnectionmaker4를 생성하는데 db커넥션 생성하는 것은 dao에서 해야지 왜 서비스에서 구현해야하나?? 하는 이슈 그걸로 싸운다 사람들끼리
		// 뭐 정답은 없지 객체지향의 SRP(Single Responsibility Principle)에 따라 서비스에는 비즈니스 로직만, db커넥션부터 해서 db관련된것은 dao에만 해야지라고 주장하긴 하는데 의존성 주입을 위해서는 어쩔 수 없지 하는 사람들도 있다
		// Spring 전에 EJB로 자바어플 개발했었는데 EJB가 잘 만들어졌지만 현재 SPRING이 더 많이 쓰는 이유는 EJB 잘만들어놓고 클래서간 커플링이 너무 심해서 복잡하기도 하고 클래스 커플링이 높다는 것은 즉 재사용이 어려워진다는 단점이 생겼다.
		// 따라서 이때 EJB를 뒤틀 IoC 개념을 활용한 DI를 통해 Spring framework이 나오게 되었고 인기를 끌고 있다.
		
		
		return dao.select(text);
	}

	public boolean deleteBookByIsbn(String text) {

		LotteConnectionMaker4 maker = new LotteConnectionMaker4();
		BookDao dao = new BookDao(maker);
		
		return dao.delete(text);
	}
}	
