package it6020002;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Stack;

public class ConnectionPoolImpl implements ConnectionPool {
	private String driver;
	private String url;
	private String username;
	private String password;
	private Stack<Connection> pool;
	
	public ConnectionPoolImpl() {
		this.driver = "com.mysql.cj.jdbc.Driver";
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		this.url = "jdbc:mysql://localhost:3306/qlsv";
		this.username = "root";
		this.password = "";
		this.pool = new Stack<>();
	}

	@Override
	public Connection getConnection(String objectName) throws SQLException {
		if (this.pool.empty()) {
			System.out.println(objectName + " have created a new connection");
			return DriverManager.getConnection(url, username, password);
		} else {
			System.out.println(objectName + " have popped the connection");
			return this.pool.pop();
		}
	}

	@Override
	public void releaseConnection(Connection c, String objectName) throws SQLException {
		System.out.println(objectName + " have pushed the connection");
		this.pool.push(c);
	}
	
	protected void finalize() throws Throwable {
		this.pool.clear();
		this.pool = null;
		System.out.println("ConnectionPool is close");
	}
	
}
