package com.fsoft.hieupnm.booklendsystem.model.POJO;

import java.time.LocalDate;

public class User implements Comparable<User> {
	private Integer userId;
	private String firstname;
	private String lastname;
	private LocalDate dob;
	private LocalDate createdDate;
	private Integer bookLend;
	private Integer bookOverdue;

	// For create/update information of user
	public User(Integer userId, String firstname, String lastname, LocalDate dob) {
		this.userId = userId;
		this.firstname = firstname;
		this.lastname = lastname;
		this.dob = dob;
		this.createdDate = LocalDate.now();
	}

	// For read user with lending stats
	public User(Integer userId, String firstname, String lastname, LocalDate dob, LocalDate createdDate,
			Integer bookLend, Integer bookOverdue) {
		this.userId = userId;
		this.firstname = firstname;
		this.lastname = lastname;
		this.dob = dob;
		this.createdDate = createdDate;
		this.bookLend = bookLend;
		this.bookOverdue = bookOverdue;
	}

	public Integer getUserId() {
		return userId;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public LocalDate getDob() {
		return dob;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public Integer getBookLend() {
		return bookLend;
	}

	public Integer getBookOverdue() {
		return bookOverdue;
	}

	@Override
	public int compareTo(User user) {
		if (this.userId > user.getUserId()) {
			return 1;
		} else if (this.userId < user.getUserId()) {
			return -1;
		} else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object user) {
		try {
			User compareUser = (User) user;
			boolean idEqual = this.userId == compareUser.getUserId();
			boolean fnameEqual = this.firstname.equals(compareUser.getFirstname());
			boolean lnameEqual = this.lastname.equals(compareUser.getLastname());
			boolean dobEqual = this.dob.toString().equals(compareUser.getDob().toString());
			boolean crDateEqual = this.createdDate.toString().equals(compareUser.getCreatedDate().toString());
			if (idEqual && fnameEqual && lnameEqual && dobEqual && crDateEqual) {
				return true;
			}
		} catch (ClassCastException e) {

		}
		return false;
	}

	@Override
	public String toString() {
		return "[" + "ID: " + this.userId + ", " + "Fname: " + this.firstname + ", " + "Lname: " + this.lastname + ", "
				+ "DOB: " + this.dob + ", " + "CreatedDate: " + this.createdDate + ", " + "BookLend: " + this.bookLend
				+ ", " + "BookOverdue: " + this.bookOverdue + "]";
	}

}
