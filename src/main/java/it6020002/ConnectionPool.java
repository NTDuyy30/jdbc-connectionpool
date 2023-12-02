package it6020002;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionPool {
	public Connection getConnection(String objectName) throws SQLException;
	
	public void releaseConnection(Connection c, String objectName) throws SQLException;
}
