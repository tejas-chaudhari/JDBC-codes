package com.dev.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class JDBCBatch {

	public static void main(String[] args) {

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try(Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/caps_buggers?user=root&password=root");
				PreparedStatement pstmt = conn.prepareStatement("insert into users_info values(?,?,?,?)");
				Scanner sc = new Scanner(System.in);
				) {
			while(true) {
			System.out.println("enter user id:");
			pstmt.setInt(1,Integer.parseInt(sc.nextLine()));
			System.out.println("enter user name:");
			pstmt.setString(2, sc.nextLine());
			System.out.println("enter email:");
			pstmt.setString(3, sc.nextLine());
			System.out.println("enter password:");
			pstmt.setString(4, sc.nextLine());
			System.out.println("enter 1 to add new record 0 to exit");
			int i =Integer.parseInt(sc.nextLine());
			pstmt.addBatch();
			
			if(i==0) {
				pstmt.executeBatch();
			    System.out.println("data added...");
			    System.exit(0);
			}
				
			}

		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
