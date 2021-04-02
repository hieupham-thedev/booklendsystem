package com.fsoft.hieupnm.booklendsystem.utils.dbconnection;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

public class DBUtilsTest {
	@Test
	public void getDBConnection_pass() throws SQLException {
		DBUtils.DRIVER = "abc";
		Connection con = DBUtils.getDBConnection();
		assertNull(con);
		DBUtils.DRIVER = "com.mysql.cj.jdbc.Driver";
		DBUtils.USER = "";
		con = DBUtils.getDBConnection();
		assertNull(con);
		DBUtils.USER = "root";
		con = DBUtils.getDBConnection();
		assertNotNull(con);
		con.close();
	}
	
	@Test
	public void getHostConnection_pass() throws SQLException {
		DBUtils.DRIVER = "abc";
		Connection con = DBUtils.getHostConnection();
		assertNull(con);
		DBUtils.DRIVER = "com.mysql.cj.jdbc.Driver";
		DBUtils.USER = "roo";
		con = DBUtils.getHostConnection();
		assertNull(con);
		DBUtils.USER = "root";
		con = DBUtils.getHostConnection();
		assertNotNull(con);
		con.close();
	}
	
	@Test
	public void createDatabase_pass() {
		DBUtils.createDatabase();
	}
}
