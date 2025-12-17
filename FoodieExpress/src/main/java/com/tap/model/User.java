package com.tap.model;

import java.sql.Timestamp;
import java.util.Scanner;

public class User {
	private int userId;
	private String userName;
	private String password;
	private String email;
	private String phone; 
	private String address;
	private String role;
	private Timestamp createdDate;
	private Timestamp lastLoginDate;

	public User(String userName, String password, String email, String phone, String address, String role,
			Timestamp createdDate, Timestamp lastLoginDate) {
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.role = role;
		this.createdDate = createdDate;
		this.lastLoginDate = lastLoginDate;
	}

	public User() {
	}

	public User(int userId, String userName, String password, String email, String phone, String address, String role,
			Timestamp createdDate, Timestamp lastLoginDate) {
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.role = role;
		this.createdDate = createdDate;
		this.lastLoginDate = lastLoginDate;
	}

	// Getters & Setters
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public Timestamp getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Timestamp lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", password=" + password + ", email=" + email
				+ ", phone=" + phone + ", address=" + address + ", role=" + role + ", createdDate=" + createdDate
				+ ", lastLoginDate=" + lastLoginDate + "]";
	}

	public static User getUserDetails() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the UserId:");
		int userId = scan.nextInt();
		scan.nextLine();

		System.out.println("Enter the UserName:");
		String userName = scan.nextLine();

		System.out.println("Enter the Password:");
		String password = scan.nextLine();

		System.out.println("Enter the Email:");
		String email = scan.nextLine();

		System.out.println("Enter the Phone:");
		String phone = scan.nextLine();

		System.out.println("Enter the Address:");
		String address = scan.nextLine();

		System.out.println("Enter the Role:");
		String role = scan.nextLine();

		Timestamp createdDate = new Timestamp(System.currentTimeMillis());
		Timestamp lastLoginDate = new Timestamp(System.currentTimeMillis());

		return new User(userId, userName, password, email, phone, address, role, createdDate, lastLoginDate);
	}
}
