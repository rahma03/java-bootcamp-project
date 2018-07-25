package com.mitrais.bootcamp.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.mitrais.bootcamp.model.User;

public interface UserService {
	public boolean loginService(String user, String password);
	public List<User> listUserService()throws SQLException,ClassNotFoundException;
	public void addNewUser(User newUser)throws SQLException,ClassNotFoundException;
	public void deleteUser(String existingUser)throws SQLException,ClassNotFoundException;
	public void updateUser(User updatedUser)throws SQLException,ClassNotFoundException;
}
