package com.dev.jdbc;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class JDBCInsertion {

	public static void main(String[] args) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
	
		
		
		try {
			
			//step 1: Loading and registring driver
			Driver div = new com.mysql.jdbc.Driver();
			DriverManager.registerDriver(div);
			System.out.println("Driver Loaded...");
			
			//step 2: get connection via driver
			String url = "jdbc:mysql://localhost:3306/caps_buggers?user=root&password=root";
			conn = DriverManager.getConnection(url);
			System.out.println("Connection Estd...");
			
			//step 3: issue SQL query via connection
			String query = "insert into users_info values(?,?,?,?)";
			pstmt = conn.prepareStatement(query);
			
			Scanner sc = new Scanner(System.in);
			
			System.out.println("Enter userid...");
			int userid = Integer.parseInt(sc.nextLine());
			
			System.out.println("Enter user name...");
			String username = sc.nextLine();
			
			System.out.println("Enter email id...");
			String email = sc.nextLine(); 
			
			System.out.println("enter password...");
			String password = sc.nextLine();
			
			pstmt.setInt(1, userid);
			pstmt.setString(2, username);
			pstmt.setString(3, email);
			pstmt.setString(4, password);
			
			int count = pstmt.executeUpdate();
			
			//step 4: process the result
			if(count>0) {
				System.out.println("data inserted...");
			}else {
				System.out.println("error!!!");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
		//step 5:Close all JDBC objects
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
