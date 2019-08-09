package com.dev.jdbc;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class JDBCDeletion {

	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			//step 1
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("driver loaded..");

			//step 2 get the connection
			String url = "jdbc:mysql://localhost:3306/caps_buggers";
			System.out.println("enter username");
			String user = sc.nextLine();
			System.out.println("enter password");
			String password = sc.nextLine();
			conn = DriverManager.getConnection(url,user,password);
			System.out.println("Connection Estd...");

			//step 3 issue query

			String query="delete from users_info where userid = ?";
			pstmt = conn.prepareStatement(query);

			System.out.println("enter user id ");
			int uid = sc.nextInt();

			pstmt.setInt(1, uid);

			int count = pstmt.executeUpdate();

			//step 4: process the result
			if(count>0) {
				System.out.println("row deleted...");
			}else {
				System.out.println("error!!!");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//step 5:close jdbc objects
		finally {
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}
}
