package org.mmxbb.exam.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConn {

	/**
	 * <li>get the connection from lookup DataSource
	 * 
	 * @return Connection
	 */
	// public Connection getConnection() {
	// try {
	// Context initCtx = new InitialContext();
	// Context envCtx = (Context)initCtx.lookup("java:comp/env");
	// ds = (DataSource)envCtx.lookup("jdbc/exam");
	// conn=ds.getConnection();
	//      	
	//    
	// } catch (Exception e) {
	// System.err.println("getConnection error: " + e);
	// return null;
	// }
	// return conn;
	// }

	public static final String url = "jdbc:mysql://127.0.0.1/exam";
	public static final String name = "com.mysql.jdbc.Driver";
	public static final String user = "root";
	public static final String password = "123456";

	public Connection conn = null;

	// public DBConn(String sql) {
	// try {
	// Class.forName(name);//指定连接类型
	// conn = DriverManager.getConnection(url, user, password);//获取连接
	// pst = conn.prepareStatement(sql);//准备执行语句
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }

	/**
	 * 
	 * <li>get the connection from lookup DataSource
	 * 
	 * @return Connection
	 */
	public Connection getConnection() {
		try {
			Class.forName(name);// 指定连接类型
			conn = DriverManager.getConnection(url, user, password);// 获取连接

		} catch (Exception e) {
			System.err.println("getConnection error: " + e);
			return null;
		}
		return conn;
	}

	/**
	 * return connection to pool
	 */
	public void close() {
		try {
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
