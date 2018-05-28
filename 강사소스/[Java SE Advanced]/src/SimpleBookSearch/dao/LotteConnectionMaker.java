package SimpleBookSearch.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class LotteConnectionMaker implements ConnectionMaker {

	@Override
	public Connection getConnection() throws Exception {
		// 1. Mysql JDBC Driver Loading
				Class.forName("com.mysql.jdbc.Driver");
				System.out.println("드라이버 로딩 성공!!");
				// 2. Database연결
				String url = "jdbc:mysql://localhost:3306/library?useUnicode=true&characterEncoding=UTF-8";
				return DriverManager.getConnection(url,"java","java");
	}

	
}
