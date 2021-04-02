package com.fsoft.hieupnm.booklendsystem.utils.dbconnection;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.fsoft.hieupnm.booklendsystem.utils.constant.Constants;

public class DBUtils {
	/*
	 * List of all information needed to make database connection
	 */
	public static String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String DRIVER_URL = "jdbc:mysql://";
	private static final String HOST = "localhost";
	private static final String PORT = ":3306";
	private static final String FORWARD_SLASH = "/";
	private static final String DB_NAME = "book_lend_system_hieupnm";
	public static String USER = "root";
	public static String PASS = "root";

	/*
	 * Make connection to database
	 */
	public static Connection getDBConnection() {
		try {
			Class.forName(DRIVER);
			try {
				return DriverManager.getConnection(DRIVER_URL + HOST + PORT + FORWARD_SLASH + DB_NAME, USER, PASS);
			} catch (SQLException e) {
				System.out.println("Wrong user/pass/connection url !");
			}
		} catch (ClassNotFoundException e) {
			System.out.println("Driver class not found !");
		}
		return null;
	}

	/*
	 * Make connection to host
	 */
	public static Connection getHostConnection() {
		try {
			Class.forName(DRIVER);
			try {
				return DriverManager.getConnection(DRIVER_URL + HOST + PORT + FORWARD_SLASH, USER, PASS);
			} catch (SQLException e) {
				System.out.println("Wrong user/pass/connection url !");
			}
		} catch (ClassNotFoundException e) {
			System.out.println("Driver class not found !");
		}
		return null;
	}

	/*
	 * Create / renew database
	 */
	public static void createDatabase() {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(Constants.CREATE_DATABASE_PATH));
			StringBuilder sql = new StringBuilder();
			try {
				while (br.ready()) {
					sql.append(br.readLine());
				}
				String[] SQL = sql.toString().split(";");
				Connection con = getHostConnection();
				try {
					for (String sqlLine : SQL) {
						PreparedStatement preparedStatement = con.prepareStatement(sqlLine);
						preparedStatement.execute();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					if (con != null) {
						try {
							con.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
