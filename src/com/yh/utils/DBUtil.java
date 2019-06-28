package com.yh.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.yh.utils.AppInfo;

/**
 * @Title: DBUtil.java
 * @Description:
 * @Copyright: Copyright (c) 2018
 * @Company: FISH
 * @author yh
 * @date 2019年6月25日下午10:23:15
 * @version 1.0
 */
public class DBUtil {

    private static DBUtil db;

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public static DBUtil getDBUtil() {
        if (db == null) {
            db = new DBUtil();
        }
        return db;
    }

    private DBUtil() {

    }

    public void close() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet executeQuery(String sql) {
        if (getConn() == null) {
            return null;
        }
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public ResultSet executeQuery(String sql, Object[] obj) {
        if (getConn() == null) {
            return null;
        }
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < obj.length; i++) {
                ps.setObject(i + 1, obj[i]); 
            }
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rs;
    }

    public int executeUpdate(String sql) {
        int result = -1;
        if (getConn() == null) {
            return result;
        }
        try {
            ps = conn.prepareStatement(sql);
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int executeUpdate(String sql, Object[] obj) {
        int result = -1;
        if (getConn() == null) {
            return result;
        }
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < obj.length; i++) {
                ps.setObject(i + 1, obj[i]);
            }
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private Connection getConn() {
        try {
            if (conn == null || conn.isClosed()) {
                Class.forName(AppInfo.JDBC_DRIVER);
                conn = DriverManager.getConnection(AppInfo.JDBC_URL,
                        AppInfo.JDBC_USERNAME, AppInfo.JDBC_PASSWORD);
            }
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver is not found.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }


}
