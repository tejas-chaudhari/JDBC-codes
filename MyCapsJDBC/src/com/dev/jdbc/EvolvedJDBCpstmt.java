package com.dev.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class EvolvedJDBCpstmt {

	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String url = "jdbc:mysql://localhost:3306/caps_buggers?user=root&password=root";
		String query = "insert into users_info values(?,?,?,?)";

		try (Connection conn = DriverManager.getConnection(url);
				PreparedStatement pstmt = conn.prepareStatement(query);) {
			System.out.println("enter user id:");
			int user_id = Integer.parseInt(sc.nextLine());
			System.out.println("enter user name");
			String user_name = sc.nextLine();
			System.out.println("enter email");
			String email = sc.nextLine();
			System.out.println("enter password");
			String pwd = sc.nextLine();
			pstmt.setInt(1, user_id);
			pstmt.setString(2, user_name);
			pstmt.setString(3, email);
			pstmt.setString(4, pwd);

			int count = pstmt.executeUpdate();

			if (count > 0) {
				System.out.println("data inserted");
			} else {
				System.out.println("error");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
