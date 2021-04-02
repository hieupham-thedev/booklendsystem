package com.fsoft.hieupnm.booklendsystem.service;

public interface BookService {
	int addNewBookFromInputFile();

	int updateBookFromInputFile();

	int readBookFromInputFile();

	int deleteBookFromInputFile();

	int lendBookFromInputFile();

	int returnBookFromInputFile();

	int printAllBooks();
}
