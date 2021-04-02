package com.fsoft.hieupnm.booklendsystem.service.implementation;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.fsoft.hieupnm.booklendsystem.service.BookService;
import com.fsoft.hieupnm.booklendsystem.service.UserService;
import com.fsoft.hieupnm.booklendsystem.utils.dbconnection.DBUtils;

public class BookServiceImplTest {
	
	@Before
	public void resetDatabase() {
		DBUtils.createDatabase();
	}
	
	@Test
	public void addNewBookFromInputFile_pass() {
		BookService bookService = new BookServiceImpl();
		int recordCount = bookService.addNewBookFromInputFile();
		boolean result = recordCount == 3;
		assertTrue(result);
	}

	@Test
	public void updateBookFromInputFile_pass() {
		BookService bookService = new BookServiceImpl();
		bookService.addNewBookFromInputFile();
		int recordCount = bookService.updateBookFromInputFile();
		boolean result = recordCount == 1;
		assertTrue(result);
	}

	@Test
	public void readBookFromInputFile_pass() {
		BookService bookService = new BookServiceImpl();
		bookService.addNewBookFromInputFile();
		int recordCount = bookService.readBookFromInputFile();
		boolean result = recordCount == 1;
		assertTrue(result);
	}

	@Test
	public void deleteBookFromInputFile_pass() {
		BookService bookService = new BookServiceImpl();
		bookService.addNewBookFromInputFile();
		int recordCount = bookService.deleteBookFromInputFile();
		boolean result = recordCount == 1;
		assertTrue(result);
	}
	
	@Test
	public void lendBookFromInputFile_pass() {
		BookService bookService = new BookServiceImpl();
		bookService.addNewBookFromInputFile();
		UserService userService = new UserServiceImpl();
		userService.addNewUserFromInputFile();
		int recordCount = bookService.lendBookFromInputFile();
		boolean result = recordCount == 1;
		assertTrue(result);
	}
	
	@Test
	public void returnBookFromInputFile_pass() {
		BookService bookService = new BookServiceImpl();
		bookService.addNewBookFromInputFile();
		UserService userService = new UserServiceImpl();
		userService.addNewUserFromInputFile();
		bookService.lendBookFromInputFile();
		int recordCount = bookService.returnBookFromInputFile();
		boolean result = recordCount == 1;
		assertTrue(result);
	}
	
	@Test
	public void printAllBooks_pass() {
		BookService bookService = new BookServiceImpl();
		bookService.addNewBookFromInputFile();
		bookService.printAllBooks();
	}
}
