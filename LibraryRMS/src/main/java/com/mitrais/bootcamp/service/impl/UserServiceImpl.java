package com.mitrais.bootcamp.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mitrais.bootcamp.dao.MysqlDao;
import com.mitrais.bootcamp.model.User;
import com.mitrais.bootcamp.service.UserService;

public class UserServiceImpl implements UserService {
	private MysqlDao mysqlDao=null;
	@Override
	public boolean loginService(String user, String password) {
		boolean result = false;
		try {
			mysqlDao = new MysqlDao();
			User userLogin = mysqlDao.getUserByUsername(user);
			if(userLogin!=null)
				if(password.equals(userLogin.getPassword()))
					return true;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if(mysqlDao!=null){
				mysqlDao.close();
			}
		}
		return result; 
	}

	@Override
	public List<User> listUserService() throws SQLException,ClassNotFoundException{
		List<User> listAllUser = new ArrayList<User>();
		try {
			mysqlDao = new MysqlDao();
			listAllUser = mysqlDao.getAllUserList();
		} catch (SQLException | ClassNotFoundException e) {
			if(mysqlDao!=null){
				mysqlDao.close();
			}
			throw e;
		} 
		return listAllUser;
	}

	@Override
	public void addNewUser(User newUser) throws SQLException, ClassNotFoundException {
		try{
			mysqlDao = new MysqlDao();
			mysqlDao.insertUser(newUser,"admin");
		} catch (SQLException | ClassNotFoundException e) {
			if(mysqlDao!=null)
				mysqlDao.close();
			throw e;
		}
	}

	@Override
	public void deleteUser(String existingUser) throws SQLException, ClassNotFoundException {
		try {
			mysqlDao = new MysqlDao();
			mysqlDao.deleteUser(existingUser);
			mysqlDao.close();
		} catch (SQLException | ClassNotFoundException e) {
			if(mysqlDao!=null)
				mysqlDao.close();
			throw e;
		}
	}

	@Override
	public void updateUser(User updatedUser) throws SQLException, ClassNotFoundException {
		try {
			mysqlDao = new MysqlDao();
			mysqlDao.editUser(updatedUser);
			mysqlDao.close();
		} catch (SQLException | ClassNotFoundException e) {
			if(mysqlDao!=null)
				mysqlDao.close();
			throw e;
		}
	}

}
