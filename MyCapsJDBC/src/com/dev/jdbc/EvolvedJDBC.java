package com.dev.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EvolvedJDBC {

	public static void main(String[] args) {

//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		String url = "jdbc:mysql://localhost:3306/caps_buggers?user=root&password=root";
		String query = "select * from users_info";

		try (Connection conn = DriverManager.getConnection(url);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query);) {
			while (rs.next()) {
				System.out.println("user id is: " + rs.getInt(1));
				System.out.println("user name is: " + rs.getString(2));
				System.out.println("email is: " + rs.getString(3));
				System.out.println("*****************************");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
