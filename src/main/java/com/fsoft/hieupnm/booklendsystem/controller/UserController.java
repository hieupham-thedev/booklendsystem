package com.fsoft.hieupnm.booklendsystem.controller;

import java.util.Scanner;

import com.fsoft.hieupnm.booklendsystem.service.UserService;
import com.fsoft.hieupnm.booklendsystem.service.implementation.UserServiceImpl;
import com.fsoft.hieupnm.booklendsystem.utils.constant.Constants;
import com.fsoft.hieupnm.booklendsystem.utils.format.StringFormat;

/*
 * Option for user CRUD controller
 */
public class UserController {
	public void menu() {
		Scanner scan = new Scanner(System.in);
		UserService userService = new UserServiceImpl();
		boolean result = true;
		byte option = 0;
		while (result) {
			System.out.println(StringFormat.displayTitle("USER CRUD OPTION"));
			System.out.println("1. Create new user from input file");
			System.out.println("2. Read user from input file");
			System.out.println("3. Update user from input file");
			System.out.println("4. Delete user from input file");
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
				int recordCreated = userService.addNewUserFromInputFile();
				System.out.println("Record created: " + recordCreated);
				break;
			case 2:
				int recordRead = userService.readUserFromInputFile();
				System.out.println("Record read: " + recordRead);
				break;
			case 3:
				int recordUpdated = userService.updateUserFromInputFile();
				System.out.println("Record updated: " + recordUpdated);
				break;
			case 4:
				int recordDeleted = userService.deleteUserFromInputFile();
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
