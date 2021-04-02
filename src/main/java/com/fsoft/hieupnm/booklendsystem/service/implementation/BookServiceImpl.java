package com.fsoft.hieupnm.booklendsystem.service.implementation;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.TreeSet;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.fsoft.hieupnm.booklendsystem.model.DAO.BookDAO;
import com.fsoft.hieupnm.booklendsystem.model.DAO.UserDAO;
import com.fsoft.hieupnm.booklendsystem.model.DAO.implementation.BookDAOImpl;
import com.fsoft.hieupnm.booklendsystem.model.DAO.implementation.UserDAOImpl;
import com.fsoft.hieupnm.booklendsystem.model.POJO.Book;
import com.fsoft.hieupnm.booklendsystem.service.BookService;
import com.fsoft.hieupnm.booklendsystem.utils.constant.Constants;
import com.fsoft.hieupnm.booklendsystem.utils.validation.InputValidator;

public class BookServiceImpl implements BookService {

	static BookDAO bookDAO = new BookDAOImpl();
	static UserDAO userDAO = new UserDAOImpl();

	/*
	 * Add new book from data get from inputfile
	 */
	@Override
	public int addNewBookFromInputFile() {
		int recordCreated = 0;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(Constants.CREATE_BOOK_PATH));
			try {
				int lineCount = 0;
				while (br.ready()) {
					lineCount++;
					System.out.print("Line " + lineCount + ": ");
					String[] bookInfo = br.readLine().split("::");
					if (bookInfo.length == 3) {
						String id = bookInfo[0];

						if (InputValidator.isValidId(id)) {
							Integer bookId = Integer.parseInt(id);

							if (!bookDAO.isBookExisted(bookId)) {

								if (InputValidator.isValidLength(bookInfo[1])) {
									String title = bookInfo[1];

									if (InputValidator.isValidLength(bookInfo[2])) {
										String author = bookInfo[2];
										Book book = new Book(bookId, title, author);
										bookDAO.createNewBook(book);
										System.out.print("Book with ID " + bookId + " created successfully !");
										recordCreated++;
									} else {
										System.out.print("Invalid author length !");
									}
								} else {
									System.out.print("Invalid title length !");
								}
							} else {
								System.out.print("Book with ID " + bookId + " already existed !");
							}
						} else {
							System.out.print("Book ID (" + id + ") format not valid !");
						}
					} else {
						System.out.print("Invalid amount of information !");
					}
					System.out.println();
				}
			} catch (IOException e) {
				System.out.println("IOException: " + e);
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found !");
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					System.out.println("Error closing stream !");
				}
			}
		}
		return recordCreated;
	}

	/*
	 * Update book information from data from inputfile
	 */
	@Override
	public int updateBookFromInputFile() {
		int recordUpdated = 0;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(Constants.UPDATE_BOOK_PATH));
			try {
				int lineCount = 0;
				while (br.ready()) {
					lineCount++;
					System.out.print("Line " + lineCount + ": ");
					String[] bookInfo = br.readLine().split("::");
					if (bookInfo.length == 3) {
						String id = bookInfo[0];

						if (InputValidator.isValidId(id)) {
							Integer bookId = Integer.parseInt(id);

							if (bookDAO.isBookExisted(bookId)) {

								if (InputValidator.isValidLength(bookInfo[1])) {
									String title = bookInfo[1];

									if (InputValidator.isValidLength(bookInfo[2])) {
										String author = bookInfo[2];
										Book book = new Book(bookId, title, author);
										bookDAO.updateBookById(book);
										System.out.print("Book with ID " + bookId + " updated successfully !");
										recordUpdated++;
									} else {
										System.out.print("Invalid author length !");
									}
								} else {
									System.out.print("Invalid title length !");
								}
							} else {
								System.out.print("Book with ID " + bookId + " not existed !");
							}
						} else {
							System.out.print("Book ID (" + id + ") format not valid !");
						}
					} else {
						System.out.print("Invalid amount of information !");
					}
					System.out.println();
				}
			} catch (IOException e) {
				System.out.println("IOException: " + e);
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found !");
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					System.out.println("Error closing stream !");
				}
			}
		}
		return recordUpdated;
	}

	/*
	 * Read book information from id from input file
	 */
	@Override
	public int readBookFromInputFile() {
		int recordRead = 0;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(Constants.READ_BOOK_PATH));
			try {
				int lineCount = 0;
				while (br.ready()) {
					lineCount++;
					System.out.print("Line " + lineCount + ": ");
					String id = br.readLine();
					if (InputValidator.isValidId(id)) {
						Integer bookId = Integer.parseInt(id);
						if (bookDAO.isBookExisted(bookId)) {
							Book book = bookDAO.readBookById(bookId);
							System.out.print(book.toString());
							recordRead++;
						} else {
							System.out.print("Book with ID " + bookId + " not existed !");
						}
					} else {
						System.out.print("Book ID (" + id + ") format not valid !");
					}
					System.out.println();
				}
			} catch (IOException e) {
				System.out.println("IOException: " + e);
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found !");
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					System.out.println("Error closing stream !");
				}
			}
		}
		return recordRead;
	}

	/*
	 * Delete book from id from inputfile
	 */
	@Override
	public int deleteBookFromInputFile() {
		int recordDeleted = 0;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(Constants.DELETE_BOOK_PATH));
			try {
				int lineCount = 0;
				while (br.ready()) {
					lineCount++;
					System.out.print("Line " + lineCount + ": ");
					String id = br.readLine();
					if (InputValidator.isValidId(id)) {
						Integer bookId = Integer.parseInt(id);
						if (bookDAO.isBookExisted(bookId)) {
							bookDAO.deleteBookById(bookId);
							System.out.print("Book with ID " + bookId + " deleted successfully !");
							recordDeleted++;
						} else {
							System.out.print("Book with ID " + bookId + " not existed !");
						}
					} else {
						System.out.print("Book ID (" + id + ") format not valid !");
					}
					System.out.println();
				}
			} catch (IOException e) {
				System.out.println("IOException: " + e);
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found !");
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					System.out.println("Error closing stream !");
				}
			}
		}
		return recordDeleted;
	}

	/*
	 * Lend book from information from inputfile
	 */
	@Override
	public int lendBookFromInputFile() {
		int recordLend = 0;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(Constants.LEND_BOOK_PATH));
			try {
				int lineCount = 0;
				while (br.ready()) {
					lineCount++;
					System.out.print("Line " + lineCount + ": ");
					String[] lendInfo = br.readLine().split("::");
					if (lendInfo.length == 2) {
						if (InputValidator.isValidId(lendInfo[0])) {
							Integer bookId = Integer.parseInt(lendInfo[0]);
							if (InputValidator.isValidId(lendInfo[1])) {
								Integer userId = Integer.parseInt(lendInfo[1]);
								if (bookDAO.isBookExisted(bookId)) {
									if (userDAO.isUserExisted(userId)) {
										if (bookDAO.isBookLend(bookId)) {
											LocalDate returnDate = bookDAO.readBookById(bookId).getReturnDate();
											System.out.print("Book with ID " + bookId
													+ " is not available, will be available at "
													+ returnDate.toString());
										} else {
											bookDAO.lendBook(new Book(bookId, userId));
											recordLend++;
											System.out.print(
													"Book " + bookId + " is successfully lend to user " + userId);
										}
									} else {
										System.out.print("User with ID " + userId + " not existed !");
									}
								} else {
									System.out.print("Book with ID " + bookId + " not existed !");
								}

							} else {
								System.out.print("User ID (" + lendInfo[1] + ") format not valid !");
							}
						} else {
							System.out.print("Book ID (" + lendInfo[0] + ") format not valid !");
						}
					} else {
						System.out.print("Invalid amount of information !");
					}
					System.out.println();
				}
			} catch (IOException e) {
				System.out.println("IOException: " + e);
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found !");
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					System.out.println("Error closing stream !");
				}
			}
		}
		return recordLend;
	}

	/*
	 * Return book from data from inputfile
	 */
	@Override
	public int returnBookFromInputFile() {
		int recordReturned = 0;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(Constants.RETURN_BOOK_PATH));
			try {
				int lineCount = 0;
				while (br.ready()) {
					lineCount++;
					System.out.print("Line " + lineCount + ": ");
					String id = br.readLine();
					if (InputValidator.isValidId(id)) {
						Integer bookId = Integer.parseInt(id);
						if (bookDAO.isBookExisted(bookId)) {
							if (bookDAO.isBookLend(bookId)) {
								bookDAO.returnBook(bookId);
								recordReturned++;
								System.out.print("Book with ID " + bookId + " returned successfully !");
							} else {
								System.out.print("Book with ID " + bookId + " is not lend !");
							}
						} else {
							System.out.print("Book with ID " + bookId + " not existed !");
						}
					} else {
						System.out.print("Book ID (" + id + ") format not valid !");
					}
					System.out.println();
				}
			} catch (IOException e) {
				System.out.println("IOException: " + e);
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found !");
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					System.out.println("Error closing stream !");
				}
			}
		}
		return recordReturned;
	}

	/*
	 * Print all books information to excel file
	 */
	@Override
	public int printAllBooks() {
		TreeSet<Book> bookList = bookDAO.readAllBook();
		int recordRead = 0;
		if (bookList.size() > 0) {
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(Constants.EXCEL_TEMPLATE_BOOK_PATH);
				try {
					Workbook workbook = new HSSFWorkbook(fis);
					Sheet sheet = workbook.getSheet("BookStatus");
					int bookCount = 1;
					for (Book book : bookList) {
						Row row = sheet.createRow(bookCount);

						Cell cell = row.createCell(0);
						cell.setCellValue(book.getBookId());

						cell = row.createCell(1);
						cell.setCellValue(book.getTitle());

						cell = row.createCell(2);
						cell.setCellValue(book.getAuthor());

						cell = row.createCell(3);
						cell.setCellValue(book.getCreatedDate().toString());

						cell = row.createCell(4);
						cell.setCellValue(book.isLend());

						cell = row.createCell(5);
						if (book.getLendDate() == null) {
							cell.setCellValue("null");
						} else {
							cell.setCellValue(book.getLendDate().toString());
						}

						cell = row.createCell(6);
						if (book.getReturnDate() == null) {
							cell.setCellValue("null");
						} else {
							cell.setCellValue(book.getReturnDate().toString());
						}

						cell = row.createCell(7);
						if (book.getUserId() == null) {
							cell.setCellValue("null");
						} else {
							cell.setCellValue(book.getUserId());
						}

						bookCount++;
						recordRead++;
					}
					FileOutputStream outputfile = null;
					String createdTime = LocalDateTime.now().toString();
					createdTime = createdTime.replace(':', '-');
					createdTime = createdTime.replace('.', '-');
					String filePath = Constants.PRINT_ALL_BOOK_PATH + "PrintBooks_" + createdTime + ".xls";
					outputfile = new FileOutputStream(filePath);
					workbook.write(outputfile);
					System.out.println("File created path: " + filePath);
					outputfile.close();
					workbook.close();
				} catch (IOException e) {
					System.out.println("Error read/write file !");
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				System.out.println("File not found !");
			} finally {
				if (fis != null) {
					try {
						fis.close();
					} catch (IOException e) {
						System.out.println("Error closing stream !");
					}
				}
			}
		}
		return recordRead;
	}

}
