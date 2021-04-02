package com.fsoft.hieupnm.booklendsystem.model.DAO.implementation;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.TreeSet;

import com.fsoft.hieupnm.booklendsystem.model.DAO.BookDAO;
import com.fsoft.hieupnm.booklendsystem.model.POJO.Book;
import com.fsoft.hieupnm.booklendsystem.utils.dbconnection.DBUtils;

public class BookDAOImpl implements BookDAO {

	private static Connection con = DBUtils.getDBConnection();

	/*
	 * Create new book in database using Book object with fully declared information
	 */
	@Override
	public boolean createNewBook(Book book) {
		if (con != null) {

			String SQL = "INSERT INTO book(bookId, title, author, createdDate) VALUES (?, ?, ?, ?)";
			try {
				PreparedStatement preparedStatement = con.prepareStatement(SQL);
				preparedStatement.setInt(1, book.getBookId());
				preparedStatement.setString(2, book.getTitle());
				preparedStatement.setString(3, book.getAuthor());
				preparedStatement.setDate(4, Date.valueOf(book.getCreatedDate().toString()));

				int affectedRow = preparedStatement.executeUpdate();
				if (affectedRow > 0) {
					return true;
				}
			} catch (SQLException e) {
				System.out.println("Error SQL execution !");
			}

		}
		return false;
	}

	/*
	 * Get information of one book by bookId
	 */
	@Override
	public Book readBookById(Integer bookId) {
		if (con != null) {

			String SQL = "SELECT * FROM book WHERE bookId = ?";
			try {
				PreparedStatement preparedStatement = con.prepareStatement(SQL);
				preparedStatement.setInt(1, bookId);
				ResultSet resultSet = preparedStatement.executeQuery();
				if (resultSet.next()) {
					String title = resultSet.getString(2);
					String author = resultSet.getString(3);
					LocalDate createdDate = resultSet.getDate(4).toLocalDate();
					boolean isLend = resultSet.getBoolean(5);
					LocalDate lendDate = null;
					LocalDate returnDate = null;
					Integer userId = null;
					if (isLend) {
						lendDate = resultSet.getDate(6).toLocalDate();
						returnDate = resultSet.getDate(7).toLocalDate();
						userId = resultSet.getInt(8);
					}
					return new Book(bookId, title, author, createdDate, isLend, lendDate, returnDate, userId);
				}
			} catch (SQLException e) {
				System.out.println("Error SQL execution !");
			}

		}
		return null;
	}

	/*
	 * Get all book information from database and return list of book sort by bookId
	 */
	@Override
	public TreeSet<Book> readAllBook() {
		TreeSet<Book> bookList = new TreeSet<>();
		if (con != null) {

			String SQL = "SELECT * FROM book";
			try {
				PreparedStatement preparedStatement = con.prepareStatement(SQL);
				ResultSet resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					Integer bookId = resultSet.getInt(1);
					String title = resultSet.getString(2);
					String author = resultSet.getString(3);
					LocalDate createdDate = resultSet.getDate(4).toLocalDate();
					boolean isLend = resultSet.getBoolean(5);
					LocalDate lendDate = null;
					LocalDate returnDate = null;
					Integer userId = null;
					if (isLend) {
						lendDate = resultSet.getDate(6).toLocalDate();
						returnDate = resultSet.getDate(7).toLocalDate();
						userId = resultSet.getInt(8);
					}
					Book book = new Book(bookId, title, author, createdDate, isLend, lendDate, returnDate, userId);
					bookList.add(book);
				}
			} catch (SQLException e) {
				System.out.println("Error SQL execution !");
			}

		}
		return bookList;
	}

	/*
	 * Update book with existed ID with new information
	 */
	@Override
	public boolean updateBookById(Book book) {
		if (con != null) {

			String SQL = "UPDATE book SET title = ?, author = ? WHERE bookId = ?";
			try {
				PreparedStatement preparedStatement = con.prepareStatement(SQL);
				preparedStatement.setString(1, book.getTitle());
				preparedStatement.setString(2, book.getAuthor());
				preparedStatement.setInt(3, book.getBookId());

				int affectedRow = preparedStatement.executeUpdate();
				if (affectedRow > 0) {
					return true;
				}
			} catch (SQLException e) {
				System.out.println("Error SQL execution !");
			}

		}
		return false;
	}

	/*
	 * Delete book in database
	 */
	@Override
	public boolean deleteBookById(Integer bookId) {
		if (con != null) {

			String SQL = "DELETE FROM book WHERE bookId = ?";
			try {
				PreparedStatement preparedStatement = con.prepareStatement(SQL);
				preparedStatement.setInt(1, bookId);
				int affectedRow = preparedStatement.executeUpdate();
				if (affectedRow > 0) {
					return true;
				}
			} catch (SQLException e) {
				System.out.println("Error SQL execution !");
			}

		}
		return false;
	}

	/*
	 * Check if book ID was existed in database
	 */
	@Override
	public boolean isBookExisted(Integer bookId) {
		if (con != null) {
			String SQL = "SELECT * FROM book WHERE bookId = ?";
			try {
				PreparedStatement preparedStatement = con.prepareStatement(SQL);
				preparedStatement.setInt(1, bookId);
				ResultSet resultSet = preparedStatement.executeQuery();
				resultSet.last();
				if (resultSet.getRow() > 0) {
					return true;
				}
			} catch (SQLException e) {
				System.out.println("Error SQL execution !");
			}
		}
		return false;
	}

	/*
	 * Update lend activities information
	 */
	@Override
	public boolean lendBook(Book book) {
		if (con != null) {

			String SQL = "UPDATE book SET isLend = ?, lendDate = ?, returnDate = ?, userId = ? WHERE bookId = ?";
			try {
				PreparedStatement preparedStatement = con.prepareStatement(SQL);
				preparedStatement.setBoolean(1, book.isLend());
				preparedStatement.setDate(2, Date.valueOf(book.getLendDate().toString()));
				preparedStatement.setDate(3, Date.valueOf(book.getReturnDate().toString()));
				preparedStatement.setInt(4, book.getUserId());
				preparedStatement.setInt(5, book.getBookId());

				int affectedRow = preparedStatement.executeUpdate();
				if (affectedRow > 0) {
					return true;
				}
			} catch (SQLException e) {
				System.out.println("Error SQL execution !");
			}

		}
		return false;
	}

	/*
	 * Check if book is lend or not
	 */
	@Override
	public boolean isBookLend(Integer bookId) {
		if (con != null) {
			String SQL = "SELECT * FROM book WHERE bookId = ? AND isLend = true";
			try {
				PreparedStatement preparedStatement = con.prepareStatement(SQL);
				preparedStatement.setInt(1, bookId);
				ResultSet resultSet = preparedStatement.executeQuery();
				resultSet.last();
				if (resultSet.getRow() > 0) {
					return true;
				}
			} catch (SQLException e) {
				System.out.println("Error SQL execution !");
			}
		}
		return false;
	}

	/*
	 * Return book which are lend
	 */
	@Override
	public boolean returnBook(Integer bookId) {
		if (con != null) {

			String SQL = "UPDATE book SET isLend = false, lendDate = null, returnDate = null, userId = null WHERE bookId = ?";
			try {
				PreparedStatement preparedStatement = con.prepareStatement(SQL);
				preparedStatement.setInt(1, bookId);

				int affectedRow = preparedStatement.executeUpdate();
				if (affectedRow > 0) {
					return true;
				}
			} catch (SQLException e) {
				System.out.println("Error SQL execution !");
			}

		}
		return false;
	}

}
