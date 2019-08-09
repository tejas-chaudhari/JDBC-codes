package com.dev.jdbc;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class MyFirstJDBC {

	public static void main(String[] args) {

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			//step:1 load the driver
			Driver div = new com.mysql.jdbc.Driver();
			DriverManager.registerDriver(div);
			System.out.println("Driver Loaded...");

			//step:2 get the connection via driver
			String url="jdbc:mysql://localhost:3306/caps_buggers?user=root&password=root";
			conn = DriverManager.getConnection(url);
			System.out.println("Connection Estd...");

			//step:3 issue SQL query via connection
			String query="select * from users_info";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			System.out.println("Query issued and executed...");
			System.out.println("--------------------------------------------------------");

			//step: 4 process the result
			while(rs.next())
			{
				System.out.println("user id is: "+rs.getInt(1));
				System.out.println("username is: "+rs.getString("username"));
				System.out.println("email id is: "+rs.getString("email"));
//				System.out.println("password is: "+rs.getString("password"));
				System.out.println("-------------------------------------------------------");	
			}

			


		} catch (SQLException e) {
			e.printStackTrace();
		}


		//step 5:close all JDBC objects
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
