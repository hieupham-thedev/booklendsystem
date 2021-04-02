package com.fsoft.hieupnm.booklendsystem.model.POJO;

import java.time.LocalDate;

public class Book implements Comparable<Book> {
	private Integer bookId;
	private String title;
	private String author;
	private LocalDate createdDate;
	private boolean isLend;
	private LocalDate lendDate;
	private LocalDate returnDate;
	private Integer userId;

	// For read information of book
	public Book(Integer bookId, String title, String author, LocalDate createdDate, boolean isLend, LocalDate lendDate,
			LocalDate returnDate, Integer userId) {
		this.bookId = bookId;
		this.title = title;
		this.author = author;
		this.createdDate = createdDate;
		this.isLend = isLend;
		this.lendDate = lendDate;
		this.returnDate = returnDate;
		this.userId = userId;
	}

	// For create/update information of book
	public Book(Integer bookId, String title, String author) {
		this.bookId = bookId;
		this.title = title;
		this.author = author;
		this.createdDate = LocalDate.now();
	}

	// For update lending book
	public Book(Integer bookId, Integer userId) {
		this.bookId = bookId;
		this.isLend = true;
		LocalDate date = LocalDate.now();
		this.lendDate = date;
		// Update returnDate after lendDate 15 days
		this.returnDate = date.plusDays(15);
		this.userId = userId;
	}

	public Integer getBookId() {
		return bookId;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public boolean isLend() {
		return isLend;
	}

	public LocalDate getLendDate() {
		return lendDate;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}

	public Integer getUserId() {
		return userId;
	}

	@Override
	public int compareTo(Book book) {
		if (this.bookId > book.getBookId()) {
			return 1;
		} else if (this.bookId < book.getBookId()) {
			return -1;
		} else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object book) {
		try {
			Book compareBook = (Book) book;
			boolean equalBookId = this.bookId == compareBook.getBookId();
			boolean equalTitle = this.title.equals(compareBook.getTitle());
			boolean equalAuthor = this.author.equals(compareBook.getAuthor());
			boolean equalcrDate = this.createdDate.toString().equals(compareBook.getCreatedDate().toString());
			boolean equalisLend = this.isLend == compareBook.isLend();
			boolean equalLDate = false;
			boolean equalRDate = false;
			try {
				equalLDate = this.lendDate.toString().equals(compareBook.getLendDate().toString());
				equalRDate = this.returnDate.toString().equals(compareBook.getReturnDate().toString());
			} catch (NullPointerException e) {
				equalLDate = this.lendDate == null && compareBook.getLendDate() == null;
				equalRDate = this.returnDate == null && compareBook.getReturnDate() == null;
			}
			boolean equalUserId = this.userId == compareBook.getUserId();

			if (equalBookId && equalTitle && equalAuthor && equalcrDate && equalcrDate && equalisLend && equalLDate
					&& equalRDate && equalUserId) {
				return true;
			}
		} catch (ClassCastException e) {

		}
		return false;
	}

	@Override
	public String toString() {
		return "[" + "ID: " + this.bookId + ", " + "Title: " + this.title + ", " + "Author: " + this.author + ", "
				+ "CreatedDate: " + this.createdDate + ", " + "IsLend: " + this.isLend + ", " + "LendDate: "
				+ this.lendDate + ", " + "ReturnDate: " + this.returnDate + ", " + "UserID: " + this.userId + "]";
	}

}
