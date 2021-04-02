package com.fsoft.hieupnm.booklendsystem.model.DAO.implementation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.TreeSet;

import org.junit.Before;
import org.junit.Test;

import com.fsoft.hieupnm.booklendsystem.model.DAO.BookDAO;
import com.fsoft.hieupnm.booklendsystem.model.DAO.UserDAO;
import com.fsoft.hieupnm.booklendsystem.model.POJO.Book;
import com.fsoft.hieupnm.booklendsystem.model.POJO.User;
import com.fsoft.hieupnm.booklendsystem.utils.dbconnection.DBUtils;

public class BookDAOImplTest {

	@Before
	public void resetDatabase() {
		DBUtils.createDatabase();
	}

	@Test
	public void isBookExisted_pass() {
		BookDAO bookDAO = new BookDAOImpl();
		bookDAO.createNewBook(new Book(1, "JAVA", "ANDERSON"));
		assertTrue(bookDAO.isBookExisted(1));
	}

	@Test
	public void isBookExisted_fail() {
		BookDAO bookDAO = new BookDAOImpl();
		assertFalse(bookDAO.isBookExisted(99));
	}

	@Test
	public void readBookById_pass() {
		BookDAO bookDAO = new BookDAOImpl();
		UserDAO userDAO = new UserDAOImpl();
		Book expected = new Book(1, "JAVA", "ANDERSON", LocalDate.now(), true, LocalDate.now(), LocalDate.now().plusDays(15), 1);
		userDAO.createNewUser(new User(1, "JOHN", "LENNON", LocalDate.of(1995, 10, 20)));
		bookDAO.createNewBook(new Book(1, "JAVA", "ANDERSON"));
		bookDAO.lendBook(new Book(1, 1));
		Book result = bookDAO.readBookById(1);
		assertEquals(expected, result);
	}

	@Test
	public void readBookById_fail() {
		BookDAO bookDAO = new BookDAOImpl();
		Book book = bookDAO.readBookById(99);
		assertNull(book);
	}
	
	@Test
	public void readAllBook_pass() {
		BookDAO bookDAO = new BookDAOImpl();
		UserDAO userDAO = new UserDAOImpl();
		Book expected = new Book(1, "JAVA", "ANDERSON", LocalDate.now(), true, LocalDate.now(), LocalDate.now().plusDays(15), 1);
		userDAO.createNewUser(new User(1, "JOHN", "LENNON", LocalDate.of(1995, 10, 20)));
		bookDAO.createNewBook(new Book(1, "JAVA", "ANDERSON"));
		bookDAO.lendBook(new Book(1, 1));
		TreeSet<Book> result = bookDAO.readAllBook();
		for (Book book : result) {
			assertEquals(expected, book);
		}
	}
	
	@Test
	public void updateBookById_pass() {
		BookDAO bookDAO = new BookDAOImpl();
		Book expected = new Book(1, "C#", "ELTON");
		
		bookDAO.createNewBook(new Book(1, "JAVA", "ANDERSON"));
		assertTrue(bookDAO.updateBookById(new Book(1, "C#", "ELTON")));
		
		Book result = bookDAO.readBookById(1);
		assertEquals(expected, result);
	}
	
	@Test
	public void updateBookById_fail() {
		BookDAO bookDAO = new BookDAOImpl();
		assertFalse(bookDAO.updateBookById(new Book(1, "C#", "ELTON")));
	}
	
	@Test
	public void returnBook_pass() {
		BookDAO bookDAO = new BookDAOImpl();
		UserDAO userDAO = new UserDAOImpl();
		userDAO.createNewUser(new User(1, "JOHN", "LENNON", LocalDate.of(1995, 10, 20)));
		bookDAO.createNewBook(new Book(1, "JAVA", "ANDERSON"));
		bookDAO.lendBook(new Book(1, 1));
		assertTrue(bookDAO.returnBook(1));
	}
	
	@Test
	public void returnBook_fail() {
		BookDAO bookDAO = new BookDAOImpl();
		assertFalse(bookDAO.returnBook(1));
	}
	
	@Test
	public void deleteBookById_pass() {
		BookDAO bookDAO = new BookDAOImpl();
		bookDAO.createNewBook(new Book(1, "JAVA", "ANDERSON"));
		assertTrue(bookDAO.deleteBookById(1));
	}
	
	@Test
	public void deleteBookById_fail() {
		BookDAO bookDAO = new BookDAOImpl();
		assertFalse(bookDAO.deleteBookById(1));
	}
	
	@Test
	public void isBookLend_pass() {
		BookDAO bookDAO = new BookDAOImpl();
		UserDAO userDAO = new UserDAOImpl();
		userDAO.createNewUser(new User(1, "JOHN", "LENNON", LocalDate.of(1995, 10, 20)));
		bookDAO.createNewBook(new Book(1, "JAVA", "ANDERSON"));
		bookDAO.lendBook(new Book(1, 1));
		assertTrue(bookDAO.isBookLend(1));
	}
	
	@Test
	public void isBookLend_fail() {
		BookDAO bookDAO = new BookDAOImpl();
		bookDAO.createNewBook(new Book(1, "JAVA", "ANDERSON"));
		assertFalse(bookDAO.isBookLend(1));
	}
	
}
