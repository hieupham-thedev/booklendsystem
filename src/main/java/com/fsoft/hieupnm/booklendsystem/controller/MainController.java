package com.fsoft.hieupnm.booklendsystem.controller;

import java.util.Scanner;

import com.fsoft.hieupnm.booklendsystem.service.BookService;
import com.fsoft.hieupnm.booklendsystem.service.UserService;
import com.fsoft.hieupnm.booklendsystem.service.implementation.BookServiceImpl;
import com.fsoft.hieupnm.booklendsystem.service.implementation.UserServiceImpl;
import com.fsoft.hieupnm.booklendsystem.utils.constant.Constants;
import com.fsoft.hieupnm.booklendsystem.utils.dbconnection.DBUtils;
import com.fsoft.hieupnm.booklendsystem.utils.format.StringFormat;

/*
 * Option for main CRUD controller
 */
public class MainController {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		BookService bookService = new BookServiceImpl();
		UserService userService = new UserServiceImpl();
		BookController bookController = new BookController();
		UserController userController = new UserController();
		boolean result = true;
		byte option = 0;
		while (result) {
			System.out.println(StringFormat.displayTitle("BOOK LEND SYSTEM"));
			System.out.println("1. User CRUD option");
			System.out.println("2. Book CRUD option");
			System.out.println("3. Lend books");
			System.out.println("4. Return books");
			System.out.println("5. Print books to excel file");
			System.out.println("6. Print users to excel file");
			System.out.println("7. Reset database");
			System.out.println("8. Exit");
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
				userController.menu();
				break;
			case 2:
				bookController.menu();
				break;
			case 3:
				int recordLend = bookService.lendBookFromInputFile();
				System.out.println("Record Lend: " + recordLend);
				break;
			case 4:
				int recordReturn = bookService.returnBookFromInputFile();
				System.out.println("Record Return: " + recordReturn);
				break;
			case 5:
				bookService.printAllBooks();
				break;
			case 6:
				userService.printAllUsers();
				break;
			case 7:
				DBUtils.createDatabase();
				System.out.println("Database reset successfully !");
				break;
			case 8:
				result = false;
				System.out.println("Goodbye !");
				break;
			default:
				System.out.println("Invalid option, please enter option on the list !");
			}

		}
	}
}
