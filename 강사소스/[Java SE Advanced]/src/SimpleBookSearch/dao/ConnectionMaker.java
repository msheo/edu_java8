package SimpleBookSearch.dao;

import java.sql.Connection;

public interface ConnectionMaker {

	public Connection getConnection() throws Exception;
	
}
