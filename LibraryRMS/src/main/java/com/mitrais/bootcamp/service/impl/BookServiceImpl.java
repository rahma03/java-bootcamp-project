package com.mitrais.bootcamp.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mitrais.bootcamp.dao.MysqlDao;
import com.mitrais.bootcamp.model.Book;
import com.mitrais.bootcamp.service.BookService;

public class BookServiceImpl implements BookService {
	private MysqlDao mysqlDao=null;
	@Override
	public List<Book> getAllBook() throws SQLException, ClassNotFoundException {
		List<Book> listAllBook = new ArrayList<Book>();
		try {
			mysqlDao = new MysqlDao();
			listAllBook = mysqlDao.selectAllBook();
			mysqlDao.close();
		} catch (SQLException | ClassNotFoundException e) {
			if(mysqlDao!=null){
				mysqlDao.close();
			}
			throw e;
		}
		return listAllBook;
	}

	@Override
	public void addNewBook(Book newBook) throws SQLException, ClassNotFoundException {
		try{
			mysqlDao = new MysqlDao();
			mysqlDao.addBook(newBook);
			mysqlDao.close();
		} catch (SQLException | ClassNotFoundException e) {
			if(mysqlDao!=null){
				mysqlDao.close();
			}
			throw e;
		}
	}

	@Override
	public void deleteBook(long existingBookId) throws SQLException, ClassNotFoundException {
		try {
			mysqlDao = new MysqlDao();
			if(!mysqlDao.deleteBookById(existingBookId)){
				mysqlDao.close();
				throw new SQLException("Book Not Found: Check your id");
			}
		} catch (SQLException | ClassNotFoundException e) {
			if(mysqlDao!=null){
				mysqlDao.close();
			}
			throw e;
		}
	}

	@Override
	public void updateBook(Book updatedBook) throws SQLException, ClassNotFoundException {
		try {
			mysqlDao = new MysqlDao();
			if(!mysqlDao.editBook(updatedBook)){
				mysqlDao.close();
				throw new SQLException("Book Not Found: Check your id");
			}
		} catch (SQLException | ClassNotFoundException e) {
			if(mysqlDao!=null){
				mysqlDao.close();
			}
			throw e;
		}
	}
}
