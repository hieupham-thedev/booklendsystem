package com.fsoft.hieupnm.booklendsystem.model.DAO.implementation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import com.fsoft.hieupnm.booklendsystem.model.DAO.UserDAO;
import com.fsoft.hieupnm.booklendsystem.model.POJO.User;
import com.fsoft.hieupnm.booklendsystem.utils.dbconnection.DBUtils;

public class UserDAOImplTest {

	@Before
	public void resetDatabase() {
		DBUtils.createDatabase();
	}

	@Test
	public void readAllUser_pass() {
		UserDAO userDAO = new UserDAOImpl();
		userDAO.createNewUser(new User(1, "JOHN", "LENNON", LocalDate.of(1995, 10, 20)));
		assertEquals((long) userDAO.readAllUser().size(), 1);
	}

	@Test
	public void readUserById_pass() {
		UserDAO userDAO = new UserDAOImpl();
		User expected = new User(1, "JOHN", "LENNON", LocalDate.of(1995, 10, 20));
		userDAO.createNewUser(expected);
		User actual = userDAO.readUserById(1);
		assertEquals(expected, actual);
	}
	
	@Test
	public void readUserById_fail() {
		UserDAO userDAO = new UserDAOImpl();
		User actual = userDAO.readUserById(1);
		assertNull(actual);
	}
	
	@Test
	public void createNewUser_pass() {
		UserDAO userDAO = new UserDAOImpl();
		boolean result = userDAO.createNewUser(new User(1, "JOHN", "LENNON", LocalDate.of(1995, 10, 20)));
		assertTrue(result);
	}
	
	@Test
	public void createNewUser_fail() {
		UserDAO userDAO = new UserDAOImpl();
		userDAO.createNewUser(new User(1, "JOHN", "LENNON", LocalDate.of(1995, 10, 20)));
		boolean result = userDAO.createNewUser(new User(1, "JOHN", "LENNON", LocalDate.of(1995, 10, 20)));
		assertFalse(result);
	}
	
	@Test
	public void deleteUserById_pass() {
		UserDAO userDAO = new UserDAOImpl();
		userDAO.createNewUser(new User(1, "JOHN", "LENNON", LocalDate.of(1995, 10, 20)));
		assertTrue(userDAO.deleteUserById(1));
	}
	
	@Test
	public void deleteUserById_fail() {
		UserDAO userDAO = new UserDAOImpl();
		assertFalse(userDAO.deleteUserById(1));
	}
	
	@Test
	public void isUserExisted_pass() {
		UserDAO userDAO = new UserDAOImpl();
		userDAO.createNewUser(new User(1, "JOHN", "LENNON", LocalDate.of(1995, 10, 20)));
		assertTrue(userDAO.isUserExisted(1));
	}
	
	@Test
	public void isUserExisted_fail() {
		UserDAO userDAO = new UserDAOImpl();
		assertFalse(userDAO.isUserExisted(1));
	}
	
	@Test
	public void updateUserById_pass() {
		UserDAO userDAO = new UserDAOImpl();
		userDAO.createNewUser(new User(1, "JOHN", "LENNON", LocalDate.of(1995, 10, 20)));
		boolean result = userDAO.updateUserById(new User(1, "MARY", "ANGEL", LocalDate.of(1996, 05, 16)));
		assertTrue(result);
	}
	
	@Test
	public void updateUserById_fail() {
		UserDAO userDAO = new UserDAOImpl();
		boolean result = userDAO.updateUserById(new User(1, "MARY", "ANGEL", LocalDate.of(1996, 05, 16)));
		assertFalse(result);
	}
}
