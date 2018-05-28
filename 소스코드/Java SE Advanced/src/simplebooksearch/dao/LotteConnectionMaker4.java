package simplebooksearch.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class LotteConnectionMaker4 implements ConnectionMaker4 {

	@Override
	public Connection getConnection() throws Exception {
		// 1.Mysql JDBC Driver Loading
		Class.forName("com.mysql.jdbc.Driver");
						
		// 2. Database연결
		String url = "jdbc:mysql://10.131.124.1:3306/library";
		Connection con = DriverManager.getConnection(url, "java", "java");
				
		return con;
	}

}
