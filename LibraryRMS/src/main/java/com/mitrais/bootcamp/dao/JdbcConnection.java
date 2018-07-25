package com.mitrais.bootcamp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mitrais.bootcamp.model.Book;



public class JdbcConnection {
	private Connection connection;
	private PreparedStatement ps;
	private ResultSet rs ;
	
	public JdbcConnection () throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mitrais_training","root","");
	}
	
	public Book selectBookById(long id){
		try {
			String query = "select * from book where id =?";
			ps = connection.prepareStatement(query);
			ps.setLong(1, id);
			rs = ps.executeQuery();
			Book resultBook = null;
			if(rs.next()){
				resultBook = new Book();
				resultBook.setId(rs.getInt("id"));
				resultBook.setAuthor(rs.getString("author"));
				resultBook.setTitle(rs.getString("title"));
				resultBook.setAvailable(rs.getInt("stock")>0);
			}
			return resultBook;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
	}
	
	public List<Book> selectAllBook(){
		List<Book> listOfBook = new ArrayList<Book>();
		try {
			String query = "select * from book";
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()){
				Book resultBook = new Book();
				resultBook.setId(rs.getInt("id"));
				resultBook.setAuthor(rs.getString("author"));
				resultBook.setTitle(rs.getString("title"));
				resultBook.setAvailable(rs.getInt("stock")>0);
				listOfBook.add(resultBook);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listOfBook;
	}
	
	public boolean addBook (Book newBook){
		try {
			String query = "insert into book(author,title,stock) values (?,?,0)";
			ps = connection.prepareStatement(query);
			ps.setString(1, newBook.getAuthor());
			ps.setString(2, newBook.getTitle());
			
			return ps.executeUpdate()>0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void closeAll (){
		try {
			if(rs!=null)
				rs.close();
			if(ps!=null)
				ps.close();
			if(connection!=null)
				connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean editBook(Book upadatedBook){
		try {
			String query = "update book set title =?, author=?, stock=? where id = ?";
			ps = connection.prepareStatement(query);
			ps.setString(1, upadatedBook.getTitle());
			ps.setString(2, upadatedBook.getAuthor());
			ps.setInt(3, upadatedBook.isAvailable()?1:0);
			ps.setLong(4, upadatedBook.getId());
			return ps.executeUpdate()>0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean deleteBookById(long id){
		try {
			String query = "delete from book where id =?";
			ps = connection.prepareStatement(query);
			ps.setLong(1, id);
			
			return ps.executeUpdate()>0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} 
	}
}
