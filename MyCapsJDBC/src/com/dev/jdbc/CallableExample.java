package com.dev.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class CallableExample {

	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {


		Connection conn = null;
		CallableStatement cstmt = null;
		ResultSet rs = null;

		try {
			//load the driver
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver Loaded...");

			//get connection
			String url="jdbc:mysql://localhost:3306/caps_buggers";
			System.out.println("enter user");
			String user = sc.nextLine();
			System.out.println("enter password");
			String password = sc.nextLine();
			conn =  DriverManager.getConnection(url, user, password);
			System.out.println("Connection Estd...");

			//query
			System.out.println("enter query for procedure call");
//			String query="call getAllDetails";
			String query=sc.nextLine();
			cstmt = conn.prepareCall(query);
			boolean b = cstmt.execute();

			if(b) {
				rs = cstmt.getResultSet();

				while(rs.next())
				{
					System.out.println("user id "+rs.getInt(1));
					System.out.println("user name "+rs.getString(2));
					System.out.println("user email "+rs.getString(3));
					System.out.println("---------------------------------------------");
				}
			}else {
				int count = cstmt.getUpdateCount();
				System.out.println("Query OK " +count+" rows affected");
			}
		}

		catch (ClassNotFoundException|SQLException e) {
			e.printStackTrace();
		}


	}
}
