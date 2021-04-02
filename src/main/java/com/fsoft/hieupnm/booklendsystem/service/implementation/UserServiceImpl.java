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

import com.fsoft.hieupnm.booklendsystem.model.DAO.UserDAO;
import com.fsoft.hieupnm.booklendsystem.model.DAO.implementation.UserDAOImpl;
import com.fsoft.hieupnm.booklendsystem.model.POJO.User;
import com.fsoft.hieupnm.booklendsystem.service.UserService;
import com.fsoft.hieupnm.booklendsystem.utils.constant.Constants;
import com.fsoft.hieupnm.booklendsystem.utils.validation.InputValidator;

public class UserServiceImpl implements UserService {

	static UserDAO userDAO = new UserDAOImpl();

	/*
	 * Open file, validate input and create new user in database
	 */
	@Override
	public int addNewUserFromInputFile() {
		int recordCreated = 0;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(Constants.CREATE_USER_PATH));
			try {
				int lineCount = 0;
				while (br.ready()) {
					lineCount++;
					System.out.print("Line " + lineCount + ": ");
					String[] userInfo = br.readLine().split("::");
					if (userInfo.length == 4) {
						String id = userInfo[0];

						if (InputValidator.isValidId(id)) {
							Integer userId = Integer.parseInt(id);

							if (!userDAO.isUserExisted(userId)) {

								if (InputValidator.isValidLength(userInfo[1])) {
									String firstname = userInfo[1];

									if (InputValidator.isValidLength(userInfo[2])) {
										String lastname = userInfo[2];

										if (InputValidator.isValidDate(userInfo[3])) {
											LocalDate dob = LocalDate.parse(userInfo[3]);
											User user = new User(userId, firstname, lastname, dob);
											userDAO.createNewUser(user);
											System.out.print("User with ID " + userId + " created successfully !");
											recordCreated++;
										} else {
											System.out.print("Invalid date of birth !");
										}
									} else {
										System.out.print("Invalid lastname length !");
									}
								} else {
									System.out.print("Invalid firstname length !");
								}
							} else {
								System.out.print("User with ID " + userId + " already existed !");
							}
						} else {
							System.out.print("User ID (" + id + ") format not valid !");
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
	 * Open file, validate information and update user information
	 */
	@Override
	public int updateUserFromInputFile() {
		int recordUpdated = 0;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(Constants.UPDATE_USER_PATH));
			try {
				int lineCount = 0;
				while (br.ready()) {
					lineCount++;
					System.out.print("Line " + lineCount + ": ");
					String[] userInfo = br.readLine().split("::");
					if (userInfo.length == 4) {
						String id = userInfo[0];

						if (InputValidator.isValidId(id)) {
							Integer userId = Integer.parseInt(id);

							if (userDAO.isUserExisted(userId)) {

								if (InputValidator.isValidLength(userInfo[1])) {
									String firstname = userInfo[1];

									if (InputValidator.isValidLength(userInfo[2])) {
										String lastname = userInfo[2];

										if (InputValidator.isValidDate(userInfo[3])) {
											LocalDate dob = LocalDate.parse(userInfo[3]);
											User user = new User(userId, firstname, lastname, dob);
											userDAO.updateUserById(user);
											System.out.print("User with ID " + userId + " updated successfully !");
											recordUpdated++;
										} else {
											System.out.print("Invalid date of birth !");
										}
									} else {
										System.out.print("Invalid lastname length !");
									}
								} else {
									System.out.print("Invalid firstname length !");
								}
							} else {
								System.out.print("User with ID " + userId + " not existed !");
							}
						} else {
							System.out.print("User ID (" + id + ") format not valid !");
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
	 * Open file, validate input and read user information
	 */
	@Override
	public int readUserFromInputFile() {
		int recordRead = 0;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(Constants.READ_USER_PATH));
			try {
				int lineCount = 0;
				while (br.ready()) {
					lineCount++;
					System.out.print("Line " + lineCount + ": ");
					String id = br.readLine();
					if (InputValidator.isValidId(id)) {
						Integer userId = Integer.parseInt(id);
						if (userDAO.isUserExisted(userId)) {
							User user = userDAO.readUserById(userId);
							System.out.print(user.toString());
							recordRead++;
						} else {
							System.out.print("User with ID " + userId + " not existed !");
						}
					} else {
						System.out.print("User ID (" + id + ") format not valid !");
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
	 * Open file, validate information and delete user
	 */
	@Override
	public int deleteUserFromInputFile() {
		int recordDeleted = 0;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(Constants.DELETE_USER_PATH));
			try {
				int lineCount = 0;
				while (br.ready()) {
					lineCount++;
					System.out.print("Line " + lineCount + ": ");
					String id = br.readLine();
					if (InputValidator.isValidId(id)) {
						Integer userId = Integer.parseInt(id);
						if (userDAO.isUserExisted(userId)) {
							userDAO.deleteUserById(userId);
							System.out.print("User with ID " + userId + " deleted successfully !");
							recordDeleted++;
						} else {
							System.out.print("User with ID " + userId + " not existed !");
						}
					} else {
						System.out.print("User ID (" + id + ") format not valid !");
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
	 * Print all users information to excel file
	 */
	@Override
	public int printAllUsers() {
		TreeSet<User> userList = userDAO.readAllUser();
		int recordRead = 0;
		if (userList.size() > 0) {
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(Constants.EXCEL_TEMPLATE_USER_PATH);
				try {
					Workbook workbook = new HSSFWorkbook(fis);
					Sheet sheet = workbook.getSheet("UserStatus");
					int userCount = 1;
					for (User user : userList) {
						Row row = sheet.createRow(userCount);

						Cell cell = row.createCell(0);
						cell.setCellValue(user.getUserId());

						cell = row.createCell(1);
						cell.setCellValue(user.getFirstname());

						cell = row.createCell(2);
						cell.setCellValue(user.getLastname());

						cell = row.createCell(3);
						cell.setCellValue(user.getDob().toString());

						cell = row.createCell(4);
						cell.setCellValue(user.getCreatedDate().toString());

						cell = row.createCell(5);
						if (user.getBookLend() == null) {
							cell.setCellValue("null");
						} else {
							cell.setCellValue(user.getBookLend());
						}

						cell = row.createCell(6);
						if (user.getBookOverdue() == null) {
							cell.setCellValue("null");
						} else {
							cell.setCellValue(user.getBookOverdue());
						}

						userCount++;
						recordRead++;
					}
					FileOutputStream outputfile = null;
					String createdTime = LocalDateTime.now().toString();
					createdTime = createdTime.replace(':', '-');
					createdTime = createdTime.replace('.', '-');
					String filePath = Constants.PRINT_ALL_USER_PATH + "PrintUsers_" + createdTime + ".xls";
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
