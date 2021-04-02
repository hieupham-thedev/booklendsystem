package com.fsoft.hieupnm.booklendsystem.model.DAO.implementation;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.TreeSet;

import com.fsoft.hieupnm.booklendsystem.model.DAO.UserDAO;
import com.fsoft.hieupnm.booklendsystem.model.POJO.User;
import com.fsoft.hieupnm.booklendsystem.utils.dbconnection.DBUtils;

public class UserDAOImpl implements UserDAO {

	private static Connection con = null;

	/*
	 * Create new user in database using User object with fully declared information
	 */
	@Override
	public boolean createNewUser(User user) {
		con = DBUtils.getDBConnection();
		if (con != null) {

			String SQL = "INSERT INTO user(userId, firstname, lastname, DOB, createdDate) VALUES (?, ?, ?, ?, ?)";
			try {
				PreparedStatement preparedStatement = con.prepareStatement(SQL);
				preparedStatement.setInt(1, user.getUserId());
				preparedStatement.setString(2, user.getFirstname());
				preparedStatement.setString(3, user.getLastname());
				preparedStatement.setDate(4, Date.valueOf(user.getDob().toString()));
				preparedStatement.setDate(5, Date.valueOf(user.getCreatedDate()));

				int affectedRow = preparedStatement.executeUpdate();
				if (affectedRow > 0) {
					return true;
				}
			} catch (SQLException e) {
				System.out.println("Error SQL execution !");
			} finally {
				try {
					con.close();
				} catch (SQLException e) {
					System.out.println("Error closing connection !");
				}
			}

		}
		return false;
	}

	/*
	 * Get information of one user by userId
	 */
	@Override
	public User readUserById(Integer userId) {
		con = DBUtils.getDBConnection();
		if (con != null) {

			String SQL = "SELECT * FROM v_user_stats WHERE userId = ?";
			try {
				PreparedStatement preparedStatement = con.prepareStatement(SQL);
				preparedStatement.setInt(1, userId);
				ResultSet resultSet = preparedStatement.executeQuery();
				if (resultSet.next()) {
					String firstname = resultSet.getString(2);
					String lastname = resultSet.getString(3);
					LocalDate dob = resultSet.getDate(4).toLocalDate();
					LocalDate createdDate = resultSet.getDate(5).toLocalDate();
					Integer bookLend = resultSet.getInt(6);
					Integer bookOverdue = resultSet.getInt(7);
					return new User(userId, firstname, lastname, dob, createdDate, bookLend, bookOverdue);
				}
			} catch (SQLException e) {
				System.out.println("Error SQL execution !");
			} finally {
				try {
					con.close();
				} catch (SQLException e) {
					System.out.println("Error closing connection !");
				}
			}

		}
		return null;
	}

	/*
	 * Get all user information from database and return list of user sort by userId
	 */
	@Override
	public TreeSet<User> readAllUser() {
		TreeSet<User> userList = new TreeSet<>();
		con = DBUtils.getDBConnection();
		if (con != null) {

			String SQL = "SELECT * FROM v_user_stats";
			try {
				PreparedStatement preparedStatement = con.prepareStatement(SQL);
				ResultSet resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					Integer userId = resultSet.getInt(1);
					String firstname = resultSet.getString(2);
					String lastname = resultSet.getString(3);
					LocalDate dob = resultSet.getDate(4).toLocalDate();
					LocalDate createdDate = resultSet.getDate(5).toLocalDate();
					Integer bookLend = resultSet.getInt(6);
					Integer bookOverdue = resultSet.getInt(7);
					User user = new User(userId, firstname, lastname, dob, createdDate, bookLend, bookOverdue);
					userList.add(user);
				}
			} catch (SQLException e) {
				System.out.println("Error SQL execution !");
			} finally {
				try {
					con.close();
				} catch (SQLException e) {
					System.out.println("Error closing connection !");
				}
			}

		}
		return userList;
	}

	/*
	 * Update user with existed ID with new information
	 */
	@Override
	public boolean updateUserById(User user) {
		con = DBUtils.getDBConnection();
		if (con != null) {

			String SQL = "UPDATE user SET firstname = ?, lastname = ?, DOB = ? WHERE userId = ?";
			try {
				PreparedStatement preparedStatement = con.prepareStatement(SQL);
				preparedStatement.setString(1, user.getFirstname());
				preparedStatement.setString(2, user.getLastname());
				preparedStatement.setDate(3, Date.valueOf(user.getDob().toString()));
				preparedStatement.setInt(4, user.getUserId());
				int affectedRow = preparedStatement.executeUpdate();
				if (affectedRow > 0) {
					return true;
				}
			} catch (SQLException e) {
				System.out.println("Error SQL execution !");
			} finally {
				try {
					con.close();
				} catch (SQLException e) {
					System.out.println("Error closing connection !");
				}
			}

		}
		return false;
	}

	/*
	 * Delete user in database
	 */
	@Override
	public boolean deleteUserById(Integer userId) {
		con = DBUtils.getDBConnection();
		if (con != null) {

			String SQL = "DELETE FROM user WHERE userId = ?";
			try {
				PreparedStatement preparedStatement = con.prepareStatement(SQL);
				preparedStatement.setInt(1, userId);
				int affectedRow = preparedStatement.executeUpdate();
				if (affectedRow > 0) {
					return true;
				}
			} catch (SQLException e) {
				System.out.println("Error SQL execution !");
			} finally {
				try {
					con.close();
				} catch (SQLException e) {
					System.out.println("Error closing connection !");
				}
			}

		}
		return false;
	}

	/*
	 * Check if user ID was existed in database
	 */
	@Override
	public boolean isUserExisted(Integer userId) {
		con = DBUtils.getDBConnection();
		if (con != null) {

			String SQL = "SELECT * FROM user WHERE userId = ?";
			try {
				PreparedStatement preparedStatement = con.prepareStatement(SQL);
				preparedStatement.setInt(1, userId);
				ResultSet resultSet = preparedStatement.executeQuery();
				resultSet.last();
				if (resultSet.getRow() > 0) {
					return true;
				}
			} catch (SQLException e) {
				System.out.println("Error SQL execution !");
			} finally {
				try {
					con.close();
				} catch (SQLException e) {
					System.out.println("Error closing connection !");
				}
			}

		}
		return false;
	}

}
