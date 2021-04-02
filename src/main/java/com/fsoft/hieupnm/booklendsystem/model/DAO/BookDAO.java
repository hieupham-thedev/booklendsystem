package com.fsoft.hieupnm.booklendsystem.model.DAO;

import java.util.TreeSet;

import com.fsoft.hieupnm.booklendsystem.model.POJO.Book;

public interface BookDAO {
	boolean createNewBook(Book book);

	Book readBookById(Integer bookId);

	TreeSet<Book> readAllBook();

	boolean updateBookById(Book book);

	boolean lendBook(Book book);

	boolean returnBook(Integer bookId);

	boolean deleteBookById(Integer bookId);

	boolean isBookExisted(Integer bookId);

	boolean isBookLend(Integer bookId);
}
