package com.tap.dao;

import java.util.List;

import com.tap.model.User;

public interface UserDAO {
	
	int addUser(User user);
	User getUser(String email, String password);
	User getUser(int userId);
	boolean updateUser(User user);
	void deleteUser(int userId);
	List<User> getAllUser();
	
	

}
