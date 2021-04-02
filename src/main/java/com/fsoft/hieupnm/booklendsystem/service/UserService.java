package com.fsoft.hieupnm.booklendsystem.service;

public interface UserService {
	int addNewUserFromInputFile();

	int updateUserFromInputFile();

	int readUserFromInputFile();

	int deleteUserFromInputFile();

	int printAllUsers();
}
