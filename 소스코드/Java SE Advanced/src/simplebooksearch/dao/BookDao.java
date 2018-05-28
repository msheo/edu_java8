package simplebooksearch.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import simplebooksearch.vo.BookEntity;

public class BookDao {
	
	private ConnectionMaker4 connectionMaker;
	
	public BookDao(ConnectionMaker4 connectionMaker) {
		this.connectionMaker = connectionMaker;
	}

	public List<BookEntity> select(String text) {
		
		List<BookEntity> result = new ArrayList<BookEntity>();
		
		// 순수 JDBC를 이용해서 Mysql에서 데이터 가져옴
		// DB 처리 코드
		try {
			
			// 1~2번 메소드로 추출(extract method)
			Connection con = connectionMaker.getConnection();
			
			// 3. Query 작성 및 Statement 생성
			String sql = "select btitle, bauthor, bisbn from book where btitle like ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + text + "%");	// ? 에 인자를 넣어줌
			
			// 4. 실행
			ResultSet rs = pstmt.executeQuery();
			
			// 5. 결과처리
			while (rs.next()) {
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
			
			String sql = "delete from book where bisbn = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, text);
			
			// executeQuery : ResultSet return, executeUpdate : update count return(int)
			int count = pstmt.executeUpdate();	
			
			if (count == 1) {
				result = true;
			}
			else {
				result = false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
