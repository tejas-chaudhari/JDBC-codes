package com.dev.jdbc;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Retrival {

	public static void main(String[] args) {

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			//loading Driver
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver loaded...");

			//getting connection
			String url="jdbc:mysql://localhost:3306/caps_buggers";
			String path="C:/Users/Tejas Chaudhary/Desktop/capg/db.properties";
			FileReader read = new FileReader(path);
			Properties prop = new Properties();
			prop.load(read);
			conn = DriverManager.getConnection(url,prop);
			System.out.println("Connection estd...");

			//query
			
			String query="select * from users_info";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			System.out.println("Query issued and executed...");
			System.out.println("--------------------------------------------------------");

			//process the result
			while(rs.next())
			{
				System.out.println("user id is: "+rs.getInt(1));
				System.out.println("username is: "+rs.getString("username"));
				System.out.println("email id is: "+rs.getString("email"));
//				System.out.println("password is: "+rs.getString("password"));
				System.out.println("-------------------------------------------------------");	
			}


		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//close JDBC objects
		finally {
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if(stmt!=null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}
}
