package com.yh.service;

import javax.swing.*;

import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import com.yh.utils.JDBCUtil;

/**
 * @Title: Register.java
 * @Description:
 * @Copyright: Copyright (c) 2018
 * @Company: FISH
 * @author yh
 * @date 2019年6月25日下午11:57:17
 * @version 1.0
 */

public class Register implements ActionListener {
	JFrame jf = new JFrame("学生成绩管理与选课系统");
	JTextField jtfUserName = new JTextField(10);
	JPasswordField jpfPassWord = new JPasswordField(10);
	JComboBox identify = new JComboBox();

	// constructor
	public Register() {
		CreateRegisterGUI();
	}

	// deal with user action, when user check:"登录","取消"or "注册"
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		if (str.equalsIgnoreCase("登录")) {
			// 当用户点击登录时，调用以下方法去数据库做匹配
			processLogin();
		} else if (str.equalsIgnoreCase("取消")) {
			jtfUserName.setText("");
			jpfPassWord.setText("");
		} else if (str.equalsIgnoreCase("注册")) {
//			new CreateLogin();
		}

	}

	// 当用户点击登录时，调用以下方法去数据库做匹配
	public void processLogin() {
		// create connection to the database.
//		Connection con = null;
		Connection con = JDBCUtil.getConnection();
		// 让程序自动连接相应的数据库,以避免连接数据库时频繁改动连接程序
		// if (JdbcUtil.class == null) {
		//// 连接达内Oracle数据库
		// con = JdbcUtil.getConnection();
		// } else {
		//// 连接本地SQLSERVER数据库
		// con = JDBCUtil.getConnection();
		// }
		// write sql sentence
		String usrName = jtfUserName.getText().trim();
		String passwrd = new String(jpfPassWord.getPassword()).trim();
		String ident = identify.getSelectedItem().toString().trim();
		String sql = "select * from registerXu " + "where userName=? and passWord=? and identify=?";
		System.out.println(usrName + ":" + passwrd + ":" + ident);
		// create object of PreparedStatement
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			// Prepare parameter for the sql
			ps.setString(1, usrName);
			ps.setString(2, passwrd);
			ps.setString(3, ident);
			// send parameter to compiler to compile.
			ResultSet rs = ps.executeQuery();
			StringBuffer sb = new StringBuffer("");
			ResultSetMetaData meta = rs.getMetaData();
			int cols = meta.getColumnCount();
			// you can use another simple way to check whether the people has
			// records in database:
			// define a boolean flag=false, if has record change it
			// to true;otherwise, if flag=flase,showMessage("Input ERROR")
			while (rs.next()) {
				for (int i = 1; i < cols; i++) {
					sb.append(meta.getColumnName(i));
					sb.append(rs.getString(i));
				}
			}
			if (sb.length() > 2) {
				JOptionPane.showMessageDialog(null, "用户名或密码错误");
			} else if (sb.length() < 2) {
				if (ident.equals("student")) {
					// if he or she is a student, and usrName-passwrd alright,
					// then go to 学生选课系统
					new StudentChooseCourse(usrName, passwrd);
					jf.setVisible(false);
					// new StudentEntered();
				} else if (ident.equals("teacher")) {
					// new TeacherEntered(usrName,passwrd);
//					new TeacherEntered();
					jf.setVisible(false);
				} else if (ident.equals("admin")) {
					// go to administrator pages.
					new AdminCourse();
					jf.setVisible(false);
				}

			}
		} catch (Exception er) {
			er.printStackTrace();
		}

	}

	// 产生图形用户界面
	public void CreateRegisterGUI() {
		jf.setLayout(new GridLayout(5, 1));
		JPanel jp1 = new JPanel();
		JLabel jl1 = new JLabel("选课管理系统");
		jp1.add(jl1);
		jf.add(jp1);
		JPanel jp2 = new JPanel();
		JLabel jl2 = new JLabel("用户名:");
		jp2.add(jl2);
		jp2.add(jtfUserName);
		jf.add(jp2);
		JPanel jp3 = new JPanel();
		// JPasswordField jpfPassWord = new JPasswordField(10);
		JLabel passWord = new JLabel("密 码:");
		jp3.add(passWord);
		jp3.add(jpfPassWord);
		jf.add(jp3);
		JPanel jp4 = new JPanel();
		JLabel jl4 = new JLabel("身 份:");
		// identify.addItem(new String("学生 "));
		identify.addItem(new String("student "));
		// identify.addItem(new String("老师 "));
//		identify.addItem(new String("teacher "));
		// identify.addItem(new String("管理员 "));
		identify.addItem(new String("admin "));
		identify.addActionListener(this);
		jp4.add(jl4);
		jp4.add(identify);
		jf.add(jp4);
		JPanel jp5 = new JPanel();
		JButton enter = new JButton("登录");
		enter.addActionListener(this);
		JButton cancel = new JButton("取消");
		cancel.addActionListener(this);
//		JButton regist = new JButton("注册");
//		regist.addActionListener(this);
		jp5.add(enter);
		jp5.add(cancel);
//		jp5.add(regist);
		jf.add(jp5);
		jf.setSize(400, 250);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public static void main(String[] args) {
		new Register();
	}

}
