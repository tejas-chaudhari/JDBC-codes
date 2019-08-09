package com.dev.jdbc;

import java.sql.Connection;
//import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Trial {

	static Connection conn = null;
	static PreparedStatement pstmt = null;
	static Statement stmt = null;
	static ResultSet rs = null;
	static Scanner sc = new Scanner(System.in);

	public static void insert() {

		try {

			// step 3: issue SQL query via connection
			String query = "insert into users_info values(?,?,?,?)";
			pstmt = conn.prepareStatement(query);

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

			// step 4: process the result
			if (count > 0) {
				System.out.println("data inserted...");
			} else {
				System.out.println("error!!!");
			}

			System.out.println("continue ? yes or no");
			if (sc.next().equals("yes")) {
				operations();
			} else {
				closeObjects();
				System.out.println("Thank you!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void view() {
		// step 3:issue sql query via connection
		try {
			String query1 = "select * from users_info";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query1);
			System.out.println("Query issued and executed...");
			System.out.println("--------------------------------------------------------");

			// step: 4 process the result
			while (rs.next()) {
				System.out.println("user id is: " + rs.getInt(1));
				System.out.println("username is: " + rs.getString("username"));
				System.out.println("email id is: " + rs.getString("email"));
				// System.out.println("password is: "+rs.getString("password"));
				System.out.println("-------------------------------------------------------");
			}

			System.out.println("continue ? yes or no");
			if (sc.next().equals("yes")) {
				operations();
			} else {
				closeObjects();
				System.out.println("Thank you!");
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// System.out.println("continue ? press 1");
		// int ans=sc.nextInt();
		// if(ans==1)
		// {
		// operations();
		// }
	}

	public static void update()

	{
		try {
			String query = "update users_info set email = ? where userid = ?";
			pstmt = conn.prepareStatement(query);

			System.out.println("enter emialid to update");
			String nemail = sc.nextLine();

			System.out.println("enter user id ");
			int uid = sc.nextInt();

			pstmt.setString(1, nemail);
			pstmt.setInt(2, uid);

			int count = pstmt.executeUpdate();

			// step 4: process the result
			if (count > 0) {
				System.out.println("data upgraded...");
			} else {
				System.out.println("error!!!");
			}

			System.out.println("continue ? yes or no");
			if (sc.next().equals("yes")) {
				operations();
			} else {
				closeObjects();
				System.out.println("Thank you!");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println("continue ? press 1");
		// int ans=sc.nextInt();
		// if(ans==1)
		// {
		// operations();
		// }

	}

	private static void delete() {

		try {

			// step 3 issue query

			String query = "delete from users_info where userid = ?";
			pstmt = conn.prepareStatement(query);

			System.out.println("enter user id ");
			int uid = sc.nextInt();

			pstmt.setInt(1, uid);

			int count = pstmt.executeUpdate();

			// step 4: process the result
			if (count > 0) {
				System.out.println("row deleted...");
			} else {
				System.out.println("error!!!");
			}

			System.out.println("continue ? yes or no");
			if (sc.next().equals("yes")) {
				operations();
			} else {
				closeObjects();
				System.out.println("Thank you!");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println("continue ? press 1");
		// int ans=sc.nextInt();
		// if(ans==1)
		// {
		// operations();
		// }

	}

	public static void closeObjects() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		System.out.println("JDBC objects closed!!!");
	}

	public static void operations() {
		System.out.println("select operation to perform");
		System.out.println("1 to insert");
		System.out.println("2 to view data");
		System.out.println("3 to update data");
		System.out.println("4 to delete data");
		System.out.println("5 to close all JDBC objects");
		int n = sc.nextInt();
		if (n == 1) {
			insert();
		} else if (n == 2) {
			view();
		} else if (n == 3) {
			insert();
		} else if (n == 4) {
			delete();
		} else if (n == 5) {
			closeObjects();
		} else {
			System.out.println("invalid choice");
		}
	}

	public static void main(String[] args) {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("driver loaded..");

			// step 2 get the connection
			String url = "jdbc:mysql://localhost:3306/caps_buggers";
			System.out.println("enter username");
			String user = sc.nextLine();
			System.out.println("enter password");
			String password = sc.nextLine();
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("Connection Estd...");
		} catch (Exception e) {
			System.out.println("invalid username or password");
			// e.printStackTrace();
		}

		if (conn != null) {
			operations();
		} else {
			System.out.println("try again");
			System.out.println("--------------------------------------");
			main(args);
		}
	}

}
