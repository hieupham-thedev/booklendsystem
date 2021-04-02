package com.fsoft.hieupnm.booklendsystem.service.implementation;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import com.fsoft.hieupnm.booklendsystem.model.DAO.UserDAO;
import com.fsoft.hieupnm.booklendsystem.model.DAO.implementation.UserDAOImpl;
import com.fsoft.hieupnm.booklendsystem.model.POJO.User;
import com.fsoft.hieupnm.booklendsystem.service.UserService;
import com.fsoft.hieupnm.booklendsystem.utils.dbconnection.DBUtils;

public class UserServiceImplTest {
	
	@Before
	public void resetDatabase() {
		DBUtils.createDatabase();
	}
	
	@Test
	public void addNewUserFromInputFile_pass() {
		UserService userService = new UserServiceImpl();
		int rowCount = userService.addNewUserFromInputFile();
		boolean result = rowCount == 1;
		assertTrue(result);
	}
	
	@Test
	public void updateUserFromInputFile_pass() {
		UserService userService = new UserServiceImpl();
		User user = new User(1, "JOHN", "KENNY", LocalDate.now());
		UserDAO userDAO = new UserDAOImpl();
		userDAO.createNewUser(user);
		int rowCount = userService.updateUserFromInputFile();
		boolean result = rowCount == 1;
		assertTrue(result);
	}
	
	@Test
	public void readUserFromInputFile_pass() {
		UserService userService = new UserServiceImpl();
		User user = new User(1, "JOHN", "KENNY", LocalDate.now());
		UserDAO userDAO = new UserDAOImpl();
		userDAO.createNewUser(user);
		int rowCount = userService.readUserFromInputFile();
		boolean result = rowCount == 1;
		assertTrue(result);
	}
	
	@Test
	public void deleteUserFromInputFile_pass() {
		UserService userService = new UserServiceImpl();
		User user = new User(1, "JOHN", "KENNY", LocalDate.now());
		UserDAO userDAO = new UserDAOImpl();
		userDAO.createNewUser(user);
		int rowCount = userService.deleteUserFromInputFile();
		boolean result = rowCount == 1;
		assertTrue(result);
	}
	
	
	@Test
	public void printAllUsers_pass() {
		UserService userService = new UserServiceImpl();
		userService.addNewUserFromInputFile();
		userService.printAllUsers();
	}
}
