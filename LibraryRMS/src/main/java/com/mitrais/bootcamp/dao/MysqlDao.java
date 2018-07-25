package com.mitrais.bootcamp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.mitrais.bootcamp.model.Book;
import com.mitrais.bootcamp.model.User;

public class MysqlDao {
	private Connection connection;
	private PreparedStatement ps;
	private ResultSet rs;

	public MysqlDao() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library-rms", "root", "");
		connection.setAutoCommit(false);
	}

	public void close() {
		try {
			rs.close();
		} catch (Exception e) {
		}
		try {
			ps.close();
		} catch (Exception e) {
		}
		try {
			connection.close();
		} catch (Exception e) {
		}
	}

	public List<User> getAllUserList() throws SQLException {
		String query = "select fullname, birthdate, alamat, username, login_status, block_status, pin_try from user";
		ps = connection.prepareStatement(query);
		rs = ps.executeQuery();
		List<User> resultQuery = new ArrayList<User>();
		while (rs.next()) {
			User newUser = new User();
			newUser.setFullName(rs.getString("fullname"));
			newUser.setBirthdate(rs.getDate("birthdate"));
			newUser.setAlamat(rs.getString("alamat"));
			newUser.setUsername(rs.getString("username"));
			newUser.setLogin_status(rs.getInt("login_status"));
			newUser.setBlock_status(rs.getInt("block_status"));
			newUser.setPin_try(rs.getInt("pin_try"));
			resultQuery.add(newUser);
		}
		return resultQuery;
	}

	public User getUserByUsername(String username) throws SQLException {
		String query = "select fullname, birthdate, alamat, username, password, login_status, block_status, pin_try from user where username=?";
		ps = connection.prepareStatement(query);
		ps.setString(1, username);
		rs = ps.executeQuery();
		while (rs.next()) {
			User newUser = new User();
			newUser.setFullName(rs.getString("fullname"));
			newUser.setBirthdate(rs.getDate("birthdate"));
			newUser.setAlamat(rs.getString("alamat"));
			newUser.setUsername(rs.getString("username"));
			newUser.setPasswordWithoutEncode(rs.getString("password"));
			newUser.setLogin_status(rs.getInt("login_status"));
			newUser.setBlock_status(rs.getInt("block_status"));
			newUser.setPin_try(rs.getInt("pin_try"));
			return newUser;
		}
		return null;
	}

	public void insertUser(User newUser, String userCreator) throws SQLException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String query = "insert into user(username,fullname,birthdate,alamat,password,created_by) values(?,?,?,?,?,?)";
		ps = connection.prepareStatement(query);
		ps.setString(1, newUser.getUsername());
		ps.setString(2, newUser.getFullName());
		ps.setString(3, sdf.format(newUser.getBirthdate()));
		ps.setString(4, newUser.getAlamat());
		ps.setString(5, newUser.getPasswordWithoutDecode());
		ps.setString(6, userCreator);
		if (ps.executeUpdate() < 1) {
			throw new SQLException();
		}
		connection.commit();
	}

	public void deleteUser(String deletedUsername) throws SQLException {
		String query = "delete from user where username=?";
		ps = connection.prepareStatement(query);
		ps.setString(1, deletedUsername);
		if (ps.executeUpdate() == 0) {
			throw new SQLException();
		}
		connection.commit();
	}

	public void editUser(User updatedUser) throws SQLException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String query = "update user set fullname=?, alamat=?, password=?, birthdate=?, "
				+ "pin_try=?, login_status=?, block_status=? where username=?";
		ps = connection.prepareStatement(query);
		ps.setString(1, updatedUser.getFullName());
		ps.setString(2, updatedUser.getAlamat());
		ps.setString(3, updatedUser.getPasswordWithoutDecode());
		ps.setString(4, sdf.format(updatedUser.getBirthdate()));
		ps.setInt(5, updatedUser.getPin_try());
		ps.setInt(6, updatedUser.getLogin_status());
		ps.setInt(7, updatedUser.getBlock_status());
		ps.setString(8, updatedUser.getUsername());
		if (ps.executeUpdate() == 0) {
			throw new SQLException();
		}
		connection.commit();
	}

	// =============================================Book Section=============================================

	public Book selectBookById(long id) throws SQLException {
		String query = "select * from book where id =?";
		ps = connection.prepareStatement(query);
		ps.setLong(1, id);
		rs = ps.executeQuery();
		Book resultBook = null;
		if (rs.next()) {
			resultBook = new Book();
			resultBook.setId(rs.getInt("id"));
			resultBook.setAuthor(rs.getString("author"));
			resultBook.setTitle(rs.getString("title"));
			resultBook.setAvailable(rs.getInt("stock") > 0);
			resultBook.setStock(rs.getInt("stock"));
		}
		return resultBook;

	}

	public List<Book> selectAllBook() throws SQLException {
		List<Book> listOfBook = new ArrayList<Book>();
		String query = "select * from book";
		ps = connection.prepareStatement(query);
		rs = ps.executeQuery();

		while (rs.next()) {
			Book resultBook = new Book();
			resultBook.setId(rs.getInt("id"));
			resultBook.setAuthor(rs.getString("author"));
			resultBook.setTitle(rs.getString("title"));
			resultBook.setAvailable(rs.getInt("stock") > 0);
			resultBook.setStock(rs.getInt("stock"));
			listOfBook.add(resultBook);
		}
		return listOfBook;
	}

	public boolean addBook(Book newBook) throws SQLException {
		boolean result = false;
		String query = "insert into book(author,title,stock) values (?,?,?)";
		ps = connection.prepareStatement(query);
		ps.setString(1, newBook.getAuthor());
		ps.setString(2, newBook.getTitle());
		ps.setInt(3, newBook.getStock());
		result = ps.executeUpdate() > 0;
		connection.commit();
		return result;
	}

	public boolean editBook(Book updatedBook) throws SQLException {
		boolean result = false;
		String query = "update book set title =?, author=?, stock=? where id = ?";
		ps = connection.prepareStatement(query);
		ps.setString(1, updatedBook.getTitle());
		ps.setString(2, updatedBook.getAuthor());
		ps.setInt(3, updatedBook.getStock());
		ps.setLong(4, updatedBook.getId());
		result = ps.executeUpdate() > 0;
		connection.commit();
		return result;
	}

	public boolean deleteBookById(long id) throws SQLException {
		boolean result = false;
		String query = "delete from book where id =?";
		ps = connection.prepareStatement(query);
		ps.setLong(1, id);

		result = ps.executeUpdate() > 0;
		connection.commit();
		return result;
	}
}
