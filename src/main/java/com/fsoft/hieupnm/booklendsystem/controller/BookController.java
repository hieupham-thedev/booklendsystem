package com.fsoft.hieupnm.booklendsystem.controller;

import java.util.Scanner;

import com.fsoft.hieupnm.booklendsystem.service.BookService;
import com.fsoft.hieupnm.booklendsystem.service.implementation.BookServiceImpl;
import com.fsoft.hieupnm.booklendsystem.utils.constant.Constants;
import com.fsoft.hieupnm.booklendsystem.utils.format.StringFormat;


/*
 * Option for book CRUD controller
 */
public class BookController {
	public void menu() {
		Scanner scan = new Scanner(System.in);
		BookService bookService = new BookServiceImpl();
		boolean result = true;
		byte option = 0;
		while (result) {
			System.out.println(StringFormat.displayTitle("BOOK CRUD OPTION"));
			System.out.println("1. Create new book from input file");
			System.out.println("2. Read book from input file");
			System.out.println("3. Update book from input file");
			System.out.println("4. Delete book from input file");
			System.out.println("5. Back");
			System.out.println(Constants.HORIZONTAL_LINE);

			while (true) {
				try {
					System.out.print("Option: ");
					option = Byte.parseByte(scan.nextLine());
					break;
				} catch (NumberFormatException e) {
					System.out.println("Invalid input, please enter number !");
				}
			}

			switch (option) {
			case 1:
				int recordCreated = bookService.addNewBookFromInputFile();
				System.out.println("Record created: " + recordCreated);
				break;
			case 2:
				int recordRead = bookService.readBookFromInputFile();
				System.out.println("Record read: " + recordRead);
				break;
			case 3:
				int recordUpdated = bookService.updateBookFromInputFile();
				System.out.println("Record updated: " + recordUpdated);
				break;
			case 4:
				int recordDeleted = bookService.deleteBookFromInputFile();
				System.out.println("Record deleted: " + recordDeleted);
				break;
			case 5:
				result = false;
				break;
			default:
				System.out.println("Invalid option, please enter option on the list !");
			}

		}
	}
}
