package com.yh.utils;

import java.sql.*;

/**
 * @Title: JDBCUtil.java
 * @Description:
 * @Copyright: Copyright (c) 2018
 * @Company: FISH
 * @author yh
 * @date 2019年6月25日下午11:53:57
 * @version 1.0
 */
public class JDBCUtil {
	public JDBCUtil() {

	}

	PreparedStatement ps = null;
	ResultSet rs = null;
	static {
		String driverName = "oracle.jdbc.OracleDriver";
		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		Connection connection = null;
		String url = "jdbc:Oracle:thin:@localhost:1521:orcl";
		String user = "SCOTT";
		String password = "tiger";
		// String password="Foolish004";
		try {
			connection = DriverManager.getConnection(url, user, password);
			connection.setAutoCommit(false);
			System.out.println(connection);
		} catch (SQLException e) {
			System.out.println("in catch");
			e.printStackTrace();
		}
		return connection;
	}

	public static void main(String[] args) {
		new JDBCUtil().getConnection();
	}

}