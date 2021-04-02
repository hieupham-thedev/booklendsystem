package com.fsoft.hieupnm.booklendsystem.model.DAO;

import java.util.TreeSet;

import com.fsoft.hieupnm.booklendsystem.model.POJO.User;

public interface UserDAO {
	boolean createNewUser(User user);

	User readUserById(Integer userId);

	TreeSet<User> readAllUser();

	boolean updateUserById(User user);

	boolean deleteUserById(Integer userId);

	boolean isUserExisted(Integer userId);

}
