package SimpleBookSearch.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import SimpleBookSearch.vo.BookEntity;

public class BookDAO {

	private ConnectionMaker connectionMaker;
	
	public BookDAO(ConnectionMaker connectionMaker) {
		this.connectionMaker = connectionMaker;
	}
	

	
	
	public List<BookEntity> select(String text) {
		// 순수 JDBC를 이용해서 Mysql에서 데이터 가져올꺼예요!!
		// DB처리 코드가 나오면 되요!
		List<BookEntity> result = new ArrayList<BookEntity>();
		try {
			// Connection을 얻어와요!
			Connection con = connectionMaker.getConnection();
			// 3. 실행할 Query작성 및 Statement생성
			String sql = "select btitle,bauthor,bisbn from book where btitle like ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + text + "%");  // ?에 인자를 넣어줘요..(패턴매칭)
			// 4. 실행
			ResultSet rs = pstmt.executeQuery();
			// 5. 결과처리.
			while(rs.next()) {
				BookEntity entity = new BookEntity();
				entity.setBisbn(rs.getString("bisbn"));
				entity.setBtitle(rs.getString("btitle"));
				entity.setBauthor(rs.getString("bauthor"));
				result.add(entity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean delete(String text) {
		
		boolean result = false;
		
		try {
			Connection con = connectionMaker.getConnection();
			// 3. 실행할 Query작성 및 Statement생성
			String sql = "delete from book where bisbn = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, text);  // ?에 인자를 넣어줘요..(인자로 전달된 ISBN값.)
			// 4. 실행
			int count = pstmt.executeUpdate();  // executeQuery : 결과레코드 집합.
			                                    // count : 영향을 받은 레코드의 수.
			// 지금은 key값을 이용한 삭제처리이기때문에 count값이 1 이어야 정상.
			// 5. 결과처리.
			if( count == 1 ) {
				result = true;
			} else {
				result = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
